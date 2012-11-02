package de.timweb.Test3D.math;

import java.text.DecimalFormat;

public class Vector3d {
	public double x, y, z;

	public static final Vector3d AXIS_X = new Vector3d(1, 0, 0);
	public static final Vector3d AXIS_Y = new Vector3d(0, 1, 0);
	public static final Vector3d AXIS_Z = new Vector3d(0, 0, 1);

	public Vector3d() {
		this(0, 0, 0);
	}

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int x() {
		return (int) x;
	}

	public int y() {
		return (int) y;
	}

	public int z() {
		return (int) z;
	}

	public Vector3d add(Vector3d other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
		return this;
	}

	public Vector3d add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3d multiply(double factor) {
		this.x *= factor;
		this.y *= factor;
		this.z *= factor;
		return this;
	}

	public Vector3d copy() {
		return new Vector3d(x, y, z);
	}

	public void set(Vector3d position) {
		x = position.x;
		y = position.y;
		z = position.z;
	}

	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distance(Vector3d other) {
		double dx = Math.abs(x - other.x);
		double dy = Math.abs(y - other.y);
		double dz = Math.abs(z - other.z);

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public double distance(double x, double y, double z) {
		double dx = Math.abs(this.x - x);
		double dy = Math.abs(this.y - y);
		double dz = Math.abs(this.z - z);

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.000");

		return "[" + df.format(x) + ", \t" + df.format(y) + ", \t"
				+ df.format(z) + "]";
	}

	/**
	 * 
	 * @return The Difference between the two Points
	 */
	public static Vector3d difference(Vector3d point1, Vector3d point2) {
		return new Vector3d(point1.x - point2.x, point1.y - point2.y, point1.z
				- point2.z);
	}

	public static Vector3d crossProduct(Vector3d vector1, Vector3d vector2) {
		Vector3d result = new Vector3d();

		result.x = vector1.y * vector2.z - (vector2.y * vector1.z);
		result.y = vector1.z * vector2.x - (vector2.z * vector1.x);
		result.z = vector1.x * vector2.y - (vector2.x * vector1.y);

		return result;
	}

	/**
	 * Negate a Vector<br/>
	 * ([1,1,1] becomes [-1,-1,-1])
	 * 
	 * @return
	 */
	public Vector3d negate() {
		multiply(-1);

		return this;
	}

}
