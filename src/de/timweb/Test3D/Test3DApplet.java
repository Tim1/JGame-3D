package de.timweb.Test3D;

import java.applet.Applet;

import de.timweb.Test3D.game.Test3DCanvas;


public class Test3DApplet extends Applet{
	private static final long serialVersionUID = 62473324232776428L;

	@Override
	public void init() {
		super.init();
		Test3DCanvas game = new Test3DCanvas(getWidth(),getHeight(),0);
		add(game);
		game.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		System.exit(0);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.exit(0);
	}
}
