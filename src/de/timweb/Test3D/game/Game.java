package de.timweb.Test3D.game;

import java.awt.Graphics;

import de.timweb.Test3D.controls.Controls;

public class Game {
	public static final Game g = new Game();

	private Game() {

	}

	public void update(int delta) {
		Controls.c.update(delta);
		
		World.w.update(delta);
	}


	public void render(Graphics g) {
		World.w.render(g);
		
		Gui.g.render(g);
	}

}
