import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class GraphicUI extends JFrame{
    int spacing = 5;
    GraphicUI(){
        this.setTitle("MINESWEEPER_V");
        this.setSize(1286,829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Land board = new Land();
        this.setContentPane(board);

        Move cMove= new Move();
        this.addMouseListener(1);
    }

    private void addMouseListener(int i) {
    }

    public class Land extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,1200,800);
            g.setColor(Color.RED);
            for(int i=0;i<16;i++){
                for(int j=0;j<9;j++){
                    g.fillRect(spacing+i*80,spacing+j*80+80,80-2*spacing,80-2*spacing);
                }
            }
        }
    }

}

class Click implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
