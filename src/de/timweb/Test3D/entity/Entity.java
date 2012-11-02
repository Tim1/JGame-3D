package de.timweb.Test3D.entity;

import java.awt.Graphics;

import de.timweb.Test3D.math.Vector3d;

public abstract class Entity {
	protected boolean isAlive = true;

	protected Vector3d pos;

	public Entity(Vector3d pos) {
		this.pos = pos.copy();
	}

	public Vector3d getPos() {
		return pos;
	}

	public void kill() {
		onKilled();
		isAlive = false;
	}

	protected void onKilled() {
	}

	public boolean isAlive() {
		return isAlive;
	}

	public abstract void update(int delta);

	public abstract void render(Graphics g);

	public void move(double x, double y, double z) {
		pos.add(x, y, z);
	}
}
