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
package net.maritimecloud.broker;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {

    static final Logger LOG = LoggerFactory.getLogger(Main.class);

    @Parameter(names = "-port", description = "The port to run the server on")
    int port = 8090;

    void kill() {

    }

    void run(String[] args) throws Exception {
        new JCommander(this, args);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                kill();
            }
        });

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ((ServerConnector) server.getConnectors()[0]).setReuseAddress(true);
        context.setContextPath("/rest");
        ServletHolder sho = new ServletHolder(new ServletContainer());
        sho.setClassName("org.glassfish.jersey.servlet.ServletContainer");
        sho.setInitParameter("jersey.config.server.provider.packages", "net.maritimecloud.broker.resources");
        context.addServlet(sho, "/*");

        HandlerWrapper hw = new HandlerWrapper();
        hw.setHandler(context);
        server.setHandler(hw);

        server.start();
        server.join();

    }

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

}
