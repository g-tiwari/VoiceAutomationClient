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

package co.gauravtiwari.voice.design;


import co.gauravtiwari.voice.clientresources.ClientOperationException;
import co.gauravtiwari.voice.clientresources.Voice;

/**
 * created by Gaurav Tiwari
 * <p>
 * Interface for Voice Automation client.
 */
public interface VoiceAutomationClientModel {

    /**
     * Call Voice automation server to load the com.se2automate.voice.clientresources.voice file. If url of the com.se2automate.voice.clientresources.voice object is not null,
     * send it to VoiceAutomation client;
     * else, first get url from Voice RSS service, then send it to VoiceAutomation client.
     *
     * @param voice - com.se2automate.voice.clientresources.voice object includes metadata of the com.se2automate.voice.clientresources.voice
     * @throws ClientOperationException - exception in loading com.se2automate.voice.clientresources.voice
     */
    void load(final Voice voice) throws ClientOperationException;

    /**
     * Request the server to play the com.se2automate.voice.clientresources.voice to device.
     *
     * @param voice - com.se2automate.voice.clientresources.voice to play
     * @throws ClientOperationException - exception in playing com.se2automate.voice.clientresources.voice
     */
    void play(final Voice voice) throws ClientOperationException;

    /**
     * Request the server to record the output from a certain mixer
     *
     * @param duration - the amount of time to record for
     * @return - returns the recorded audio in a byte array format
     * @throws ClientOperationException - exception in recording audio
     */
    byte[] record(final long duration) throws ClientOperationException;
}
