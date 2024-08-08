import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener{

    JButton button;
    JLabel label;

    
    Frame() {        
        ImageIcon icon = new ImageIcon("facepalm.png");
        label = new JLabel();
        label.setIcon(icon);
        label.setBounds(200, 200, 900, 700);
        label.setVisible(false);
        
        button = new JButton();
        button.setBounds(100, 100, 300, 30);
        button.setText("Cadastrar");
        button.setFocusable(false);
        button.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(900, 700);
        this.setVisible(true);
        //this.getContentPane().setBackground(Color.darkGray);
        this.add(button);
        this.add(label);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("pinto");
        //button.setEnabled(false);
        if(e.getSource()==button){
            label.setVisible(true);
        }
    }
}