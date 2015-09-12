package cs155.pa01;

public class Plane3D {
	private Point3D p; // the point
	private Point3D n; // the normal
	
	public Plane3D(Point3D p, Point3D n){
		this.p = p; this.n=n;	
	}
	
	/**
		returns the time at which the ray intersects the plane or returns Double.POSITIVE_INFINITY
	**/
	public double intersect(Ray3D r) {
		double dn = (r.d).dot(n);
		if (dn==0.0) return Double.POSITIVE_INFINITY;
		double t = (p.subtract(r.p)).dot(n)/dn;
		if (t > 0) return t;
		else return Double.POSITIVE_INFINITY;
	}
	
	/**
		returns the normal at the point p, assuming p is on the plane P
	**/
	public Point3D getNormal(Point3D p){
		return this.n;
	}
	
}