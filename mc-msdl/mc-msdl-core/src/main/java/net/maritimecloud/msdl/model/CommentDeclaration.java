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
package net.maritimecloud.msdl.model;

/**
 *
 * @author Kasper Nielsen
 */
public interface CommentDeclaration {
    // Ideen er at man skal kunne dokumentere nogle information i de MSDL filer.
    // Annotations bliver _ALDRIG_ brugt til dokumentation de har altid en eller anden
    // form for semantics mening.
    String TITLE = "foo";

    String getMain();

    String getMainUncapitalized();
}
