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

package co.gauravtiwari.voice.client;


import co.gauravtiwari.voice.messagemodel.VoiceAutomationMessage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * created by Gaurav Tiwari
 * <p>
 * This HTTPClient is used to communicate with Voice Automation server over http
 */
public class HTTPClient {

    private Client client;
    private String serverUrl;

    /**
     * Constructor for HTTPClient.
     *
     * @param serverUrl - url of the server connecting to
     */
    public HTTPClient(final String serverUrl) {
        this.serverUrl = serverUrl;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);
        // client.addFilter(new LoggingFilter());
    }

    /**
     * Send HTTP request to /load endpoint.
     *
     * @param url - url of the com.se2automate.voice.clientresources.voice file to be included in JSON payload
     * @return - response from server
     */
    public VoiceAutomationMessage load(final String url) {
        String input = "{\"voiceFilePath\": \"" + url + "\"}";
        return callAPIHelper("load", input);
    }

    /**
     * Send HTTP request to /play endpoint.
     *
     * @param filename - filename to play
     * @return - response from server
     */
    public VoiceAutomationMessage play(final String filename) {
        String input = "{\"voiceFilePath\": \"" + filename + "\"}";
        return callAPIHelper("play", input);
    }

    /**
     * Send HTTP request to /record enpoint
     *
     * @param duration - the duration to record
     * @return - response from server
     */
    public VoiceAutomationMessage record(final long duration) {
        String input = "{\"recordingDuration\": \"" + duration + "\"}";
        return callAPIHelper("record", input);
    }

    /**
     * Helper method for making HTTP requests to given API endpoints.
     *
     * @param endpoint - API endpoint on server
     * @param input    - JSON payload
     * @return - client response
     */
    private VoiceAutomationMessage callAPIHelper(final String endpoint, final String input) {
        WebResource webResource = client.resource(serverUrl + endpoint);
        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);
        return response.getEntity(VoiceAutomationMessage.class);
    }
}
