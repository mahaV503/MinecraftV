
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GraphicUI extends JPanel{

    GraphicUIvar variables = new GraphicUIvar();
    private Image[] img;
    private int diffT;
    private int[] field_;
    public static int[] field;
    private final JLabel statusbar;
    private final JLabel timebar_;
    private long createdMillis;
    boolean exitTime=true;

    Thread t;
    int time=1000;
    public static String flagG="";
    public GraphicUI(JLabel statusbar,JLabel timebar_) {

        this.statusbar = statusbar;
        this.timebar_=timebar_;

        initGraphicUI();
    }

    private void initGraphicUI() {

        setPreferredSize(new Dimension(variables.getBOARD_WIDTH(), variables.getBOARD_HEIGHT()));

        img=variables.setIMGS();
        field_= variables.setField();
        addMouseListener(new MinesAdapter());
        newGame();
    }



    public int stopTime(){


                long nowMillis = System.currentTimeMillis();
                diffT=(int)((nowMillis - this.createdMillis) / 1000);
                System.out.println(nowMillis+": "+this.createdMillis);

        //System.out.println(1000-diffT);
        return diffT;

    }

    private void newGame() {
        createdMillis = System.currentTimeMillis();
        if(flagG.equals("load")){
            time=LMain.timeRead;
        }
        t = new Thread() {
            public void run() {

                while(stopTime()<time && exitTime){
                    timebar_.setText("Time Remaining: "+(time-stopTime()));
                    //System.out.println(stopTime());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        //System.out.println(createdMillis);
        if (flagG.equals("load")){
            variables.setInGame(true);
            variables.setMinesLeft(LMain.minesRead);
            variables.setAllCells(variables.getN_COLS()*variables.getN_COLS());
            variables.setField();
            statusbar.setText(Integer.toString(LMain.minesRead));

            field=LMain.fieldRead;
        }else {
            variables.setInGame(true);
            variables.setMinesLeft(variables.getN_MINES());
            variables.setAllCells(variables.getN_COLS() * variables.getN_COLS());
            variables.setField();
            statusbar.setText(Integer.toString(variables.getMinesLeft()));
            field_ = variables.setField();
            field = variables.emptyFILLS(field_);
            //System.out.println(field.length);
        }
        //While func

    }

    private void find_empty_cells(int j) {

        int current_col = j % variables.getN_COLS();
        int cell;

        if (current_col > 0) {
            cell = j - variables.getN_COLS() - 1;
            if (cell >= 0) {
                if (field[cell] > variables.getMINE_CELL()) {
                    field[cell] -= variables.getCOVER_FOR_CELL();
                    if (field[cell] == variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell] > variables.getMINE_CELL()) {
                    field[cell] -= variables.getCOVER_FOR_CELL();
                    if (field[cell] == variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + variables.getN_COLS() - 1;
            if (cell < variables.getAllCells()) {
                if (field[cell] > variables.getMINE_CELL()) {
                    field[cell] -= variables.getCOVER_FOR_CELL();
                    if (field[cell] == variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - variables.getN_COLS();
        if (cell >= 0) {
            if (field[cell] > variables.getMINE_CELL()) {
                field[cell] -= variables.getCOVER_FOR_CELL();
                if (field[cell] == variables.getEMPTY_CELL()) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + variables.getN_COLS();
        if (cell < variables.getAllCells()) {
            if (field[cell] >variables.getMINE_CELL()) {
                field[cell] -=variables.getCOVER_FOR_CELL();
                if (field[cell] ==variables.getEMPTY_CELL()) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (variables.getN_COLS() - 1)) {
            cell = j -variables.getN_COLS() + 1;
            if (cell >= 0) {
                if (field[cell] >variables.getMINE_CELL()) {
                    field[cell] -=variables.getCOVER_FOR_CELL();
                    if (field[cell] ==variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + variables.getN_COLS() + 1;
            if (cell <variables.getAllCells()) {
                if (field[cell] >variables.getMINE_CELL()) {
                    field[cell] -=variables.getCOVER_FOR_CELL();
                    if (field[cell] ==variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell <variables.getAllCells()) {
                if (field[cell] >variables.getMINE_CELL()) {
                    field[cell] -=variables.getCOVER_FOR_CELL();
                    if (field[cell] ==variables.getEMPTY_CELL()) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;

        for (int i = 0; i <variables.getN_ROWS(); i++) {

            for (int j = 0; j <variables.getN_COLS(); j++) {

                int cell = field[(i *variables.getN_COLS()) + j];

                if (variables.isInGame() && cell ==variables.getMINE_CELL()) {

                    variables.setInGame(false);
                }

                if (!variables.isInGame()) {

                    if (cell ==variables.getCOVERED_MINE_CELL()) {
                        cell =variables.getDRAW_MINE();
                    } else if (cell ==variables.getMARKED_MINE_CELL()) {
                        cell = variables.getDRAW_MARK();
                    } else if (cell >variables.getCOVERED_MINE_CELL()) {
                        cell = variables.getDRAW_WRONG_MARK();
                    } else if (cell >variables.getMINE_CELL()) {
                        cell = variables.getDRAW_COVER();
                    }

                } else {

                    if (cell >variables.getCOVERED_MINE_CELL()) {
                        cell =variables.getDRAW_MARK();
                    } else if (cell >variables.getMINE_CELL()) {
                        cell =variables.getDRAW_COVER();
                        uncover++;
                    }
                }

                g.drawImage(img[cell], (j *variables.getCELL_SIZE()),
                        (i * variables.getCELL_SIZE()), this);
            }
        }

        if (uncover == 0 && variables.isInGame()) {
            variables.setInGame(false);

            statusbar.setText("Game won");

        } else if (!variables.isInGame()) {
            statusbar.setText("Game lost");
        }
    }

    private class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x /variables.getCELL_SIZE();
            int cRow = y /variables.getCELL_SIZE();

            boolean doRepaint = false;

            if (!variables.isInGame()) {

                newGame();
                repaint();
            }

            if ((x <variables.getN_COLS() *variables.getCELL_SIZE()) && (y <variables.getN_ROWS() *variables.getCELL_SIZE())) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (field[(cRow *variables.getN_COLS()) + cCol] >variables.getMINE_CELL()) {

                        doRepaint = true;

                        if (field[(cRow *variables.getN_COLS()) + cCol] <=variables.getCOVERED_MINE_CELL()) {

                            if (variables.getMinesLeft() > 0) {
                                field[(cRow *variables.getN_COLS()) + cCol] +=variables.getMARK_FOR_CELL();
                                variables.setMinesLeft(variables.getMinesLeft()-1);

                                String msg = Integer.toString(variables.getMinesLeft());
                                statusbar.setText(msg);
                            } else {
                                statusbar.setText("No marks left");
                            }
                        } else {

                            field[(cRow *variables.getN_COLS()) + cCol] -=variables.getMARK_FOR_CELL();
                            variables.setMinesLeft(variables.getMinesLeft()+1);

                            String msg = Integer.toString(variables.getMinesLeft());
                            statusbar.setText(msg);
                        }
                    }

                } else {

                    if (field[(cRow *variables.getN_COLS()) + cCol] >variables.getCOVERED_MINE_CELL()) {

                        return;
                    }

                    if ((field[(cRow *variables.getN_COLS()) + cCol] >variables.getMINE_CELL())
                            && (field[(cRow *variables.getN_COLS()) + cCol] <variables.getMARKED_MINE_CELL())) {

                        field[(cRow *variables.getN_COLS()) + cCol] -=variables.getCOVER_FOR_CELL();
                        doRepaint = true;

                        if (field[(cRow *variables.getN_COLS()) + cCol] ==variables.getMINE_CELL()) {
                            variables.setInGame(false);
                        }

                        if (field[(cRow *variables.getN_COLS()) + cCol] ==variables.getEMPTY_CELL()) {
                            find_empty_cells((cRow *variables.getN_COLS()) + cCol);
                        }
                    }
                }

                if (doRepaint) {
                    LMain.fieldData=field;
                    System.out.println(Arrays.toString(field));
                    LMain.minesData= variables.getMinesLeft();
                    LMain.timeData =time-stopTime();
                    repaint();
                }
            }
        }
    }
}
