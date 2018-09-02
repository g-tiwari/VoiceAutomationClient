[![badge-jdk](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![License badge](https://img.shields.io/badge/license-Apache2-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
<a href="https://twitter.com/se2automate"><img src="https://github.com/johan/svg-cleanups/blob/master/logos/twitter.svg" height="30" width="30" ></a>
<a href="https://selenium2automate.wordpress.com/"><img src="https://github.com/Automattic/social-logos/blob/master/svg-min/wordpress.svg" height="30" width="30"  ></a>

# VoiceAutomationClient
Client for sending request to VoiceServer for playing files on the machine where voiceautomationserver is running.

VoiceAutomationClient is an open source, released under the terms of [Apache 2.0 License]. It is used along with VoiceAutomationServer

## Purpose

Lets understand the purpose of having this VoiceAutomationClient and VoiceAutomationServer. So for simulating voice, either we record the voice commands in mp3 or wav file and then play them to mimic voice for our voice software
or we use a text to speech library to get our voice files and then play them. But here are some challenges in that - 
1) Storing audio files on the targeted machine makes it hard to make the solution more scalable
2) no portability 
3) Need to record files for each new test data
4) Need additional mechanism to handle audio ports if multiple devices are connected
5) We might end up using PSexec or similar software for triggering playback on the remote machine if automation scripts are on the different machine

## How my solution helping

So here we have a Client-Server model which is making the voice playback easy and portable across all the automation machines. 
VoiceAutomationServer has the ability to download the recorded audio file(over internet) to the machine where it is running and also communicate to a TTS service too and download the audio file on the targeted machine
VoiceAutomationClient resides on the machine from where automation scripts are triggered. 
VoiceAutomationClient send the request to voice automation server for either converting the text in to speech or download the internet audio file on the machine where server is running. 
Then client send the request to play that file on the server


## Pre-requisite

You have to use this library along with VoiceAutomationServer Standalone jar. 
Download the Standalone jar from [VoiceAutomationServer-Jar](https://github.com/g-tiwari/VoiceAutomationServer/blob/master/VoiceAutomationServer-1.0.0.jar)
Then start the server from terminal by typing 
```bash
java -jar VoiceAutomationServer-1.0.0.jar
```

After this you will see something similar to below - 

```bash
 INFO [main] (VoiceAutomationServer.java:119) - Initializing Voice Automation server
Jul 15, 2018 11:50:50 PM com.sun.jersey.api.core.PackagesResourceConfig init
INFO: Scanning for root resource and provider classes in the packages:
  com.se2automate.voice.serverresources
Jul 15, 2018 11:50:50 PM com.sun.jersey.api.core.ScanningResourceConfig logClasses
INFO: Root resource classes found:
  class com.se2automate.voice.serverresources.VoiceAutomationServerAPI
Jul 15, 2018 11:50:50 PM com.sun.jersey.api.core.ScanningResourceConfig logClasses
INFO: Provider classes found:
  class com.se2automate.voice.serverresources.JSONParseExceptionMapper
Jul 15, 2018 11:50:50 PM com.sun.jersey.server.impl.application.WebApplicationImpl _initiate
INFO: Initiating Jersey application, version 'Jersey: 1.17 01/17/2013 03:31 PM'
 INFO [main] (VoiceAutomationServer.java:128) - Voice Automation server started at http://192.168.1.3:9090/

```


## Usage

In order to use VoiceAutomationClient in a Maven project, you need to add the following dependency in your `pom.xml` (Java 8 or upper required):

```xml
<dependency>
    <groupId>co.gauravtiwari.voice</groupId>
    <artifactId>VoiceAutomationClient</artifactId>
    <version>1.0.0</version>
</dependency>
```

VoiceAutomationClient is typically used by tests, and therefore, the typical scope would be *test* (`<scope>test</scope>`).

Once we have included this dependency, you can let VoiceAutomationClient to manage the com.se2automate.voice.clientresources.voice play request on the server. Take a look at this below Sample Test

```java
public class VoiceTest {
    public static void main(String[] args){
        textToSpeechTest();
        playInternetVoiceFile();
    }

    public static void textToSpeechTest(){
        final Logger LOG = LoggerFactory.getLogger(VoiceTest.class);
        //Start the server on your target machine by command java -jar VoiceAuotmationServer.jar
        System.setProperty("VoiceAutomationServerEndpoint","<VoiceAutomationServerIP:Port>");
        //Signup to voicerss and get your api key
        System.setProperty("VoiceRssKey","<VoiceRSSAPIKEy>");
        Voice voice = new Voice("Hey Google, whats the current time", Language.ENGLISH_US);

        try {
            VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
            voiceAutomationClient.load(voice);
            voiceAutomationClient.play(voice);
            LOG.info(voice.getFilename());
            LOG.info(voice.getText());
            LOG.info(voice.getVoiceName());
            LOG.info(voice.getVoiceLanguage().toString());
        }catch (ClientOperationException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void playInternetVoiceFile(){
        final Logger LOG = LoggerFactory.getLogger(VoiceTest.class);
        //Start the server on your target machine by command java -jar VoiceAuotmationServer.jar
        System.setProperty("VoiceAutomationServerEndpoint","<VoiceAutomationServerIP:Port>");
        //Signup to voicerss and get your api key
        System.setProperty("VoiceRssKey","<VoiceRSSAPIKEy>");
        Voice voice = new Voice("http://www.pacdv.com/sounds/voices/thank-god-its-friday.wav");

        try {
            VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
            voiceAutomationClient.load(voice);
            voiceAutomationClient.play(voice);
            LOG.info(voice.getFilename());
            LOG.info(voice.getText());
            LOG.info(voice.getVoiceName());
            LOG.info(voice.getVoiceLanguage().toString());
        }catch (ClientOperationException e){
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

```

1. It send a request to get the voice file from the TTS API or directly from the url
2. It downloads voice wav file on the target machine and save it with some random name in temp folder
3. Then It plays the file on the machine where server is running, on specified audio port

## Help

If you have questions on how to use VoiceAutomationClient properly with a special configuration or suchlike, please consider asking a question on [Github Queries].

## About

VoiceAutomationClient (Copyright &copy; 2018) is a project created by [Gaurav Tiwari] and licensed under the terms of the [Apache 2.0 License]. Comments, questions and suggestions are always very [welcome][VoiceAutomationClient issues]!

[Apache HTTP Client]: https://hc.apache.org/httpcomponents-client-ga/
[Apache HTTP Client logging practices]: https://hc.apache.org/httpcomponents-client-ga/logging.html
[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[authenticated requests]: https://developer.github.com/v3/#rate-limiting
[Gaurav Tiwari]: http://g-tiwari.github.io/
[GitHub account]: https://github.com/settings/tokens
[GitHub Repository]: https://github.com/g-tiwari/VoiceAutomationClient
[VoiceAutomationClient issues]: https://github.com/g-tiwari/VoiceAutomationClient/issues
[VoiceAutomationServer issues]: https://github.com/g-tiwari/VoiceAutomationServer/issues
