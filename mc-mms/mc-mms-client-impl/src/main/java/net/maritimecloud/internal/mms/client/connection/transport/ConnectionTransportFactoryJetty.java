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
package net.maritimecloud.internal.mms.client.connection.transport;

import net.maritimecloud.net.mms.MmsConnection;

import org.cakeframework.container.RunOnStart;
import org.cakeframework.container.RunOnStop;
import org.eclipse.jetty.websocket.jsr356.ClientContainer;

/**
 * A transport manager that uses Jetty to create new web socket connections.
 *
 * @author Kasper Nielsen
 */
public class ConnectionTransportFactoryJetty extends ConnectionTransportFactory {

    /** The single instance of a WebSocketContainer. */
    private final ClientContainer container = new ClientContainer();

    /** {@inheritDoc} */
    @Override
    public ConnectionTransport create(ConnectionTransportListener listener, MmsConnection.Listener connectionListener) {
        return new ConnectionTransportJsr356(listener, connectionListener, container);
    }

    /**
     * Starts Jetty.
     *
     * @throws Exception
     *             if Jetty failed to start properly
     */
    @RunOnStart
    public void start() throws Exception {
        container.start();
    }

    /**
     * Stops Jetty.
     *
     * @throws Exception
     *             if Jetty failed to stop properly
     */
    @RunOnStop
    public void stop() throws Exception {
        container.stop();
    }
}
