package de.timweb.Test3D.math;

/**
 * square Matrix
 * 
 * <pre>
 * x1 | x2 | x3 
 * y1 | y2 | y3 
 * z1 | z2 | z3
 * </pre>
 * 
 * @author Tim
 * 
 */
public class Matrix3D {
	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
	public static final int AXIS_Z = 2;

	public double x1;
	public double x2;
	public double x3;
	public double y1;
	public double y2;
	public double y3;
	public double z1;
	public double z2;
	public double z3;

	public Matrix3D(double x1, double x2, double x3, double y1, double y2,
			double y3, double z1, double z2, double z3) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.z1 = z1;
		this.z2 = z2;
		this.z3 = z3;
	}

	/**
	 * Generates a RotationMatrix for the given Angle and Axis for the rotation
	 * 
	 * @param angle
	 *            the angle of the Rotation (in Radian)
	 * @param axis
	 *            AXIS_X, AXIS_Y or AXIS_Z
	 * @return the RotationMatrix
	 */
	public static Matrix3D getRotationMatrix(double angle, int axis) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);

		switch (axis) {
		case AXIS_X:
			return new Matrix3D(1, 0, 0, 0, cos, -sin, 0, sin, cos);
		case AXIS_Y:
			return new Matrix3D(cos, 0, sin, 0, 1, 0, -sin, 0, cos);
		case AXIS_Z:
			return new Matrix3D(cos, -sin, 0, sin, cos, 0, 0, 0, 1);
		default:
			throw new IllegalArgumentException(
					"Illegal Axis, must be AXIS_X, AXIS_Y or AXIS_Z");
		}
	}

	@Override
	public String toString() {
		String str1 = x1 + "\t| " + x2 + "\t| " + x3 + "\n";
		String str2 = y1 + "\t| " + y2 + "\t| " + y3 + "\n";
		String str3 = z1 + "\t| " + z2 + "\t| " + z3;

		return str1+str2+str3;
	}
	
	public static void main(String[] args) {
		System.out.println(getRotationMatrix(Math.toRadians(90), AXIS_X));
		System.out.println();
		System.out.println(getRotationMatrix(Math.toRadians(90), AXIS_Y));
		System.out.println();
		System.out.println(getRotationMatrix(Math.toRadians(90), AXIS_Z));
	}
}
