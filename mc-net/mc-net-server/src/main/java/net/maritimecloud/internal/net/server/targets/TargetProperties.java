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
package net.maritimecloud.internal.net.server.targets;


/**
 * 
 * @author Kasper Nielsen
 */
public class TargetProperties {

    private final String description;

    private final String name;

    private final String organization;

    /**
     * @param description
     * @param name
     * @param organization
     */
    public TargetProperties(String name, String description, String organization) {
        this.description = description;
        this.name = name;
        this.organization = organization;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "TargetProperties [description=" + description + ", name=" + name + ", organization=" + organization
                + "]";
    }

    public static TargetProperties createFrom(String commaSeparated) {
        return new TargetProperties(extractFrom("name", null, commaSeparated), extractFrom("description", null,
                commaSeparated), extractFrom("organization", null, commaSeparated));
    }

    private static String extractFrom(String name, String defaultValue, String extractFrom) {
        String[] ss = extractFrom.split(",");
        for (String s : ss) {
            int i = s.indexOf(name + "=");
            if (i >= 0) {
                return s.substring(i + name.length() + 1);
            }
        }
        return defaultValue;
    }
}
