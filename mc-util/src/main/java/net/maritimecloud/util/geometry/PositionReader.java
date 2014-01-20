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
package net.maritimecloud.util.geometry;

import static java.util.Objects.requireNonNull;

/**
 * A interface describing a way to get the current position (and timestamp of the reading) for an object.
 * <p>
 * Any implementations are not required to be thread safe.
 * 
 * @see PositionReaderSimulator
 * @author Kasper Nielsen
 */
public abstract class PositionReader {

    /**
     * Returns the current position and a timestamp for when the position was read. The timestamp, measured in
     * milliseconds, must be the difference between the current time and midnight, January 1, 1970 UTC.
     * 
     * @return the current position and time
     */
    public abstract PositionTime getCurrentPosition();

    /**
     * Returns a position reader that will return the current position on android devices.
     * 
     * @return a position reader that will return the current position on android devices
     * @throws UnsupportedOperationException
     *             if invoked on a non-android platform
     */
    public static PositionReader androidReader() {
        throw new UnsupportedOperationException("This method is only supported on the android platforms");
    }

    /**
     * Returns a reader that returns the same position every time.
     * 
     * @param positionTime
     *            the position time to return every time
     * @return a new fixed position reader
     */
    public static PositionReader fixedPosition(final PositionTime positionTime) {
        requireNonNull(positionTime, "positionTime is null");
        return new PositionReader() {
            public PositionTime getCurrentPosition() {
                return positionTime;
            }
        };
    }
}
