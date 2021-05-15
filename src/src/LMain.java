

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class LMain extends JFrame {

    private JLabel statusbar;
    public static int[] fieldData;
    public static int[] fieldRead;
    public static int minesData;
    public static int minesRead;
    public static int timeData;
    public static int timeRead;
    public String flag;
    JLabel timebar;
    JMenu optionsmenu;
    JRadioButtonMenuItem[] options = new JRadioButtonMenuItem[4];
    JMenuBar menubar;
    public LMain() {

        initUI();
    }


    private void initUI() {
        statusbar = new JLabel("");
        timebar = new JLabel("Time Remaining");
        add(statusbar, BorderLayout.SOUTH);
        add(timebar, BorderLayout.NORTH);
        if(flag==null) {
            System.out.println("loading ..");
            add(new Base(statusbar, timebar));
        }else if(flag.equals("save")){
            Base.flagG="save";
            System.out.println("saving ..");
            add(new Base(statusbar, timebar));
        }else if(flag.equals("load")){
            Base.flagG="load";
            add(new Base(statusbar, timebar));
        }

        setResizable(false);
        menubar = new JMenuBar();
        optionsmenu = new JMenu("File");
        options[0] = new JRadioButtonMenuItem("New", false);options[0].setMnemonic(KeyEvent.VK_N);
        options[0].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        options[0].addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                flag="";
                Base.flagG="";
                dispose();
                var ex = new LMain();

                ex.setVisible(true);
            }
        });
        options[1] = new JRadioButtonMenuItem("Open", false);options[1].setMnemonic(KeyEvent.VK_O);
        options[1].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        options[1].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                flag="load";
                Base.flagG="load";
                JFrame parentFrame = new JFrame();

                String name=JOptionPane.showInputDialog(parentFrame,"Enter Name");
                 if (name != null) {
                    dbConnect.dbFlag="load";
                    dbConnect.plaLoad=name;

                    try {
                        //saveDds readObj = (saveDds) timerSt.load(fileToOpen.getAbsolutePath());
                        //fieldRead= readObj.fieldMapArray;
                        //minesRead= readObj.mineDS;
                        //timeRead= readObj.timeDS;

                        dbConnect ab= new dbConnect();
                        ab.getRemoteConnection();
                        fieldRead=dbConnect.fie;
                        System.out.println(dbConnect.fie);
                        timeRead=dbConnect.tim;
                        minesRead=dbConnect.min;
                        System.out.println(minesRead+": "+timeRead);
                        dispose();
                        var ex = new LMain();

                        ex.setVisible(true);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.err.println("Cannot Load the data");
                    }
                }
            }
        });
        options[2] = new JRadioButtonMenuItem("Save", false);options[2].setMnemonic(KeyEvent.VK_S);
        options[2].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        options[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag="save";
                JFrame parentFrame = new JFrame();

                String name=JOptionPane.showInputDialog(parentFrame,"Enter Name");

                if (name != null) {
                    //saveDds dataA=new saveDds();
                    //dataA.fieldMapArray=fieldData;
                    //dataA.mineDS=minesData;
                    //dataA.timeDS=timeData;
                    //String fileToSave = name;
                    dbConnect.fie= fieldData;
                    System.out.println(dbConnect.fie);
                    dbConnect.min=minesData;
                    dbConnect.tim=timeData;
                    System.out.println(name);
                    dbConnect.pla=name;
                    try {
                        //timerSt.save(dataA,fileToSave.getAbsolutePath());
                        dbConnect.dbFlag="save";
                        dbConnect a= new dbConnect();
                        a.getRemoteConnection();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.err.println("Unable to dave the file");
                    }
                    System.out.println("Save as file: " +name);
                }
            }
        });
        options[3] = new JRadioButtonMenuItem("Exit", false);options[3].setMnemonic(KeyEvent.VK_X);
        options[3].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        options[3].addActionListener(ev -> System.exit(0));
        optionsmenu.add(options[0]);optionsmenu.add(options[1]);optionsmenu.add(options[2]);optionsmenu.add(options[3]);
        menubar.add(optionsmenu);
        setJMenuBar(menubar);
        pack();
        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new LMain();

            ex.setVisible(true);
        });
    }
}
