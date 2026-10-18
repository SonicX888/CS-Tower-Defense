package com.mycompany.cs_tower_defense;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 *
 * @author kyllianlim
 */

public class Sound {
    private Clip clip;

    public Sound(String resourcePath) {
        try {
            InputStream raw = getClass().getResourceAsStream(resourcePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(raw)
            );
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null) {
            return;
        }
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        if (clip == null) {
            return;
        }
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(float decibels) {
        FloatControl gain = (FloatControl) clip.getControl(
                FloatControl.Type.MASTER_GAIN
        );
        gain.setValue(decibels);
    }
}
