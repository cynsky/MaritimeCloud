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
import net.maritimecloud.mms.server.MmsServer;
import net.maritimecloud.mms.server.targets.TargetManager;

/**
 *
 * @author Kasper Nielsen
 */
public class ConnectionManager {

    public final MmsServer server;

    final TargetManager targetManager;

    public ConnectionManager(MmsServer server, TargetManager targetManager) {
        this.server = requireNonNull(server);
        this.targetManager = requireNonNull(targetManager);
    }
}
