package cs155.pa01;
public interface Object3D {
	/** rayIntersect(r) returns the intersection
	of the object with a ray as a RayHit object.
	**/
	//public String obj = "plane";
	//public Object3D (String obj) {
	//	this.obj = obj;
	//}
	public abstract RayHit rayIntersect(Ray3D ray);
}