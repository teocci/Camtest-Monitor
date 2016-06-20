package net.kseek.cammonitor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



/**
 * Created by teocci on 6/20/16.
 */
public class CamtestMonitor extends JFrame{
    GridLayout gl = new GridLayout(2, 3);

    public CamtestMonitor(String name) {
        super(name);
        setResizable(false);
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel jp = new JPanel();
        jp.setLayout(gl);

        //Set up components preferred size
        jp.add(new ServerUI(8888));
        jp.add(new ServerUI(8880));
        jp.setPreferredSize(new Dimension(1920, 1080));

        pane.add(jp, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        CamtestMonitor f = new CamtestMonitor("Monitor");

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Set up the content pane.
        f.addComponentsToPane(f.getContentPane());
        //Display the window.
        f.pack();
        f.setVisible(true);
    }
}
