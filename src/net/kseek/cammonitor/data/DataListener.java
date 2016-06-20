package net.kseek.cammonitor.data;

import java.awt.image.BufferedImage;

public interface DataListener {
	void onDirty(BufferedImage bufferedImage);
}
