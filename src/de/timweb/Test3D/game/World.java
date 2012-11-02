package de.timweb.Test3D.game;

import java.awt.Color;
import java.awt.Graphics;

import de.timweb.Test3D.entity.Player;
import de.timweb.Test3D.graphics.Graphics3D;
import de.timweb.Test3D.math.Converter3D;
import de.timweb.Test3D.math.Line3D;
import de.timweb.Test3D.math.Vector3d;
import de.timweb.Test3D.shape.Cube3D;
import de.timweb.Test3D.shape.Raster3D;

public class World {
	public final static World w = new World();
	public Color redTrans = new Color(255, 0, 0, 40);
	public Color yellowTrans = new Color(255, 255, 0, 60);

	Cube3D cube0 = new Cube3D(new Vector3d(-1000, -500, 1000), 1000);
	Cube3D cube1 = new Cube3D(new Vector3d(1000, -500, 1000), 1000);
	private Raster3D raster3d;

	private World() {
		raster3d = new Raster3D(new Vector3d(0, -200, 0), 10, 100);
	}

	public void update(int delta) {

	}

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		raster3d.render(g);

		g.setColor(Color.green);
		Vector3d point3 = new Vector3d(0, 0, 1000);
		Graphics3D.drawPoint3D(point3, g);

		g.setColor(Color.yellow);
		Vector3d point = new Vector3d(0, 100, 1000);
		Graphics3D.drawPoint3D(point, g);

		g.setColor(Color.red);
		Vector3d point2 = new Vector3d(0, 200, 1000);
		Graphics3D.drawPoint3D(point2, g);

		System.out.println(Player.p.getScreencanvas());

		// g.setColor(Color.red);
		// Graphics3D.drawLine3D(new Vector3d(-500,-200,0), new
		// Vector3d(-500,-200,500), g);
		// Graphics3D.drawPoint3D(new Vector3d(-500,-200,0), g);
		// System.out.println(Player.p.getEyepos());
		// Graphics3D.drawPoint3D(new Vector3d(0,100,100), g);

		// g.setColor(Color.green);
		// Graphics3D.drawPoint3D(new Vector3d(100,0,100), g);
		for (int i = 0; i < 3000; i++)
			cube0.render(g);
		// //
		// cube1.render(g);

		// Vector3d intersect =
		// Player.p.getScreencanvas().intersect(Line3D.getByTwoPoints(point,
		// Player.p.getEyepos()));

	}

}
