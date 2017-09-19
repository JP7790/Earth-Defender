/**
 * Earth Defender: Final Project
 * Authors: Matt Chieco
 * 
 * This class creates:
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ControlPanel extends javax.swing.JPanel {
	JLabel _cash,_ammo;
	JButton _menu;
	Holder _myHolder;
	AnimationTimer _timer;
	public ControlPanel(Holder properties, AnimationTimer timer) {
		_myHolder = properties;
		_timer = timer;
		this.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		this.setSize(600, 30);
		this.setBackground(Color.GRAY);
		//create Labels
		_cash = new JLabel("Cash: 0");
		_ammo = new JLabel("Ammo: 0 / 0");
		
		//create menuButton
		_menu = new JButton("Pause Game (P)");
		
		
		//format labels
		_cash.setFont(new Font("Arial", Font.BOLD, 20));
		_ammo.setFont(new Font("Arial", Font.BOLD, 20));

		_cash.setForeground(Color.WHITE);
		_ammo.setForeground(Color.WHITE);

		//button listener
		_menu.addActionListener(new ButtonListener());
		this.addKeyListener(new KeyListener());
		this.setFocusable(true);
		
		this.add(_cash);
		this.add(_ammo);
		this.add(_menu);
		this.update();
	}


	public void update() {
		_cash.setText("Cash: "+_myHolder.get_cash());
		if(_myHolder.get_ammo()==_myHolder.get_maxAmmo()){
			_ammo.setForeground(Color.red);
		}else{
			_ammo.setForeground(Color.white);
		}
		_ammo.setText("Ammo Charge: "+_myHolder.get_ammo()+ " / "+_myHolder.get_maxAmmo());
	}
	private class ButtonListener implements java.awt.event.ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(_timer.isRunning()){
				_menu.setText("Resume Game (P)");
				_timer.stop();
				_myHolder.set_gamePaused(true);
			}else{
				_menu.setText("Pause Game (P)");
				_timer.start();
				_myHolder.set_gamePaused(false);
			}
		}
		
	}
	private class KeyListener implements java.awt.event.KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == 'p'){
				if(_timer.isRunning()){
					_menu.setText("Resume Game (P)");
					_timer.stop();
					_myHolder.set_gamePaused(true);
				}else{
					_menu.setText("Pause Game (P)");
					_timer.start();
					_myHolder.set_gamePaused(false);
				}
			}else if(e.getKeyChar() == '='){
				_myHolder.set_level(_myHolder.get_level()+1);
			}else if(e.getKeyChar() == 'g'){
				_myHolder.set_health(_myHolder.get_health()+500);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {			
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
		
	}

}


