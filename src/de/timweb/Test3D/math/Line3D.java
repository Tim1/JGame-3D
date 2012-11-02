package de.timweb.Test3D.math;

public class Line3D {
	private Vector3d origin;
	private Vector3d direction;

	/**
	 * Use Fabrik methods to create Line3D Objects
	 * 
	 * @param origin
	 * @param direction
	 */
	public Line3D(Vector3d origin, Vector3d direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public Vector3d getOrigin() {
		return origin;
	}
	
	public Vector3d getDirection() {
		return direction;
	}
	
	public static Line3D getByTwoPoints(Vector3d point1, Vector3d point2) {
		return new Line3D(point1.copy(), Vector3d.difference(point1, point2));
	}
	
	public static Line3D getByDirection(Vector3d origin, Vector3d direction){
		return new Line3D(origin, direction);
	}
}
