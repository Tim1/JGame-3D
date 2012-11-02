package de.timweb.Test3D;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.timweb.Test3D.game.Test3DCanvas;
import de.timweb.Test3D.util.ImageLoader;

public class Test3DMain {

	public static final JFrame frame = new JFrame("Test3D");;

	public static void main(String[] args) {
		Test3DCanvas evocanvas = new Test3DCanvas(1024, 600, 10);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(evocanvas);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);

		evocanvas.setFocusable(true);
		evocanvas.requestFocus();

//		hideCurser();
//		frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		evocanvas.start();

	}

	public static void hideCurser() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLoader.empty_16,
				new Point(), "transparent");
		frame.setCursor(c);
	}
	public static void showCurser() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
}
