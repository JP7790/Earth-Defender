/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante Matt Chieco
 * 
 * This class creates: an asteroid object that has the capability to move a certain location and generate random images. 
 */
import java.awt.Point;

import javax.swing.ImageIcon;

public class Asteroid {
	   java.awt.Point.Double _location;
	   java.awt.Image _fImage;
	   private int _health = 100;
	   private double _speed = 1.2;
	   private double _wayPointY;
	   private double _wayPointX;
	   private double _x,_y;
	   private int _myDamage;
	    
    public Asteroid(java.awt.Point target, java.awt.Point loc, int level){
    	_location = new Point.Double(loc.getX(),loc.getY());
    	_wayPointX = target.getX();
    	_wayPointY = target.getY();
    	_x = loc.getX();
    	_y = loc.getY();
    	_myDamage = (int) (10+ Math.random()*level*10);
    	
    	//generate asteroid image
    	if(this.getDamage()>=60){
    		_fImage = new ImageIcon("Asteroid.png").getImage();
    	}else if(this.getDamage()>=40){
    		_fImage = new ImageIcon("Asteroid2.png").getImage();
    	}else{
        	_fImage = new ImageIcon("Asteroid3.png").getImage();

    	}

    }
   
    public double getX() {
		return _location.getX(); 
	}
    public double getY() {
		return _location.getY(); 
	}
    public java.awt.Image getImage() {
		return _fImage; 
	 }

	public int getRadius() {
		return 40 + +(this.getDamage()/2 <= 20 ? this.getDamage()/2 : 20);
	}

	public void doDamge(int power) {
		_health = _health-power;
	}
	public boolean update(){
		return move();
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
		
		_location = new Point.Double(_location.getX()+dx,_location.getY()+dy);
		_x = _x+dx;
		_y = _y+dy;
		if((Math.abs(_wayPointX-_x)<=Math.abs(dx))&&(Math.abs(_wayPointY-_y)<=Math.abs(dy))){
			return true; //YES we made it to our target
		}
		return false; //NO we did not make it to our target
	}

	public int getDamage() {
		return _myDamage;
	}

	public void doDamage(int power) {
		_myDamage = _myDamage-power;
		if(_myDamage<0){
			_myDamage=0;
		}
	}
}