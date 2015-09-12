package cs155.pa01;

public class Light3D {
	Point3D p;
	
	public Light3D(Point3D p){
		this.p = p;
	}
	
	/**
		return the diffuse component of illumination from the light
		for the point q on a surface with normal n
		**/
	public double getDiffuse(Point3D q, Point3D n){
		Point3D lightvec = p.subtract(q);
		Point3D lightdir = lightvec.normalize();
		double d = lightdir.dot(n);
		if (d <=0.0) return 0;
		else return d;
	}
}