package se.stensatter.alfred;

import com.sun.deploy.panel.JavaPanel;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import javafx.geometry.VerticalDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainSwing extends JFrame {

    JPanel panel1;
    public MainSwing()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout());
        NewGame();
    }
    public void NewGame()
    {
        JLabel SizeLabel = new JLabel("SIZE");
        panel1.add(SizeLabel);
        JTextField size = new JTextField();
        panel1.add(size);
        JLabel speedlabel = new JLabel("speed");
        panel1.add(speedlabel);
        JComboBox speed = new JComboBox();
        speed.addItem("1");
        speed.addItem("2");
        speed.addItem("3");
        panel1.add(speed);

        JButton Start = new JButton("Start");
        panel1.add(Start);
        add(panel1);
        setResizable(true);
        DisplayGraphics m = new DisplayGraphics();
        m.mainSwing = this;
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setSize(Integer.valueOf(width.getText()), Integer.valueOf(hight.getText()));

                panel1.removeAll();
                validate();
                repaint();
                m.setFocusable(true);
                m.pixelSize = Integer.parseInt(size.getText())*10;
                m.speed = getSpeed(speed);
                m.Start();

                panel1.add(m);
                add(panel1);
                m.requestFocusInWindow();
                revalidate();
            }
        });
    }
    private long getSpeed(JComboBox comboBox)
    {
        long speed=50;
        if(comboBox.getSelectedItem().equals("2"))
        {
            speed = 100;
        }
        if(comboBox.getSelectedItem().equals("3"))
        {
            speed = 50;
        }
        if(comboBox.getSelectedItem().equals("1"))
        {
            speed = 200;
        }


        return speed;
    }
}
