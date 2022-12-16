import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloDemo extends JFrame{
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JButton button1;

    public HelloDemo() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1,textArea1.getText());
                System.out.println(textArea1.getText());
            }
        });
    }

    public static void main(String[] args) {
        HelloDemo h= new HelloDemo();
        h.setContentPane(h.mainPanel);
        h.setTitle("CE");
        h.setSize(300,400);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
