import javax.swing.*;
public class optionPane {
    JFrame f;
    optionPane(){
        JFrame f=new JFrame();
        String name=JOptionPane.showInputDialog(f,"Enter Name");
    }
    public static void main(String[] args) {
        new optionPane();
    }
}