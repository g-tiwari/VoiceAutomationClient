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

import co.gauravtiwari.voice.design.Language;

import java.io.File;
import java.net.URL;

/**
 * created by Gaurav Tiwari
 * <p>
 * Voice class used to wrap all metadata of the com.se2automate.voice.clientresources.voice.
 */
public class Voice {

    private URL url;
    private String text;
    private Language language;
    private String filename;
    private String voiceName = "";

    /**
     * Constructor for Voice object.
     *
     * @param url - URL object
     */
    public Voice(final URL url) {
        this.url = url;
    }

    /**
     * Constructor for Voice object.
     *
     * @param File - URL object
     */
    public Voice(final File filePath) {
        //this.url = url;
        this.filename = filePath.getName();
    }

    /**
     * Constructor for Voice object.
     *
     * @param text - text of the com.se2automate.voice.clientresources.voice
     */
    public Voice(final String text) {
        this.text = text;
    }

    /**
     * Constructor for Voice object.
     *
     * @param text - text of the com.se2automate.voice.clientresources.voice
     */
    public Voice(final String text, final Language language) {
        this.text = text;
        this.language = language;
    }

    /**
     * Getter for url field.
     *
     * @return - URL object
     */
    public URL getUrl() {
        return url;
    }

    /**
     * Setter for url field.
     *
     * @param url - URL object
     */
    public void setUrl(final URL url) {
        this.url = url;
    }

    /**
     * Getter for text field.
     *
     * @return - text of the com.se2automate.voice.clientresources.voice
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text field.
     *
     * @param text - text of the com.se2automate.voice.clientresources.voice
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Getter for filename field.
     *
     * @return - filename of the com.se2automate.voice.clientresources.voice
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for filename field.
     *
     * @param filename - filename of the com.se2automate.voice.clientresources.voice
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    /**
     * Getter for voiceName field.
     *
     * @return - voiceName of the com.se2automate.voice.clientresources.voice
     */
    public String getVoiceName() {
        return voiceName;
    }

    /**
     * Setter for voiceName field.
     *
     * @param voiceName - voiceName of the com.se2automate.voice.clientresources.voice
     */
    public void setVoiceName(final String voiceName) {
        this.voiceName = voiceName;
    }

    /**
     * Getter for voiceLanguage field.
     *
     * @return - voiceLanguage of the com.se2automate.voice.clientresources.voice
     */
    public Language getVoiceLanguage() {
        return language;
    }

    /**
     * Setter for voiceLanguage field.
     *
     * @param language - voiceLanguage of the com.se2automate.voice.clientresources.voice
     */
    public void setVoiceLanguage(final Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return String.format("Voice with text: \"%s\" %nfilename: \"%s\" %nvoiceName: \"%s\" ", this.text, this.filename, this.voiceName);
    }

}
