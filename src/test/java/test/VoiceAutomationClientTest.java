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
package test;


import co.gauravtiwari.voice.client.VoiceAutomationClient;
import co.gauravtiwari.voice.clientresources.ClientOperationException;
import co.gauravtiwari.voice.clientresources.Voice;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class VoiceAutomationClientTest {

    @Test
    public void testVoiceInstantiatedFromText() {
        Voice voice = new Voice("Hello World!");
        Assert.assertNotNull(voice.getText());
        Assert.assertNull(voice.getUrl());
        Assert.assertNull(voice.getFilename());
    }

    @Test
    public void testVoiceInstantiatedFromUrl() throws MalformedURLException {
        Voice voice = new Voice(new URL("http://abcd.bar"));
        Assert.assertNotNull(voice.getUrl());
        Assert.assertNull(voice.getText());
        Assert.assertNull(voice.getFilename());
    }

    @Test(expectedExceptions = ClientOperationException.class)
    public void testInstantiateVAClientWithoutVAServerEndpoint() throws ClientOperationException {
        System.clearProperty("VoiceAutomationServerEndpoint");
        VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient(); // jvm arg VAServerEndpoint not set
    }
}
