
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;

public class Base extends JPanel{

    Var variables = new Var();
     Image[] img;
     int diffT;
     int[] nyBours;
    public static int[] nxBours;
    public static boolean resseter=false,flagger=false;
     JLabel minebar,timebar_;
     long createdMillis;
    boolean exitTime=true;

    int ncols=16;
    Thread t;
    int time=1000;
    public static String flagG="";

    private void setGame() {

        setPreferredSize(new Dimension(256,256));
        img=variables.setIMGS();
        nyBours = variables.setField();
        addMouseListener(new click());
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
            variables.setSeter(true);
            variables.setMinesLeft(LMain.minesRead);
            variables.setAllCells(256);
            variables.setField();
            minebar.setText(Integer.toString(LMain.minesRead));

            nxBours =LMain.fieldRead;
        }else {
            variables.setSeter(true);
            variables.setMinesLeft(40);
            variables.setAllCells(256);
            variables.setField();
            minebar.setText(Integer.toString(variables.getMinesLeft()));
            nyBours = variables.setField();
            nxBours = variables.emptyFILLS(nyBours);
            //System.out.println(nxBours.length);
        }
        //While func

    }

    private void space(int j) {

        int current_col = j % ncols;
        int ij;
        if (current_col > 0) {
            ij = j - ncols - 1;
            if (ij >= 0) {
            if (nxBours[ij] > 9) {
                nxBours[ij] -= 10;
                if (nxBours[ij] ==0) {
                    space(ij);
                    resseter=true;}}}

        ij = j - 1;
        if (ij >= 0) {
            if (nxBours[ij] > 9) {
                nxBours[ij] -= 10;
                if (nxBours[ij] == 0) {
                    space(ij);}}}

            ij = j + ncols - 1;
            if (ij < variables.getAllCells()) {
                if (nxBours[ij] > 9) {
                    nxBours[ij] -= 10;
                if (nxBours[ij] == 0) {
                    space(ij);}
                }
            }}

        ij = j - ncols;
        if (ij >= 0) {
            if (nxBours[ij] > 9) {
                nxBours[ij] -= 10;
            if (nxBours[ij] == 0) {
                space(ij);
            }
            }
        }

        ij = j + ncols;
        if (ij < variables.getAllCells()) {
            if (nxBours[ij] >9) {
                nxBours[ij] -=10;
                resseter=true;
        if (nxBours[ij] ==0) {
            space(ij);
        }}}

        if (current_col < (ncols - 1)) {
            ij = j -ncols+ 1;
            if (ij >= 0) {
                if (nxBours[ij] >9) {
                    nxBours[ij] -=10;
            if (nxBours[ij] ==0) {
                space(ij);
                flagger=true;
            }}}
            ij = j + ncols + 1;
            if (ij <variables.getAllCells()) {
                if (nxBours[ij] >9) {
                    nxBours[ij] -=10;
            if (nxBours[ij] ==0) {
                space(ij);
                resseter=true;
            }
                }}

            ij = j + 1;
            if (ij <variables.getAllCells()) {
                if (nxBours[ij] >9) {
                    nxBours[ij] -=10;
            if (nxBours[ij] ==0) {
                space(ij);
                flagger=true;
            }
                    resseter=false;
                }}
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;

        for (int i = 0; i <16; i++) {
            for (int j = 0; j <ncols; j++) {
                int ijPos = nxBours[(i *ncols) + j];
                if (variables.isSeter() && ijPos ==9) {
                    variables.setSeter(false);
                }
            if (!variables.isSeter()) {
            if (ijPos ==19) {
                    ijPos =9;
            } else if (ijPos ==29) {
                ijPos = 11;
            } else if (ijPos >19) {
                ijPos = 12;
            } else if (ijPos >9) {
                ijPos = 10;
            }
            } else {
                if (ijPos >19) {
                        ijPos =11;
                    } else if (ijPos >9) {
                        ijPos =10;
                        uncover++;
                    }}
                g.drawImage(img[ijPos], (j *15),
                        (i * 15), this);
            }
        }

        if (uncover == 0 && variables.isSeter()) {
            variables.setSeter(false);
            minebar.setText("Game won");
        } else if (!variables.isSeter()) {
            minebar.setText("Game lost");
        }
    }

    private class click extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x /15;
            int cRow = y /15;

            boolean doRepaint = false;

            if (!variables.isSeter()) {

                newGame();
                repaint();
            }

            if ((x <ncols *15) && (y <16 *15)) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (nxBours[(cRow *ncols) + cCol] >9) {

                        doRepaint = true;

                        if (nxBours[(cRow *ncols) + cCol] <=19) {

                            if (variables.getMinesLeft() > 0) {
                                nxBours[(cRow *ncols) + cCol] +=10;
                                variables.setMinesLeft(variables.getMinesLeft()-1);

                                String msg = Integer.toString(variables.getMinesLeft());
                                minebar.setText(msg);
                            } else {
                                minebar.setText("No marks left");
                            }
                        } else {

                            nxBours[(cRow *ncols) + cCol] -=10;
                            variables.setMinesLeft(variables.getMinesLeft()+1);

                            String msg = Integer.toString(variables.getMinesLeft());
                            minebar.setText(msg);
                        }
                    }

                } else {

                    if (nxBours[(cRow *ncols) + cCol] >19) {

                        return;
                    }

                    if ((nxBours[(cRow *ncols) + cCol] >9)
                            && (nxBours[(cRow *ncols) + cCol] <29)) {

                        nxBours[(cRow *ncols) + cCol] -=10;
                        doRepaint = true;

                        if (nxBours[(cRow *ncols) + cCol] ==9) {
                            variables.setSeter(false);
                        }

                        if (nxBours[(cRow *ncols) + cCol] ==0) {
                            space((cRow *ncols) + cCol);
                        }
                    }
                }

                if (doRepaint) {
                    LMain.fieldData= nxBours;
                    System.out.println(Arrays.toString(nxBours));
                    LMain.minesData= variables.getMinesLeft();
                    LMain.timeData =time-stopTime();
                    repaint();
                }
            }
        }
    }

    public Base(JLabel minebar,JLabel timebar_) {

        this.minebar = minebar;
        this.timebar_=timebar_;

        setGame();
    }

}
