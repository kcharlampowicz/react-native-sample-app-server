package io.netbulls.tools;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageTool {
	/**
	 * Rescale image
	 *
	 * @param input input image
	 * @param width desired image width
	 * @param height desired image height
	 *
	 * @return new image as byte array
	 */
	public static byte[] resizeImage(byte[] input, int width, int height) {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();
			 ByteInputStream is = new ByteInputStream(input, input.length)) {
			Image tmpImage = ImageIO.read(is);
			Image scaled = tmpImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaled, 0, 0, new Color(0, 0, 0), null);
			ImageIO.write(imageBuff, "png", os);
			return os.toByteArray();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
