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
package net.maritimecloud.mms.server.connectionold;

import static java.util.Objects.requireNonNull;
import net.maritimecloud.internal.mms.messages.spi.MmsMessage;
import net.maritimecloud.message.Message;
import net.maritimecloud.mms.server.MmsServer;
import net.maritimecloud.mms.server.connectionold.transport.ServerTransport;
import net.maritimecloud.mms.server.targets.Target;
import net.maritimecloud.net.mms.MmsConnectionClosingCode;
import net.maritimecloud.util.Binary;

/**
 *
 * @author Kasper Nielsen
 */
public class ServerConnection {

    final MmsServerConnectionBus bus;

    final Binary id = Binary.random(32);

    final MmsServer is;

    final Target target;

    volatile ServerTransport transport;

    final Worker worker = new Worker(this);

    ServerConnection(Target target, MmsServer is) {
        this.target = requireNonNull(target);
        this.bus = requireNonNull(is.getService(MmsServerConnectionBus.class));
        this.is = is;
    }

    /**
     * @return the target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * @param serverTransport
     * @param m
     */
    public void messageReceive(ServerTransport serverTransport, MmsMessage m) {
        if (serverTransport == this.transport) {
            worker.messageReceived(m);
        }
    }

    public OutstandingMessage messageSend(Message message) {
        return worker.messageSend(message);
    }

    /**
     * @param serverTransport
     * @param reason
     */
    public void transportDisconnected(ServerTransport serverTransport, MmsConnectionClosingCode reason) {}
}
