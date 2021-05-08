

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Java Minesweeper Game
 *
 * Author: Jan Bodnar
 * Website: http://zetcode.com
 */

public class LMain extends JFrame {

    private JLabel statusbar;

    public LMain() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new GraphicUI(statusbar));

        setResizable(false);
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
