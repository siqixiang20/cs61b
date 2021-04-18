/** Create a Planet.*/
public class Planet{
	private static final double G = 6.67 * 1e-11; //The gravitational constant 
	
	public double xxPos; /**Its current x position.*/
	public double yyPos; /**Its current y position.*/
	public double xxVel; /**Its current velocity in the x direction.*/
	public double yyVel; /**Its current velocity in the y direction.*/
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, 
	              double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p) {
		double diffx;
		double diffy;
		diffx = this.xxPos - p.xxPos;
		diffy = this.yyPos - p.yyPos;
		double dist = Math.sqrt(diffx * diffx + diffy * diffy);
		return dist;	
	}
	
	public double calcForceExertedBy(Planet p) {
		double force;
		double r = calcDistance(p);
		force = G * this.mass * p.mass / (r * r);
		return force;
	}
	
	public double calcForceExertedByX(Planet p) {
		double force = calcForceExertedBy(p);
		double r = calcDistance(p);
		double Fx = force * (p.xxPos - this.xxPos)/r;
		return Fx;
	}
	
	public double calcForceExertedByY(Planet p) {
		double force = calcForceExertedBy(p);
		double r = calcDistance(p);
		double Fy = force * (p.yyPos - this.yyPos)/r;
		return Fy;
	}
	
	/** Check if two planets are equal. */
	public Boolean equals(Planet p) {
		if (this.xxPos == p.xxPos && this.yyPos == p.yyPos && 
		    this.xxVel == p.xxVel && this.yyVel == p.yyVel &&
			this.mass == p.mass && this.imgFileName == p.imgFileName) {
				return true;
			} else {
				return false;
			}
	}
	
	/** calculate the net X and net Y force exerted by all planets 
	  * in that array upon the current Planet. 
	  */
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netFx = 0.0;
		for (Planet p : allPlanets) {
			if (this.equals(p)){
				continue;
			} else {
				netFx += this.calcForceExertedByX(p);
			}
		}
		return netFx;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netFy = 0.0;
		for (Planet p : allPlanets) {
			if (this.equals(p)){
				continue;
			} else {
				netFy += this.calcForceExertedByY(p);
			}
		}
		return netFy;
	}
	
	/** the resulting change in the planet’s velocity and position 
	  * in a small period of time dt.
	  */
	public void update(double dt, double fX, double fY) {
		double acceX = fX / this.mass;
		double acceY = fY / this.mass;
		this.xxVel += dt * acceX;
		this.yyVel += dt * acceY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
		
	}
	
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}
}