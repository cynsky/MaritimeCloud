/* Copyright (c) 2011 Danish Maritime Authority.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.maritimecloud.mms.server;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.TimeUnit;

import net.maritimecloud.core.id.ServerId;
import net.maritimecloud.mms.server.broadcast.ServerBroadcastManager;
import net.maritimecloud.mms.server.connectionold.ConnectionManager;
import net.maritimecloud.mms.server.connectionold.MmsServerConnectionBus;
import net.maritimecloud.mms.server.connectionold.server.UnsecureWebSocketServer;
import net.maritimecloud.mms.server.endpoints.ServerEndpointManager;
import net.maritimecloud.mms.server.endpoints.ServerServices;
import net.maritimecloud.mms.server.rest.WebServer;
import net.maritimecloud.mms.server.targets.TargetManager;

import org.cakeframework.container.Container;
import org.cakeframework.container.Container.State;
import org.cakeframework.container.ContainerConfiguration;

/**
 *
 * @author Kasper Nielsen
 */
public class MmsServer {

    private final Container container;

    private final ServerId serverId;

    public MmsServer(int port) {
        this(new MmsServerConfiguration().setServerPort(port));
    }

    /**
     * Creates a new instance of this class.
     *
     * @param configuration
     *            the configuration
     */
    public MmsServer(MmsServerConfiguration configuration) {
        serverId = requireNonNull(configuration.getId());

        ContainerConfiguration conf = new ContainerConfiguration();
        conf.addService(configuration);
        conf.addService(this);

        conf.addService(ServerServices.class);
        conf.addService(TargetManager.class);
        conf.addService(ConnectionManager.class);
        conf.addService(UnsecureWebSocketServer.class);
        conf.addService(MmsServerConnectionBus.class);
        conf.addService(ServerBroadcastManager.class);
        conf.addService(ServerEndpointManager.class);
        if (configuration.getWebserverPort() > 0) {
            conf.addService(WebServer.class);
        }
        container = conf.create();
    }

    public boolean awaitTerminated(long timeout, TimeUnit unit) throws InterruptedException {
        return container.awaitState(State.TERMINATED, timeout, unit);
    }

    /**
     * @return the serverId
     */
    public ServerId getServerId() {
        return serverId;
    }

    public <T> T getService(Class<T> service) {
        return container.getService(service);
    }

    public void shutdown() {
        container.shutdown();
    }

    public void startBlocking() {
        container.start().join();
    }
}
