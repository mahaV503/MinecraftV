

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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
            add(new GraphicUI(statusbar, timebar));
        }else if(flag.equals("save")){
            GraphicUI.flagG="save";
            System.out.println("saving ..");
            add(new GraphicUI(statusbar, timebar));
        }else if(flag.equals("load")){
            GraphicUI.flagG="load";
            add(new GraphicUI(statusbar, timebar));
        }

        setResizable(false);
        menubar = new JMenuBar();
        optionsmenu = new JMenu("File");
        options[0] = new JRadioButtonMenuItem("New", false);options[0].setMnemonic(KeyEvent.VK_N);
        options[0].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        options[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
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
                GraphicUI.flagG="load";
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(parentFrame);
                if (result == JFileChooser.APPROVE_OPTION) {saveDds dataA=new saveDds();
                    dataA.fieldMapArray=GraphicUI.field;
                    File fileToOpen = fileChooser.getSelectedFile();
                    dbConnect.dbFlag="load";
                    dbConnect.plaLoad=fileToOpen.getAbsolutePath();

                    try {
                        //saveDds readObj = (saveDds) timerSt.load(fileToOpen.getAbsolutePath());
                        //fieldRead= readObj.fieldMapArray;
                        //minesRead= readObj.mineDS;
                        //timeRead= readObj.timeDS;
                        new dbConnect();
                        fieldRead=dbConnect.fie;
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

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    saveDds dataA=new saveDds();
                    //dataA.fieldMapArray=fieldData;
                    //dataA.mineDS=minesData;
                    //dataA.timeDS=timeData;
                    File fileToSave = fileChooser.getSelectedFile();
                    dbConnect.fie=fieldData;
                    dbConnect.min=minesData;
                    dbConnect.tim=timeData;
                    dbConnect.pla=fileToSave.getAbsolutePath();
                    try {
                        //timerSt.save(dataA,fileToSave.getAbsolutePath());
                        dbConnect.dbFlag="save";
                        dbConnect a= new dbConnect();
                        a.getRemoteConnection();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.err.println("Unable to dave the file");
                    }
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
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
