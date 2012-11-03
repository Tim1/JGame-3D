package de.timweb.Test3D.shape;

import java.awt.Color;
import java.awt.Graphics;

import de.timweb.Test3D.graphics.Graphics3D;
import de.timweb.Test3D.graphics.Renderable;
import de.timweb.Test3D.math.Vector3d;

public class Cube3D extends Shape3D implements Renderable{
	private static final long serialVersionUID = 8643464514238648769L;
	private Vector3d front0;
	private Vector3d front1;
	private Vector3d front2;
	private Vector3d front3;

	private Vector3d back0;
	private Vector3d back1;
	private Vector3d back2;
	private Vector3d back3;

	/**
	 * <pre>
	 * Points in Cube:
	 *    _______
	 *   /      /|
	 *  3------2 |
	 *  |      | |
	 *  |      |/
	 *  0------1
	 * </pre>
	 */

	public Cube3D(Vector3d origin, int size){
		this(size);
		
		move(origin);
	}
	
	public void move(Vector3d origin) {
		front0.add(origin);
		front1.add(origin);
		front2.add(origin);
		front3.add(origin);
		
		back0.add(origin);
		back1.add(origin);
		back2.add(origin);
		back3.add(origin);
	}

	public Cube3D(int size) {
		int s = size;
		
		front0 = new Vector3d(0, 0, 0);
		front1 = new Vector3d(s, 0, 0);
		front2 = new Vector3d(s, s, 0);
		front3 = new Vector3d(0, s, 0);

		back0 = new Vector3d(0, 0, s);
		back1 = new Vector3d(s, 0, s);
		back2 = new Vector3d(s, s, s);
		back3 = new Vector3d(0, s, s);
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		Graphics3D.drawLine3D(back0, back1, g);
		Graphics3D.drawLine3D(back1, back2, g);
		Graphics3D.drawLine3D(back2, back3, g);
		Graphics3D.drawLine3D(back3, back0, g);

		// Seitliche Seite
		g.setColor(Color.green);
		Graphics3D.drawLine3D(front0, back0, g);
		Graphics3D.drawLine3D(front1, back1, g);
		Graphics3D.drawLine3D(front2, back2, g);
		Graphics3D.drawLine3D(front3, back3, g);

		// Vordere Seite des Würfels
		// g.setColor(yellowTrans);
		// polygon = getPolygonOnScreen(point1,point2,point3,point4);
		// g.fillPolygon(polygon);

		g.setColor(Color.yellow);
		Graphics3D.drawLine3D(front0, front1, g);
		Graphics3D.drawLine3D(front1, front2, g);
		Graphics3D.drawLine3D(front2, front3, g);
		Graphics3D.drawLine3D(front3, front0, g);
	}

	@Override
	public void move(double x, double y, double z) {
		move(new Vector3d(x, y, z));
	}
}
