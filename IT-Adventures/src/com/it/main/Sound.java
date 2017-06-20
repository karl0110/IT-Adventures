package com.it.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {

	public void playSound(String path){
		try{	
		AudioInputStream audioInputStream =
		    AudioSystem.getAudioInputStream(this.getClass().getResource(path));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
