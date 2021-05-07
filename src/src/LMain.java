import javax.swing.*;
import java.awt.*;

public class LMain implements Runnable {

    GraphicUI app = new GraphicUI();

    public void main(String[] args) {
        new Thread(new LMain()).start();
    }

    @Override
    public void run() {
        while (true) {
            app.repaint();
        }
    }
}
    class Land extends JPanel {
        public void paintComp(Graphics g){

        }
    }
