
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GraphicUI extends JPanel {

    GraphicUIvar variables = new GraphicUIvar();
    private Image[] img;
    private int[] field;
    private final JLabel statusbar;

    public GraphicUI(JLabel statusbar) {

        this.statusbar = statusbar;
        initBoard();
    }

    private void initBoard() {

        setPreferredSize(new Dimension(variables.getBOARD_WIDTH(), variables.getBOARD_HEIGHT()));

        variables.setIMGS(img);
        variables.setField(field);

        newGame();
    }

    private void newGame() {

        variables.setInGame(true);
        variables.setMinesLeft(variables.getN_MINES());
        variables.setAllCells(variables.getN_COLS() * variables.getN_COLS());
        variables.setField(field);
        statusbar.setText(Integer.toString(variables.getMinesLeft()));
        variables.emptyFILLS(field);
        //While func

    }
}