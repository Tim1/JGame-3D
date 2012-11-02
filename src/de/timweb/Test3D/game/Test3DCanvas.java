package de.timweb.Test3D.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;

import de.timweb.Test3D.controls.ControlListener;
import de.timweb.Test3D.entity.Player;
import de.timweb.Test3D.util.ImageLoader;

public class Test3DCanvas extends Canvas implements Runnable {
	public static int WIDTH;
	public static int HEIGHT;

	public static long currentFPS;
	public static final int FPS_TARGET = 120;
	public static final int DELTA_TARGET = 1000 / FPS_TARGET;

	public Test3DCanvas(int width, int height, int border) {
		WIDTH = width;
		HEIGHT = height;

		Dimension dim = new Dimension(width - border, height - border);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.addKeyListener(ControlListener.c);
		this.addMouseListener(ControlListener.c);
		this.addMouseMotionListener(ControlListener.c);

		//Initialisierung
		ImageLoader.init();
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		long delta = 0;

		while (true) {
			long start = System.currentTimeMillis();

			update((int) delta);
			BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				continue;
			}
			render(bs.getDrawGraphics());

			if (bs != null)
				bs.show();

			long timepassed = System.currentTimeMillis() - start;
			if (timepassed < DELTA_TARGET) {
				try {
					Thread.sleep(DELTA_TARGET - timepassed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			delta = System.currentTimeMillis() - start;
			if (delta != 0)
				currentFPS = 1000 / delta;
		}

	}

	private void update(int delta) {
		Game.g.update(delta);
	}

	private void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		Game.g.render(g);

		renderDebug(g);
		
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	private void renderDebug(Graphics g) {
		g.setColor(Color.red);
		g.setFont(getFont());
		g.drawString("FPS: " + currentFPS, WIDTH - 50, 20);
		
		g.setColor(Color.white);
		g.drawString(Player.p.getEyepos().toString(), 5, 15);
		
		DecimalFormat df = new DecimalFormat("#000.000");
		g.drawString(df.format(Player.p.getScreencanvas().getAngle())+"°", 5, 35);
		
	}

}
