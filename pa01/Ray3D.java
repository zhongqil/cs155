package cs155.pa01;

/**   A Ray3D consists of a point and a (normalized) direction.
**/

public class Ray3D
 {
  public Point3D p,d;

     /**
       This represents a 3D ray with a specified origin point p and direction d.
       The direction of a ray is a normalized vector.
     **/

  public Ray3D(Point3D p, Point3D d)
 {
	 this.p = p;
	 this.d = d.normalize();
  }

     /**
	This returns the point along the ray t units from its origin p
     **/

     public Point3D atTime(double t)
   {
	   
      return p.add(d.scale(t));
   } 

     public static void main(String[] args){
    	 /* put in some tests here */
     }

    public double distance(double t)
    {
      return this.atTime(t).subtract(this.p).length();
    }
	 
	 public static Ray3D generateRay(int i, int j, int w, int h){
		 Point3D camera = new Point3D(0,0,0);
		 Point3D screen = new Point3D(-0.5 + i*(1.0/w) + 1.0/(2*w), -0.5+j*(1.0/h) + 1.0/(2*h), -1);
		 return new Ray3D(camera, screen);
	 }
	 
	 public String toString(){
		 return this.p + "+t*"+this.d;
	 }
 }

  

