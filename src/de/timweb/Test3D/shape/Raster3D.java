package de.timweb.Test3D.shape;

import java.awt.Graphics;
import java.util.ArrayList;

import de.timweb.Test3D.graphics.Renderable;
import de.timweb.Test3D.math.Vector3d;

/**
 * A Raster (LineCount x LineCount Lines) around a point
 * 
 * @author Tim
 */
public class Raster3D extends Shape3D implements Renderable {
	private static final long serialVersionUID = 789397089002518860L;
	private ArrayList<Path3D> line = new ArrayList<Path3D>();
	private ArrayList<Path3D> cross = new ArrayList<Path3D>();

	/**
	 * 
	 * @param origin
	 * @param linecount
	 * @param distance
	 *            Distance between the lines
	 */
	public Raster3D(Vector3d origin, int linecount, int distance) {
		final int MAXLENGTH = linecount*distance;
		for (int i = -linecount/2; i <= linecount/2; i++) {
			Vector3d start = new Vector3d(origin.x+i*distance, origin.y, origin.z-MAXLENGTH/2);
			Vector3d end = start.copy().add(0, 0, MAXLENGTH);
			
			cross.add(new Path3D(start,end));
		}

		System.out.println();
		for (int i = -linecount/2; i <= linecount/2; i++) {
			Vector3d start = new Vector3d(origin.x-MAXLENGTH/2, origin.y, origin.z+i*distance);
			Vector3d end = start.copy().add(MAXLENGTH, 0, 0);
			
			cross.add(new Path3D(start,end));
		}
	}

	@Override
	public void render(Graphics g) {
		for(Path3D p:line)
			p.render(g);
		for(Path3D p:cross)
			p.render(g);

	}

	@Override
	public void move(double x, double y, double z) {
		for(Path3D p:line)
			p.move(x, y, z);
		for(Path3D p:cross)
			p.move(x, y, z);
	}
	
}
