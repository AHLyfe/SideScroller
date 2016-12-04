package Game.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
	MediaPlayer mediaPlayer;
	Media media;
	
	
	public Sound(String filepath){
		setMedia(filepath);	
		mediaPlayer = new MediaPlayer(media);
	}
	
	public void setMedia(String filepath){
		media = new Media(filepath);
	}
	
	public void pause(){
		mediaPlayer.pause();
	}
}
