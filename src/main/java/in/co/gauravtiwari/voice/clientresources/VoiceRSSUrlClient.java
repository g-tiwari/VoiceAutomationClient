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

package in.co.gauravtiwari.voice.clientresources;

import in.co.gauravtiwari.voice.client.LanguageUtils;
import in.co.gauravtiwari.voice.design.Language;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

/**
 * created by Gaurav Tiwari
 * <p>
 * This class is used for doing any action via VoiceRSS TTS service
 */
public class VoiceRSSUrlClient {

    private static final Logger LOG = LoggerFactory.getLogger(VoiceRSSUrlClient.class);

    private static final String VOICERSS_BASEURL = "https://api.voicerss.org/?";
    private String VOICERSS_APIKEY;

    /**
     * Default constructor for VoiceRSSUrlClient.
     * @param apiKey - VoiceRss key
     */
    public VoiceRSSUrlClient(String apiKey) {
        this.VOICERSS_APIKEY = apiKey;
    }

    /**
     * Get co.gauravtiwari.voice.clientresources.voice url from VOICE RSS service.
     *
     * @param text     - text
     * @param language - language
     * @return -  url
     * @throws ClientOperationException - exception thrown when communicating to Voice RSS service
     */
    public String getVoiceUrl(final String text, final Language language) throws ClientOperationException {
        String voiceUrl = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(VOICERSS_BASEURL);
            uriBuilder.addParameter("key", VOICERSS_APIKEY);
            uriBuilder.addParameter("c", "WAV");
            uriBuilder.addParameter("hl", LanguageUtils.getStringFor(language));
            uriBuilder.addParameter("src", text);
            voiceUrl = uriBuilder.toString();
        } catch (URISyntaxException ue) {
            ue.printStackTrace();
            LOG.error(ue.getMessage());
        }
        return voiceUrl;
    }

}
