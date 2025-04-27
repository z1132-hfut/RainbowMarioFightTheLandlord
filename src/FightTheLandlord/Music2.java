package FightTheLandlord;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class Music2 {
    public Music2() throws FileNotFoundException, JavaLayerException {
        Player player1;//用于播放
        BufferedInputStream name = new BufferedInputStream(new FileInputStream("music/BGM5.mp3"));
        player1 =new Player(name);
        player1.play();
    }

}
