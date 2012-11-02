package de.timweb.Test3D.entity;

import java.awt.Graphics;

import de.timweb.Test3D.math.Matrix3D;
import de.timweb.Test3D.math.Plane3D;
import de.timweb.Test3D.math.Vector3d;
import de.timweb.Test3D.math.VectorUtils;

public class Player extends Entity {
	public static final int DISTANCE_TO_SCREEN = 20;
	public static final double DEFAULTSPEED =  0.1;
	
	public static final Player p = new Player();

	private double speed = DEFAULTSPEED;

	private Plane3D screencanvas;

	private Player() {
		super(new Vector3d(0, 0, -DISTANCE_TO_SCREEN));

		screencanvas = Plane3D.getByNormalAndOffset(new Vector3d(0, 0, 1), 0);
	}

	@Override
	public void update(int delta) {

	}

	@Override
	public void render(Graphics g) {

	}

	/**
	 * 
	 * @param leftright
	 *            Angle to Rotate around the vertical Y-Axis
	 * @param updown
	 *            Angle to Rotate around the horizontal XZ-Plane
	 */
	public void rotate(double leftright, double updown) {
		if (leftright != 0) {
			screencanvas.rotate(Player.p.getEyepos(),leftright);
			
		}
		if (updown != 0) {
//			screencanvas.getNormalVector().set(
//					VectorUtils.mulitply(Matrix3D.getRotationMatrix(
//							Math.toRadians(updown), Matrix3D.AXIS_X),
//							screencanvas.getNormalVector()));
		}

	}

	@Override
	public void move(double x, double y, double z) {
		throw new IllegalAccessError(
				"A Player cannot move independently of his angle");
	}

	public Plane3D getScreencanvas() {
		return screencanvas;
	}

	public Vector3d getEyepos() {
		Vector3d result = pos.copy();
		result.add(0, 0, 0);
		return result;
	}

	/**
	 * Moves the Player in his looking direction
	 * 
	 * @param forward
	 *            true if forward, false if backward
	 */
	public void moveForward(int delta, boolean forward) {
		int opposite = forward ? 1 : -1;

		double add = delta * speed * opposite;
		Vector3d move = screencanvas.getNormalVector();

		pos.add(move.x * add, move.y * add, move.z * add);
		screencanvas.addOffset(add);
	}

	public void moveSide(int delta, boolean toRight) {
		int opposite = toRight ? -1 : 1;
		
		double add = delta * speed * opposite;
		Vector3d move = Vector3d.crossProduct(screencanvas.getNormalVector(), Vector3d.AXIS_Y);
		pos.add(move.x * add, move.y * add, move.z * add);
	}

	public void setSprint(boolean sprint) {
		if(sprint)
			speed = DEFAULTSPEED *2;
		else
			speed = DEFAULTSPEED;
	}

}