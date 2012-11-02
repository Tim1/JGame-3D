package de.timweb.Test3D.math;

import java.awt.Point;
import java.awt.Polygon;

import de.timweb.Test3D.entity.Player;
import de.timweb.Test3D.game.Test3DCanvas;

/**
 * Used to convert Point in the GameWorld to the Screen
 * 
 * @author Tim
 * 
 */
public class Converter3D {
	public static final Point pointNotOnScreen = new Point(Integer.MIN_VALUE,
			Integer.MIN_VALUE);

	/**
	 * Calculates where a Point in the Gameworld is on the Screen
	 * 
	 * @param worldPoint
	 *            Point in the Gameworld
	 * @return the Point on Screen or [Integer.MIN_VALUE,Integer.MIN_VALUE] if
	 *         Point is behind Players Screencanvas
	 */
	public static Point getPoint(Vector3d worldPoint) {
		if (checkIfBehindScreen(worldPoint)) {
			return pointNotOnScreen;
		}
		Plane3D screencanvas = Player.p.getScreencanvas();
		Vector3d screennormal = Player.p.getScreencanvas().getNormalVector();
		Vector3d eyepos = Player.p.getEyepos();

		// The intersectionpoint of Plane and virtual Line to Players eye
		Vector3d intersect = screencanvas.intersect(Line3D.getByTwoPoints(
				eyepos, worldPoint));

		Vector3d normalMultiply = screennormal.copy().multiply(
				-screencanvas.distanceToMinus(eyepos));

		// The middle of the Screencanvas (relativ to Players Eyeposition)
		Vector3d midSceencanvas = eyepos.copy().add(normalMultiply);

		Vector3d xIntersect = new Vector3d(intersect.x, 0, intersect.z);
		Vector3d xmidScreen = new Vector3d(midSceencanvas.x, 0,
				midSceencanvas.z);

		Vector3d yIntersect = new Vector3d(0, intersect.y, 0);
		Vector3d ymidScreen = new Vector3d(0, midSceencanvas.y, 0);

		// Plane parallel to Y-Axis (Up-Down Axis)
		Plane3D xPlane = Plane3D.getByPointAndLine(eyepos, Line3D
				.getByTwoPoints(midSceencanvas,
						midSceencanvas.copy().add(0, 1, 0)));

		// Plane parallel to XZ-Plane (Up-Down Axis)
		Plane3D yPlane = Plane3D.getByPointAndLine(eyepos, Line3D
				.getByTwoPoints(midSceencanvas,
						midSceencanvas.copy().add(0, 1, 0)));

		// Distance from middle of Screen
		double dx = xPlane.distanceToMinus(xIntersect);
		double dy = (midSceencanvas.y -intersect.y) * 400 /Player.DISTANCE_TO_SCREEN;

		intersect.x += Test3DCanvas.WIDTH / 2 - midSceencanvas.x;
		intersect.y = Test3DCanvas.HEIGHT / 2 - intersect.y - midSceencanvas.y;
		// intersect.x += Test3DCanvas.WIDTH / 2 - Player.p.getEyepos().x;
		// intersect.y = Test3DCanvas.HEIGHT / 2 - intersect.y
		// - Player.p.getEyepos().y;

		//
		return new Point((int) (dx + Test3DCanvas.WIDTH / 2),
				(int) (dy + Test3DCanvas.HEIGHT / 2));
	}

	/**
	 * Calculates the Intersection between the Line (point1 -> point2 ) and
	 * Screencanvas Use this, if one Point is behind the the Screencanvas
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 *             if both Points are on the same Side of the screencanvas (Line
	 *             does not intersect with Screencanvas)
	 */

	public static Point getPoint(Vector3d point1, Vector3d point2) {
		if (checkIfBehindScreen(point1) == checkIfBehindScreen(point2))
			throw new IllegalArgumentException(
					"The Line MUST cross the Screencanvas: Both given Points were on the same side!");

		Vector3d intersect = Player.p.getScreencanvas().intersect(
				Line3D.getByTwoPoints(point1, point2));

		// System.out.println(pointOnScreen.x() + "\t" + pointOnScreen.y());
		return getPoint(intersect);
	}

	/**
	 * @deprecated Because it does not work if some Points are behind the
	 *             Screencanvas
	 * @param points
	 * @return
	 */
	@Deprecated
	public static Polygon getPolygon(Vector3d... points) {
		Polygon polygon = new Polygon();

		for (Vector3d v : points) {
			Point p = getPoint(v);
			polygon.addPoint(p.x, p.y);
		}

		return polygon;
	}

	/**
	 * 
	 * @param point
	 * @return true if behind the Screen
	 */
	private static boolean checkIfBehindScreen(Vector3d point) {
		return Player.p.getScreencanvas().distanceToMinus(point) < -0.001;
	}
}
