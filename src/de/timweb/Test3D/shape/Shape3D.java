package de.timweb.Test3D.shape;

import de.timweb.Test3D.math.Vector3d;

public abstract class Shape3D implements Movable{

	@Override
	public void move(Vector3d move) {
		move(move.x, move.y, move.z);
	}

}
