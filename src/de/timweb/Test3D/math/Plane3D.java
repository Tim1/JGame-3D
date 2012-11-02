package de.timweb.Test3D.math;

public class Plane3D {
	private Vector3d normal;
	private double offset;

	public Plane3D(Vector3d normal, double offset) {
		this.normal = normal;
		this.offset = offset;
	}

	public static Plane3D getByNormalAndOffset(Vector3d normal, double offset) {
		return new Plane3D(normal, offset);
	}

	public static Plane3D getByThreePoints(Vector3d point1, Vector3d point2,
			Vector3d point3) {
		Vector3d dir1 = Vector3d.difference(point1, point2);
		Vector3d dir2 = Vector3d.difference(point1, point3);

		Vector3d normal = Vector3d.crossProduct(dir1, dir2);
		double offset = normal.x * point3.x + normal.y * point3.y + normal.z
				* point3.z;

		return new Plane3D(normal, offset);
	}

	public static Plane3D getByPointAndLine(Vector3d point1, Line3D line) {
		return getByThreePoints(point1, line.getOrigin().copy(), line
				.getOrigin().copy().add(line.getDirection()));
	}

	public Vector3d intersect(Line3D line) {
		/**
		 * <pre>
		 * Example:
		 * 
		 * Plane3D 
		 * 1*x + 2*y + 3*z = 0
		 * 
		 * Line3D (h = Lamda)
		 * (1)					(3)
		 * (1)  	+  h	 *	(2)
		 * (1)					(1)
		 *  
		 * -->
		 * 1* (1+3h) + 2* (1+2h) + 3* (1+1h) = 0
		 * h = 1* 3 + 2* 2 + 3* 1
		 * leftside = 1* 1 + 2* 1 + 3* 1
		 * 
		 * </pre>
		 */

		Vector3d origin = line.getOrigin().copy();
		Vector3d dir = line.getDirection().copy();

		double lamda = normal.x * dir.x + normal.y * dir.y + normal.z * dir.z;
		double leftSide = normal.x * origin.x + normal.y * origin.y + normal.z
				* origin.z;

		double rightside = offset - leftSide;
		rightside = rightside / lamda;

		dir.multiply(rightside);
		origin.add(dir);
		return origin.copy();
	}

	/**
	 * 
	 * @return Angle to Vector [0,0,1] (in degree)
	 */
	public double getAngle() {
		// 1. Quadrant
		if (normal.x > 0 && normal.z > 0)
			return Math.toDegrees(Math.atan(normal.x / normal.z));

		// 2. Quadrant
		if (normal.x > 0 && normal.z < 0)
			return 180 + Math.toDegrees(Math.atan(normal.x / normal.z));

		// 3. Quadrant
		if (normal.x < 0 && normal.z < 0)
			return 180 + Math.toDegrees(Math.atan(normal.x / normal.z));

		// 4. Quadrant
		return 360 + Math.toDegrees(Math.atan(normal.x / normal.z));

	}

	public Vector3d getNormalVector() {
		return normal;
	}

	/**
	 * Is a Point before or behind a Plane
	 * 
	 * @param point
	 * @return true if it is behind the Plane, otherwise false
	 */
	public boolean isBehind(Vector3d point) {
		return distanceToMinus(point) < 0;
	}

	/**
	 * Distance to a Point (always >= 0)
	 * 
	 * @param point
	 * @return
	 */
	public double distanceTo(Vector3d point) {
		return Math.abs(distanceToMinus(point));
	}

	/**
	 * Distance to a Point, if behind the Plane its < 0
	 * 
	 * @param point
	 * @return
	 */
	public double distanceToMinus(Vector3d point) {
		double distance = (normal.x * point.x) + (normal.y * point.y)
				+ (normal.z * point.z) - offset;

		return distance;
	}

	public void addOffset(double d) {
		offset += d;
	}

	public double getOffset() {
		return offset;
	}

	/**
	 * Rotates a Plane3D around a Point for the given angle
	 * 
	 * @param point
	 *            The point to rotate around
	 * @param leftright
	 *            The angle to rotate (in degree)
	 */
	public void rotate(Vector3d point, double leftright) {
		double distance = distanceTo(point);

		normal.set(VectorUtils.mulitply(Matrix3D.getRotationMatrix(
				Math.toRadians(leftright), Matrix3D.AXIS_Y), normal));

		offset = distance + (normal.x * point.x) + (normal.y * point.y)
				+ (normal.z * point.z);

	}

	/**
	 * Moves the Plane3D by the given Vector
	 * 
	 * @param negate
	 */
	private void move(Vector3d vector) {
		double leftside = (vector.x * normal.x) + (vector.y * normal.y)
				+ (vector.z * normal.z);

		offset = leftside;
	}

	@Override
	public String toString() {
		return normal + " = " + offset;
	}
}
