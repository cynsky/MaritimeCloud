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
package net.maritimecloud.internal.net.client.broadcast.stubs;

import java.io.IOException;

import net.maritimecloud.core.message.MessageWriter;
import net.maritimecloud.net.broadcast.BroadcastMessage;

/**
 *
 * @author Kasper Nielsen
 */
public class HelloWorld implements BroadcastMessage {

    private String message;

    /**
     * @return the message
     */
    public String getMsg() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     * @return
     */
    public HelloWorld setMsg(String message) {
        this.message = message;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public void writeTo(MessageWriter w) throws IOException {
        w.writeString(1, "msg", message);
    }
}