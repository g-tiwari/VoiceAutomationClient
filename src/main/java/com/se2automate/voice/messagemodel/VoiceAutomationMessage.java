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

package com.se2automate.voice.messagemodel;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * created by Gaurav Tiwari
 */

/**
 * A POJO that is used when the server serializes and deserializes
 * data to/from json. When the server receives an HTTP request, it
 * uses jackson to convert the json body an instance of this class.
 * When the server sends an HTTP response, it uses jackson to convert
 * an instance of this class to json.
 * <p>
 * status - status of the request, whether it succeeded or not [SUCCESS, FAIL]
 * message - contains the error message if the request failed
 * voiceFilePath - read in HTTP requests to play the com.se2automate.voice.clientresources.voice files
 * <p>
 * Setters and Getters are needed for jackson to serialize and
 * deserialize the object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoiceAutomationMessage {
    private VoiceAutomationMessageStatus status;
    private String message;
    private String voiceFilePath;
    private long recordingDuration;
    private byte[] audioData;

    /**
     * Default Constructor needed for jackson serialization.
     */
    public VoiceAutomationMessage() {
    }

    /**
     * Default Constructor needed for jackson serialization.
     *
     * @param status  - status of the request
     * @param message - the message
     */
    public VoiceAutomationMessage(final VoiceAutomationMessageStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Default Constructor needed for jackson serialization.
     *
     * @param status        - status of the request
     * @param message       - the message
     * @param voiceFilePath - filepath of the com.se2automate.voice.clientresources.voice
     */
    public VoiceAutomationMessage(final VoiceAutomationMessageStatus status, final String message, final String voiceFilePath) {
        this(status, message);
        this.voiceFilePath = voiceFilePath;
    }

    /**
     * Getter for status field.
     *
     * @return status
     */
    public VoiceAutomationMessageStatus getStatus() {
        return this.status;
    }

    /**
     * Setter for status field.
     *
     * @param status - status of the request
     */
    public void setStatus(final VoiceAutomationMessageStatus status) {
        this.status = status;
    }

    /**
     * Getter for message field.
     *
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Setter for message field.
     *
     * @param message - message if the request failed
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Getter for voiceFilePath field.
     *
     * @return voiceFilePath
     */
    public String getVoiceFilePath() {
        return this.voiceFilePath;
    }

    /**
     * Setter for voiceFilePath field.
     *
     * @param voiceFilePath - file paths or names to com.se2automate.voice.clientresources.voice files
     */
    public void setVoiceFilePath(final String voiceFilePath) {
        this.voiceFilePath = voiceFilePath;
    }

    /**
     * Getter for audioData field. Audio data is the recorded audio in byte[] format
     *
     * @return audioData
     */
    public byte[] getAudioData() {
        return this.audioData;
    }

    /**
     * Setter for audioData field
     *
     * @param audioData - audioData in byte[] of the recorded audio
     */
    public void setAudioData(final byte[] audioData) {
        this.audioData = audioData;
    }

    /**
     * Getter for recordingDuration field. Recording duration is in milliseconds
     *
     * @return recordingDuration
     */
    public long getRecordingDuration() {
        return this.recordingDuration;
    }

    /**
     * Setter for recordingDuration field. Recording duration is in milliseconds
     *
     * @param recordingDuration - the recording duration for the recording request in milliseconds
     */
    public void setRecordingDuration(final long recordingDuration) {
        this.recordingDuration = recordingDuration;
    }

    /**
     * convert contents of VoiceAutomationMessage in to string.
     *
     * @return string of the class fields
     */
    @Override
    public String toString() {
        return String.format("VoiceAutoMessage [VoiceAutomationMessageStatus=%s, Message=%s, VoiceFilePath=%s]",
                this.status, this.message, this.voiceFilePath);
    }
}
