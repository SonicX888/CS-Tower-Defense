package com.mycompany.cs_tower_defense;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class that loads and plays a sound
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Sound {

    // audio clip of the sound
    private Clip clip;

    /**
     * Loads a sound from the resources of the project.
     *
     * @param resourcePath path of the sound file in the resources
     */
    public Sound(String resourcePath) {
        try {
            // gets the sound file from the resources
            InputStream raw = getClass().getResourceAsStream(resourcePath);
            // converts the file into an audio stream
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(raw)
            );
            // creates the clip and loads the audio in it
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            // prints the error if the sound could not be loaded
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound from the beginning.
     */
    public void play() {
        // does nothing if the sound could not be loaded
        if (clip == null) {
            return;
        }
        // restarts the sound from the beginning
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Plays the sound again and again without stopping.
     */
    public void loop() {
        // does nothing if the sound could not be loaded
        if (clip == null) {
            return;
        }
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the sound.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
