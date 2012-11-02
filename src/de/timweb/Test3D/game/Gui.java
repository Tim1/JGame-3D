package de.timweb.Test3D.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Gui {
	public static final Gui g = new Gui();

	private Gui() {
	}

	public void render(Graphics g) {
//		g.setColor(Color.gray);
//		g.drawLine(0, Test3DCanvas.HEIGHT/2, Test3DCanvas.WIDTH, Test3DCanvas.HEIGHT/2);
//		g.drawLine(Test3DCanvas.WIDTH/2, 0, Test3DCanvas.WIDTH/2, Test3DCanvas.HEIGHT);

		
		g.setColor(Color.white);
		//-4 +4
		g.setFont(new Font("Arial", Test3DCanvas.WIDTH/2, 16));
		g.drawString("+", Test3DCanvas.WIDTH/2-4, Test3DCanvas.HEIGHT/2+6);
		
	}

	public void update(int delta) {

	}
}
