public class NBody{
	public static double readRadius(String file) {
		In in = new In(file);
		int n = in.readInt();
		double radius = in.readDouble();
		
		return radius;
	}
	
	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int n = in.readInt();
		double radius = in.readDouble();
		
		Planet[] allPlanets = new Planet[n];
		
		for (int i = 0; i < n; i ++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			
			allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		
		return allPlanets;
	}
	
	public static void main(String[] args) {
		/** Read command line arguments. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] allPlanets = NBody.readPlanets(filename);
		
		/** Draw the Background. */
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		/** Drawing All of the Planets. */
		for(Planet p: allPlanets) {
			p.draw();
		}
		
		/** Creating an Animation. */
		StdDraw.enableDoubleBuffering();
		
		int n = allPlanets.length;
		for(double t = 0.0; t <= T; t += dt) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			
			for (int i = 0; i < n; i++) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			
			for (int i = 0; i < n; i++) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p: allPlanets) {
			    p.draw();
			}
			StdDraw.show();
		    StdDraw.pause(10);	
		}
		
		/** print out the final state of the universe. */
		StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
				
	}	
}