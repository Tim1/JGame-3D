package de.timweb.Test3D.graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import de.timweb.Test3D.entity.Player;
import de.timweb.Test3D.math.Converter3D;
import de.timweb.Test3D.math.Vector3d;

public class Graphics3D {

	public static void drawPoint3D(Vector3d point, Graphics g) {
		drawPoint3D(point, 8, g);
	}

	public static void drawPoint3D(Vector3d point, int size, Graphics g) {
		Point p = Converter3D.getPoint(point);

		g.drawOval(p.x, p.y, size, size);
	}

	public static void drawPolygon3D(Graphics g, Vector3d... points) {
		Polygon polyon = Converter3D.getPolygon(points);

		g.drawPolygon(polyon);
	}

	public static void drawLine3D(Vector3d v1, Vector3d v2, Graphics g) {
		Point p1 = Converter3D.getPoint(v1);
		Point p2 = Converter3D.getPoint(v2);

		// both Points not on Screen -> draw no Line
		if (p1.equals(Converter3D.pointNotOnScreen)
				&& p2.equals(Converter3D.pointNotOnScreen))
			return;

		// If one Point is behind the Screen, get a Point on the Screen to draw
		// the line
		if (p2.equals(Converter3D.pointNotOnScreen)) {
			p2 = Converter3D.getPoint(v1, v2);
		} else if (p1.equals(Converter3D.pointNotOnScreen)) {
			p1 = Converter3D.getPoint(v1, v2);
		}

		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

}
