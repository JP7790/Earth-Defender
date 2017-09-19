/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante
 * 
 * This class creates: the main gamepanel where collision and drawing of the game objects is handled. 
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
/*
 * TO DO:
 * Centeralize collision with asteriods to bullets
 * Make hit box on earth larger.
 * slow down and speed up asteriods
 * change images based on type.
 */



@SuppressWarnings("serial")
public class GamePanel extends javax.swing.JPanel  implements MouseMotionListener,MouseListener   {
	private Holder _myHolder;
	private Line2D.Double _turret,_aim;
	@SuppressWarnings("unused")
	private double _mouseX,_mouseY;
	private double _currentAngle = -90;
	private final int EARTHX, EARTHY, EARTHR; //middle location of earth and radius of earth
	private ArrayList<Bullet> _projectiles;
	private ArrayList<Asteroid>_asteriods;
	private boolean _mousePressed = false;
	private int _time = 0;
	
	
	public GamePanel(Holder properties) {
		super();
		_myHolder = properties;
		this.setSize(800, 800);
		this.setBackground(Color.DARK_GRAY);
		//set constants based on size of panel
		EARTHX = super.getWidth()/2;EARTHY = super.getHeight()/2; EARTHR = 100;
		
		_turret  = new Line2D.Double();
		_aim = new Line2D.Double();
		
		_projectiles = new ArrayList<Bullet>();
		_asteriods = new ArrayList<Asteroid>();
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}


	public void update() {
		if(_myHolder.get_health()<=0){
			_myHolder.set_gamePaused(true);
			return;
		}
	
		this.repaint();
		if(_mousePressed!=false){
			_myHolder.set_ammo(this.chargeBullet(_time));
			_time++;
		}
		for(int i = 0;i<_asteriods.size();i++){
			if(_asteriods.get(i).update()){
				_myHolder.set_health(_myHolder.get_health()-_asteriods.get(i).getDamage());
				_asteriods.remove(i);
			}
		}
		for(int i = 0;i<_projectiles.size();i++){
			if(_projectiles.get(i).update()){
				_projectiles.remove(i);
			}else{ // we are still traveling, time to test if an asteroid was hit.
				for(int a = 0;a<_asteriods.size();a++){
					if((Math.abs(_asteriods.get(a).getX()+(_asteriods.get(a).getRadius()/2)-_projectiles.get(i).getX())<=Math.abs(_asteriods.get(a).getRadius()))&&
							(Math.abs(_asteriods.get(a).getY()+(_asteriods.get(a).getRadius()/2)-_projectiles.get(i).getY())<=Math.abs(_asteriods.get(a).getRadius()))){ //check if we hit
						System.out.println("Hit");
						int preDamage = (_asteriods.get(a).getDamage());
						_asteriods.get(a).doDamage(_projectiles.get(i).getPower());
						int postDamage = (_asteriods.get(a).getDamage());
						this.scoreIncrease(preDamage-postDamage);
						if(_asteriods.get(a).getDamage()<=0){
							_asteriods.remove(a);
						}
						_projectiles.remove(i);
						break;
					}
				}
			}
		}
		this.generateNewAsteriods();
	}
	private void scoreIncrease(int power) {
		_myHolder.set_score(_myHolder.get_score()+power);
		_myHolder.set_cash(_myHolder.get_cash()+power);
		if(_myHolder.get_score()>=(_myHolder.get_level()*500)){
			_myHolder.set_level(_myHolder.get_level()+1);
		}
	}


	private void generateNewAsteriods() {
		//every level adds a max of 2 asteroids.
		int max = 5+ (_myHolder.get_level()/2 <= 5 ? _myHolder.get_level()/2 : 5);
		if(_asteriods.size()<max){
			double r = Math.random()*360;
			double x = EARTHX + ((super.getWidth()) * Math.cos(Math.toRadians(r)));
			double y = EARTHY + ((super.getHeight()) * Math.sin(Math.toRadians(r)));
			Point origin = new Point((int)x,(int)y);
			System.out.println(("New Aasteriod Spawned at" + origin.getX() + " | "+ origin.getY()));
			_asteriods.add(new Asteroid(new Point(EARTHX,EARTHY),origin,_myHolder.get_level()));
		}
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D)g;
		for(Asteroid a: _asteriods){
			brush.drawImage(a.getImage(), (int)a.getX(), (int)a.getY(), a.getRadius(), a.getRadius(),
					new Color(Color.DARK_GRAY.getRed(),Color.DARK_GRAY.getGreen(),Color.DARK_GRAY.getBlue(),1), null);
		}
		
		g.drawImage((new ImageIcon("EarthImageSERFinal.png").getImage()), EARTHX-(EARTHR/2)-10, EARTHY-(EARTHR/2), EARTHR+30,EARTHR,new Color(Color.DARK_GRAY.getRed(),Color.DARK_GRAY.getGreen(),Color.DARK_GRAY.getBlue(),1), null);
		
		//draw aiming line
		brush.setStroke(new BasicStroke(2));
		brush.setColor(Color.red);
		brush.draw(_aim);
		
		//draw turret line
		brush.setColor(Color.BLACK);
		brush.setStroke(new BasicStroke(10));
		brush.draw(_turret);
		g.setColor(Color.DARK_GRAY);
		
		//draw bullets
		for(Bullet b: _projectiles){
			b.draw(brush);
		}

	}
	
	private int chargeBullet(int time){
		int myPower = 10;
		if(time*2<=_myHolder.get_maxAmmo() && time*2>=10)
			myPower = myPower+(time*2)-10;
		else if((time*2)>=_myHolder.get_maxAmmo())
			myPower = _myHolder.get_maxAmmo();
		return myPower;
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseMoved(e);
	}

    public static double GetAngleOfLineBetweenTwoPoints(Point.Double p1, Point.Double p2)
    {
        double xDiff = p2.x - p1.x;
        double yDiff = p2.y - p1.y;
        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }
	
	@Override
	public void mouseMoved(MouseEvent e) {
		_mouseX = e.getX();
		_mouseY = e.getY();
		 _currentAngle = GetAngleOfLineBetweenTwoPoints(new Point.Double(EARTHX,EARTHY),new Point.Double(e.getX(),e.getY()));
		double x = EARTHX + ((EARTHR-50) * Math.cos(Math.toRadians(_currentAngle)));
		double y = EARTHY + ((EARTHR-50) * Math.sin(Math.toRadians(_currentAngle)));
		_aim.setLine(EARTHX,EARTHY,e.getX(),e.getY());
		_turret.setLine(EARTHX, EARTHY, x, y);
	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent e) {
		if(_myHolder.get_gamePaused())
			return;
		_mousePressed=true;  
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(_myHolder.get_gamePaused())
			return;
		
		_mousePressed=false;
		System.out.println(this.chargeBullet(_time));
		Point target = new Point((int)(EARTHX + ((super.getWidth()) * Math.cos(Math.toRadians(_currentAngle)))),(int)(EARTHX + ((super.getHeight()) * Math.sin(Math.toRadians(_currentAngle)))));
		_projectiles.add(new Bullet(this.chargeBullet(_time),target,new Point(EARTHX,EARTHY)));
		_time = 0;
		_myHolder.set_ammo(10);
	}

}
