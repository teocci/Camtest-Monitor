package net.kseek.cammonitor.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.*;

import net.kseek.cammonitor.data.DataListener;
import net.kseek.cammonitor.io.SocketServer;

public class ServerUI extends JLabel implements DataListener {
    private LinkedList<BufferedImage> queue = new LinkedList<BufferedImage>();
    private static final int MAX_BUFFER = 15;

    BufferedImage image, lastFrame;

    @Override
    public void paint(Graphics g) {
        synchronized (queue) {
            if (queue.size() > 0) {
                lastFrame = queue.poll();
            }
        }
        if (lastFrame != null) {
            g.drawImage(lastFrame, 0, 0, null);
        } else if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public ServerUI(int port) {
        SocketServer server = new SocketServer(port);
        server.setOnDataListener(this);
        server.start();
    }

    private void updateUI(BufferedImage bufferedImage) {
        synchronized (queue) {
            if (queue.size() == MAX_BUFFER) {
                lastFrame = queue.poll();
            }
            queue.add(bufferedImage);
        }

        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (image == null) {
            return new Dimension(960, 720); // init window size
        } else {
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }

    @Override
    public void onDirty(BufferedImage bufferedImage) {
        // TODO Auto-generated method stub
        updateUI(bufferedImage);
    }
}
