import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
public class LMain implements Runnable {

    //GraphicUI game = new GraphicUI();

    public static void main(String[] args) {
        (new Thread(new LMain())).start();
    }


    @Override
    public void run() {

    }
}