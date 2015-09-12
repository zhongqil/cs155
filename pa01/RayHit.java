package cs155.pa01;
/** Record properties of intersection of a ray and an object **/
public class RayHit {
	/** distance along ray to the first intersection **/
	public double distance;
	/** point at which the ray first intersects the object **/
	public Point3D iPoint;
	/** object that the ray intersects **/
	public Object3D obj;

	public RayHit (double distance, Point3D iPoint, Object3D obj)
	{
		this.distance = distance; this.iPoint = iPoint; this.obj = obj;
	}
}