package de.timweb.Test3D.shape;

import de.timweb.Test3D.math.Vector3d;

public interface Movable {
	/**
	 * Moves the Objects in the Gameworld
	 * @param x
	 * @param y
	 * @param z
	 */
	public void move(double x, double y, double z);
	
	/**
	 * Moves the Objects in the Gameworld
	 * @param x
	 * @param y
	 * @param z
	 */
	public void move(Vector3d move);
}
