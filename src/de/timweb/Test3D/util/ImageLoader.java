package de.timweb.Test3D.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.timweb.Test3D.Test3DMain;

public class ImageLoader {
	public static BufferedImage point_red;
	public static BufferedImage point_green;
	public static BufferedImage point_blue;
	
	public static BufferedImage empty_16;

	public static void init() {
		point_red = loadImage("point_red.png");
		point_green = loadImage("point_green.png");
		point_blue = loadImage("point_blue.png");
		
		empty_16 = loadImage("empty_16.png");
	}

	private static BufferedImage loadImage(String img) {
		try {
			return ImageIO.read(Test3DMain.class.getResource("/" + img));
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"The Image you tried to load was not found!", e);
		}
	}
}
