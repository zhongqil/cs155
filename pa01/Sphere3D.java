package cs155.pa01;
import java.awt.Color;
/** class represents a 3D sphere **/
public class Sphere3D implements Object3D
{
	public Point3D center;
	public double radius = 0.0;

	public Sphere3D (Point3D center, double radius) {
		this.center = center; this.radius = radius;
	}

	public RayHit rayIntersect(Ray3D r) {
		 // calculate the RayHit object using algorithms above
		 double d_length = (r.d.length());
		 double factorA = d_length * d_length;
		 Point3D p_minus_c = r.p.subtract(this.center);
		 double factorB = 2*r.d.dot(p_minus_c);
		 double factorC = p_minus_c.length() * p_minus_c.length() - this.radius * this.radius;
		 double factorD = factorB * factorB - 4 * factorA * factorC;
		 if (factorD < 0){
		 	System.out.println("drew");
		 	return new RayHit(Double.POSITIVE_INFINITY, null, null);
		 }
		 else if (factorD == 0){
		 	double t = - factorB / (2 * factorA);
		 	if (t > 0){
		 		return new RayHit(r.distance(t), r.atTime(t), this);
		 	} else {
		 		return new RayHit(Double.POSITIVE_INFINITY, null, null);
		 	}
		 }
		 else {
		 	double t1 = (-factorB - Math.sqrt(factorD)) / (2 * factorA);
		 	double t2 = (-factorB + Math.sqrt(factorD)) / (2 * factorA);
		 	if (t1 > 0 && t2 > 0) {
		 		return new RayHit(r.distance(t1), r.atTime(t1), this);
		 	} else {
		 		return new RayHit(Double.POSITIVE_INFINITY, null, null);
		 	}
		 }

	}
}