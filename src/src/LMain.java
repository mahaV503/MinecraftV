

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class LMain extends JFrame {

    private JLabel statusbar;
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

        add(new GraphicUI(statusbar,timebar));


        setResizable(false);
        menubar = new JMenuBar();
        optionsmenu = new JMenu("File");
        options[0] = new JRadioButtonMenuItem("New", false);options[0].setMnemonic(KeyEvent.VK_N);
        options[0].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        options[1] = new JRadioButtonMenuItem("Open", false);options[1].setMnemonic(KeyEvent.VK_O);
        options[1].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        options[2] = new JRadioButtonMenuItem("Save", false);options[2].setMnemonic(KeyEvent.VK_S);
        options[2].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        options[3] = new JRadioButtonMenuItem("Exit", false);options[3].setMnemonic(KeyEvent.VK_X);
        options[3].setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
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
