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

package com.se2automate.voice.client;

import com.se2automate.voice.clientresources.ClientOperationException;
import com.se2automate.voice.clientresources.Voice;
import com.se2automate.voice.clientresources.VoiceRSSUrlClient;
import com.se2automate.voice.design.Language;
import com.se2automate.voice.design.VoiceAutomationClientModel;
import com.se2automate.voice.messagemodel.VoiceAutomationMessage;
import com.se2automate.voice.messagemodel.VoiceAutomationMessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * created by Gaurav Tiwari
 * Voice Automation client used for com.se2automate.voice.clientresources.voice automation tests.
 */
public class VoiceAutomationClient implements VoiceAutomationClientModel {
    private static final Logger LOG = LoggerFactory.getLogger(VoiceAutomationClient.class);
    private HTTPClient httpClient;
    private VoiceRSSUrlClient voiceRSSUrlClient;

    /**
     * Constructor for VoiceAutomationClient.
     *
     * @throws ClientOperationException - exception in instantiating com.se2automate.voice.clientresources.voice automation client
     */
    public VoiceAutomationClient() throws ClientOperationException {
        String serverEndpoint = System.getProperty("VoiceAutomationServerEndpoint");
        if (null == serverEndpoint) {
            throw new ClientOperationException("Please specify url of the com.se2automate.voice.clientresources.voice automation server by passing JVM argument 'VoiceAutomationServerEndpoint'.");
        }
        this.httpClient = new HTTPClient(serverEndpoint);
    }

    public void load(final Voice voice) throws ClientOperationException {
        // If the com.se2automate.voice.clientresources.voice is instantiated from text, set its url first, then ask the server to load it.
        // If the com.se2automate.voice.clientresources.voice is instantiated from url, directly ask the server to load it.
        VoiceAutomationMessage message;
        String voiceUrl;

        if (voice.getUrl() == null) {
            // use voiceRss service here and make an url here
            String voiceText = voice.getText();
            Language targetedLanguage = voice.getVoiceLanguage();

            if (voiceText == null) {
                throw new ClientOperationException("Voice text is null, please initialise the Voice object with text and language");
            }
            if (targetedLanguage == null) {
                targetedLanguage = Language.ENGLISH_US;
            }
            //Now create the URL -
            String apikey = System.getProperty("VoiceRssKey");
            if (apikey != null) {

                this.voiceRSSUrlClient = new VoiceRSSUrlClient(apikey);
            } else {
                throw new ClientOperationException("Please specify VoiceRss cloud service api key by passing JVM argument 'VoiceRssKey'.");
            }
            voiceUrl = voiceRSSUrlClient.getVoiceUrl(voiceText, targetedLanguage);
            LOG.info("Voice url is " + voiceUrl);
            try {
                voice.setUrl(new URL(voiceUrl));
            } catch (MalformedURLException e) {
                throw new ClientOperationException("URL got from Voice RSS service is malformed.", e);
            }

        } else {// com.se2automate.voice.clientresources.voice is instantiated from url
            voiceUrl = voice.getUrl().toString();
        }

        message = httpClient.load(voiceUrl);
        LOG.info("com.se2automate.voice.clientresources.voice file path " + message.getVoiceFilePath());
        if (message.getStatus() != VoiceAutomationMessageStatus.SUCCESS) {
            throw new ClientOperationException(message.getMessage());
        }

        voice.setFilename(message.getVoiceFilePath());
    }

    public void play(final Voice voice) throws ClientOperationException {
        VoiceAutomationMessage message = httpClient.play(voice.getFilename());
        if (message.getStatus() != VoiceAutomationMessageStatus.SUCCESS) {
            throw new ClientOperationException(message.getMessage());
        }
    }

    public byte[] record(final long duration) throws ClientOperationException {
        VoiceAutomationMessage message = httpClient.record(duration);
        if (message.getStatus() != VoiceAutomationMessageStatus.SUCCESS) {
            throw new ClientOperationException(message.getMessage());
        } else {
            return message.getAudioData();
        }
    }
}
