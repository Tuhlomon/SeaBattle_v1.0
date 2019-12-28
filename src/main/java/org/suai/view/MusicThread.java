package org.suai.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicThread extends Thread{
    public void run(){
        try{
            File file = new File("C:\\Users\\Tuhlomon\\Desktop\\spaceship battle\\bg.wav");
            AudioInputStream ais =  AudioSystem.getAudioInputStream(file);
            Clip audio = AudioSystem.getClip();
            audio.open(ais);
            audio.setFramePosition(0);
            audio.loop(Clip.LOOP_CONTINUOUSLY);
            audio.start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
