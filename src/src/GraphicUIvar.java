import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class GraphicUIvar extends JPanel {

    private final int NUM_IMAGES = 13;
    private final int CELL_SIZE = 15;
    private final int COVER_FOR_CELL = 10;
    private final int MARK_FOR_CELL = 10;
    private final int EMPTY_CELL = 0;
    private final int MINE_CELL = 9;
    private final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
    private final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

    private final int DRAW_MINE = 9;
    private final int DRAW_COVER = 10;
    private final int DRAW_MARK = 11;
    private final int DRAW_WRONG_MARK = 12;

    private final int N_MINES = 40;
    private final int N_ROWS = 16;
    private final int N_COLS = 16;

    private final int BOARD_WIDTH = N_COLS * CELL_SIZE + 1;
    private final int BOARD_HEIGHT = N_ROWS * CELL_SIZE + 1;


    private boolean inGame;
    private int[] fieldVar;
    private int minesLeft;
    private Image[] imgVar;

    private int allCells;

    public Image[] setIMGS() {
        imgVar = new Image[NUM_IMAGES];

        for (int i = 0; i < NUM_IMAGES; i++) {

            var path = "src/minesweepertiles/" + i + ".png";
            imgVar[i] = (new ImageIcon(path)).getImage();
        }
        return imgVar;
    }
    public int[] setField(){
        fieldVar = new int[allCells];

        for (int i = 0; i < allCells; i++) {

            fieldVar[i] = COVER_FOR_CELL;
        }
        return fieldVar;
    }

    public int[] emptyFILLS(int[] fieldVar_){
        int i=0;
        int cell;
        var random = new Random();
        while (i <  N_MINES) {

            int position = (int) (allCells * random.nextDouble());
            //System.out.println(fieldVar_);
                    //System.out.println(allCells);
            if ((position < allCells)
                    && (fieldVar_[position] != COVERED_MINE_CELL)) {

                int current_col = position % N_COLS;
                fieldVar_[position] = COVERED_MINE_CELL;
                i++;

                if (current_col > 0) {
                    cell = position - 1 - N_COLS;
                    if (cell >= 0) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }

                    cell = position + N_COLS - 1;
                    if (cell < allCells) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }
                }

                cell = position - N_COLS;
                if (cell >= 0) {
                    if (fieldVar_[cell] != COVERED_MINE_CELL) {
                        fieldVar_[cell] += 1;
                    }
                }

                cell = position + N_COLS;
                if (cell < allCells) {
                    if (fieldVar_[cell] != COVERED_MINE_CELL) {
                        fieldVar_[cell] += 1;
                    }
                }

                if (current_col < (N_COLS - 1)) {
                    cell = position - N_COLS + 1;
                    if (cell >= 0) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }
                    cell = position + N_COLS + 1;
                    if (cell < allCells) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }
                    cell = position + 1;
                    if (cell < allCells) {
                        if (fieldVar_[cell] != COVERED_MINE_CELL) {
                            fieldVar_[cell] += 1;
                        }
                    }
                }
            }
        }
        return fieldVar_;
    }


    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void setMinesLeft(int minesLeft) {
        this.minesLeft = minesLeft;
    }



    public void setAllCells(int allCells) {
        this.allCells = allCells;
    }

    public int getNUM_IMAGES() {
        return NUM_IMAGES;
    }

    public int getCELL_SIZE() {
        return CELL_SIZE;
    }

    public int getCOVER_FOR_CELL() {
        return COVER_FOR_CELL;
    }

    public int getMARK_FOR_CELL() {
        return MARK_FOR_CELL;
    }

    public int getEMPTY_CELL() {
        return EMPTY_CELL;
    }

    public int getMINE_CELL() {
        return MINE_CELL;
    }

    public int getCOVERED_MINE_CELL() {
        return COVERED_MINE_CELL;
    }

    public int getMARKED_MINE_CELL() {
        return MARKED_MINE_CELL;
    }

    public int getDRAW_MINE() {
        return DRAW_MINE;
    }

    public int getDRAW_COVER() {
        return DRAW_COVER;
    }

    public int getDRAW_MARK() {
        return DRAW_MARK;
    }

    public int getDRAW_WRONG_MARK() {
        return DRAW_WRONG_MARK;
    }

    public int getN_MINES() {
        return N_MINES;
    }

    public int getN_ROWS() {
        return N_ROWS;
    }

    public int getN_COLS() {
        return N_COLS;
    }

    public int getBOARD_WIDTH() {
        return BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }



    public boolean isInGame() {
        return inGame;
    }

    public int getMinesLeft() {
        return minesLeft;
    }


    public int getAllCells() {
        return allCells;
    }
}
