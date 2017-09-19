/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante Matt Chieco
 * 
 * This class creates a bullet for the player to shoot
 */
import java.awt.Color;
import java.awt.Point;

@SuppressWarnings("serial")
public class Bullet extends java.awt.geom.Ellipse2D.Double {
	private double _x,_y;
	private double _speed = 3;
	private double _wayPointX;
	private double _wayPointY;
	private int _radius = 5;
	private int _power;
	public Bullet(int power, Point target, Point spawn) {
		super();
		_x = spawn.getX();
		_y = spawn.getY();
		_power = power;
		_speed = _speed+(power/20);
		_radius = _radius+(power/20);
		this.setSize(_radius,_radius);
		this.setLocation(_x, _y);
		_wayPointX = target.getX();
		_wayPointY = target.getY();
	}
    public void setLocation (double x, double y) {
    	_x = x;
    	_y = y;
    	this.setFrame (x, y, this.getWidth(), 
                       this.getHeight());
    }
	public void setSize (int width, int height) {
		this.setFrame(this.getX(), this.getY(), 
                      width, height);
    }
	public void draw (java.awt.Graphics2D aBrush) {
		aBrush.setColor(new Color(220,220,71));
		aBrush.draw(this);
		aBrush.fill(this);
		
	}
	public boolean update(){
		return this.move();
	}
	
	private boolean move(){
		double dy = _speed;
		double dx = _speed;
		if(Math.abs(_wayPointY-_y)<Math.abs(_wayPointX-_x)){
			dy = _speed*(Math.abs(_wayPointY-_y)/Math.abs(_wayPointX-_x));
		}
		if((Math.abs(_wayPointX-_x))<Math.abs(_wayPointY-_y)){
			dx = _speed*(Math.abs(_wayPointX-_x)/Math.abs(_wayPointY-_y));
		}
		
		if(_wayPointX<_x){dx = -dx;};if(_wayPointY<_y){dy = -dy;} //change the dx/dy to move the plane in the correct direction
		
		this.setLocation(_x+dx, _y+dy);
		
		if((Math.abs(_wayPointX-_x)<=Math.abs(dx))&&(Math.abs(_wayPointY-_y)<=Math.abs(dy))){
			return true; //YES we made it to our target
		
		
		}
		return false; //NO we did not make it to our target
	}
	public int getRadius() {
		return _radius;
	}
	public int getPower() {
		return _power;
	}
}
