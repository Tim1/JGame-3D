package de.timweb.Test3D.shape;

import java.awt.Graphics;

import de.timweb.Test3D.graphics.Graphics3D;
import de.timweb.Test3D.graphics.Renderable;
import de.timweb.Test3D.math.Vector3d;

public class Path3D extends Shape3D implements Renderable {
	private Vector3d[] points;

	public Path3D(Vector3d... points) {
		this.points = points;
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < points.length - 1; i++) {
			Graphics3D.drawLine3D(points[i], points[i + 1], g);
		}
	}

	@Override
	public void move(double x, double y, double z) {
		Vector3d move = new Vector3d(x, y, z);
		for(int i=0;i<points.length;i++){
			points[i].add(move);
		}
	}

}
