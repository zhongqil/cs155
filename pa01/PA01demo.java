package cs155.pa01;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import java.awt.Color;


/**
 * This class tests out the methods in PA01
 * @author tim
 *
 */
public class PA01demo {
	private static final int N = 800;
    private static MyCanvas3D mc = new MyCanvas3D(N,N);
    
    /**
     * this creates a window to demo the Canvas3D object
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
    	/*
    	 * This is the preferred way to create a GUI.
    	 * It avoid thread problems by creating the GUI 
    	 * in the EventDispatch thread.
    	 */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
		Thread.sleep(1000L);
        
		drawSceneOnMC();		
       
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {mc.refresh();} // copy the offscreen canvas to the screen
        });

        System.out.println("drew a circle!");
    }
	
	public static void drawSceneOnMC(){

		Plane3D planeA = new Plane3D(new Point3D(0,-1,0), new Point3D(0,1,0));
        Plane3D planeB = new Plane3D(new Point3D(1.5,0,0), new Point3D(-1,0,0));
        Point3D pointQ = new Point3D(1, 1, -5);
        double radius = 1;
        Sphere3D sphere = new Sphere3D(pointQ, radius);
		Light3D light = new Light3D(new Point3D(0.3,0,-2));
        Point3D lightL = new Point3D(2, 1, 0);
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				Ray3D r = Ray3D.generateRay(i,j,N,N);
				double t_A = planeA.intersect(r);
                double t_B = planeB.intersect(r);
                RayHit ray = sphere.rayIntersect(r);

                Point3D p_A = r.atTime(t_A);
                Point3D p_B = r.atTime(t_B);
                double distance_A = p_A.distance(lightL);
                double distance_B = p_B.distance(lightL);
                double distance_C = Double.POSITIVE_INFINITY;
                if (ray.iPoint != null){
                    distance_C = ray.iPoint.distance(lightL);

                }
				
				if (t_A==Double.POSITIVE_INFINITY && t_B==Double.POSITIVE_INFINITY && distance_C==Double.POSITIVE_INFINITY) {
					mc.drawPixel(i, N-1-j,Color.BLUE);
				} else {
                    if (distance_C < distance_A && distance_C < distance_B) {
                        mc.drawPixel(i, N-1-j, getColor(ray.iPoint.subtract(pointQ), ray.iPoint, lightL));
                    } else if (distance_B < distance_A && distance_B < distance_C) {
                        mc.drawPixel(i, N-1-j, getColor(new Point3D(-1, 0, 0), p_B, lightL));
                    } else {
                        mc.drawPixel(i, N-1-j, getColor(new Point3D(0, 1, 0), p_A, lightL));
                    } 
                } 
				// intersect ray r with a plane P
				// if not intersection set i,j pixel to blue
				// else calculate the distance d to intersection point
				// let color c be 1/(d+1), 1/(d+1), 1/(d+1)
				// set i,j pixel color to c
			}
		}
	}

    public static Color getColor(Point3D n, Point3D p, Point3D light) {
        Point3D u = light.subtract(p);
        float b = Math.abs((float)(n.dot(u) / (n.length() * u.length())));
        //System.out.println("drew" + Math.abs(b));
        return new Color(b, b, b);
    }

    /*
     * here we create a window, add the canvas,
     * set the window size and make it visible!
     */
    private static void createAndShowGUI() {
        
        JFrame f = new JFrame("PA01 Demo");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(mc);
        f.setSize(N,N);
        f.setVisible(true);
    } 
	
	private static void drawCircle(int x0, int y0, int r){
    	for(int i=-r;i<=r;i++){
    		int x = x0 + i;
    		int y = y0 + (int)Math.sqrt(r*r-i*i);
    		mc.drawPixel(x,y,Color.BLUE);
    	}
    	for(int i=0;i<180;i++){
			double s = degToRad(i);
    		int x = (int) (x0 + r*Math.cos(s));
    		int y = (int) (y0 - r*Math.sin(s));
    		mc.drawPixel(x, y, Color.YELLOW);
    	}
	}
	
	private static double degToRad(double d){
		return (d/360)*2*Math.PI;
	}

}

