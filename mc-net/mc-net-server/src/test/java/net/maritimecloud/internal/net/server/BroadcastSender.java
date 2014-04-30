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
package net.maritimecloud.internal.net.server;

import java.util.Date;

import net.maritimecloud.net.MaritimeCloudClient;
import net.maritimecloud.net.MaritimeCloudClientConfiguration;
import net.maritimecloud.net.broadcast.BroadcastOptions;

/**
 * 
 * @author Kasper Nielsen
 */
public class BroadcastSender {
    public static void main(String[] args) throws Exception {
        MaritimeCloudClientConfiguration conf = MaritimeCloudClientConfiguration.create("mmsi://65487");
        // conf.setHost("service.e-navigation.net:43234");
        MaritimeCloudClient cc = conf.build();
        BroadcastOptions options = new BroadcastOptions();
        options.setBroadcastRadius(10000);

        for (;;) {
            cc.broadcast(new Message("Current Date: " + new Date()), options);
            Thread.sleep(1000);
        }
    }
}