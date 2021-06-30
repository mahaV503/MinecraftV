import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Var extends JPanel {


     boolean seter;
    //int[][] revealed;
     int[] revealed;
     int minesLeft;
// int[][] neighbours;
    // int[][] mines;
     int allCells;

    public Image[] setIMGS() {
        Image[] imgVar = new Image[13];

        for (int i = 0; i < 13; i++) {

            var path = "src/minesweepertiles/" + i + ".png";
            imgVar[i] = (new ImageIcon(path)).getImage();
        }
        return imgVar;
    }
    public int[] setField(){
        revealed = new int[allCells];

        for (int i = 0; i < allCells; i++) {

            revealed[i] = 10;
        }
        return revealed;
    }

    public int[] emptyFILLS(int[] fieldVar_){
        int i=0;
        int j;
        var random = new Random();
        while (i <  40) {

            int position = (int) (allCells * random.nextDouble());
            //System.out.println(fieldVar_);
                    //System.out.println(allCells);
            if ((position < allCells)
                    && (fieldVar_[position] != 19)) {

                int current_col = position % 16;
                fieldVar_[position] = 19;
                i++;

                if (current_col > 0) {
                    j = position - 1 - 16;
                    if (j >= 0) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }
                    j = position - 1;
                    if (j >= 0) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }

                    j = position + 16 - 1;
                    if (j < allCells) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }
                }

                j = position - 16;
                if (j >= 0) {
                    if (fieldVar_[j] != 19) {
                        fieldVar_[j] += 1;
                    }
                }

                j = position + 16;
                if (j < allCells) {
                    if (fieldVar_[j] != 19) {
                        fieldVar_[j] += 1;
                    }
                }

                if (current_col < (16 - 1)) {
                    j = position - 16 + 1;
                    if (j >= 0) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }
                    j = position + 16 + 1;
                    if (j < allCells) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }
                    j = position + 1;
                    if (j < allCells) {
                        if (fieldVar_[j] != 19) {
                            fieldVar_[j] += 1;
                        }
                    }
                }
            }
        }
        return fieldVar_;
    }


    public void setSeter(boolean seter) {
        this.seter = seter;
    }

    public void setMinesLeft(int minesLeft) {
        this.minesLeft = minesLeft;
    }



    public void setAllCells(int allCells) {
        this.allCells = allCells;
    }


    public boolean isSeter() {
        return seter;
    }

    public int getMinesLeft() {
        return minesLeft;
    }


    public int getAllCells() {
        return allCells;
    }
}
