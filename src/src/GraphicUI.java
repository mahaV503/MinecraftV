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

    int vicX = -250;
    int vicY = 25;
    int vicX2 = -250;

    int[][] table = new int[16][12];
    int[][] nearby = new int[16][12];
    boolean[][] revealed = new boolean[16][12];
    boolean[][] flagged = new boolean[16][12];

    Random rand = new Random();

    boolean flagger = false;

    boolean happy = true;

    public void GameClass() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 12; j++) {
                table[i][j] = 0;
                nearby[i][j] = 0;
                revealed[i][j] = false;
                flagged[i][j] = false;
                if (rand.nextInt(100) < 20 && j > 1) {
                    table[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 12; j++) {
                for (int m = 0; m < 16; m++) {
                    for (int n = 0; n < 12; n++) {
                        if (Math.abs(i-m) <= 1 && Math.abs(j-n) <= 1 && (i != m || j != n) && table[m][n] == 1) {
                            nearby[i][j]++;
                        }
                    }
                }
            }
        }
    }
}
