package de.timweb.Test3D.math;

public class VectorUtils {
	
	/**
	 * Multiplies the Matrix with the Vector
	 * @param matrix
	 * @param vector
	 * @return the resulting Vector
	 */
	public static Vector3d mulitply(Matrix3D matrix,Vector3d vector){
		Vector3d result = new Vector3d();
		
		result.x += matrix.x1*vector.x; 
		result.x += matrix.x2*vector.y; 
		result.x += matrix.x3*vector.z; 

		result.y += matrix.y1*vector.x; 
		result.y += matrix.y2*vector.y; 
		result.y += matrix.y3*vector.z; 

		result.z += matrix.z1*vector.x; 
		result.z += matrix.z2*vector.y; 
		result.z += matrix.z3*vector.z; 
		
		return result;
	}

	
}
