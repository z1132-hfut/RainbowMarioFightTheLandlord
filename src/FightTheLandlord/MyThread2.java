package FightTheLandlord;


import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;

public class MyThread2 extends Thread{

    public void run(){
        try {
            new Music2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}