package RGB.superMario;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/*public class MusicTools {
    private File fileBGm;
    private AudioClip acBgm;

    void    playMusicTools() {
        fileBGm = new File("bgm/12.wav");
        try {

            acBgm = Applet.newAudioClip(fileBGm.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void loogBgm() {
        acBgm.loop();
    }
}*/



import java.net.URI;

public class Music{
    private File f;
    private URI uri;
    private URL url;


    public void run() throws InterruptedException, MalformedURLException {
        for(int i=1;i<=31;i++){

            try{
                f = new File("music/BGMPart00"+i+".wav"); // 绝对路径
                uri = f.toURI();
                url = uri.toURL(); // 解析路径
                AudioClip aau;
                aau = Applet.newAudioClip(url);
                aau.play(); // 单曲循环
                System.out.println("");
                Thread.sleep(5000);
            }catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

}

