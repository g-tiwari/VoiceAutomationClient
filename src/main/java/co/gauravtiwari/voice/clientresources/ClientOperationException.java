/**
 Copyright [2018] [Gaurav Tiwari]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package co.gauravtiwari.voice.clientresources;


import java.io.IOException;

/**
 * create by Gaurav Tiwari
 */

/**
 * Customized Exception thrown in co.gauravtiwari.voice.clientresources.voice automation client operations.
 */
public class ClientOperationException extends IOException {

    /**
     * Default constructor for ClientOperationException.
     */
    public ClientOperationException() {
        super();
    }

    /**
     * Constructor for ClientOperationException.
     *
     * @param message - error message
     */
    public ClientOperationException(final String message) {
        super(message);
    }

    /**
     * Constructor for ClientOperationException.
     *
     * @param message - error message
     * @param cause   - error cause
     */
    public ClientOperationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for ClientOperationException.
     *
     * @param cause - error cause
     */
    public ClientOperationException(final Throwable cause) {
        super(cause);
    }
}
