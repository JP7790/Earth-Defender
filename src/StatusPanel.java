/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante Matt Chieco
 * 
 * This class creates: Displays the statistics of the game
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusPanel extends javax.swing.JPanel {
	JLabel _score,_level, _health;
	Holder _myHolder;
	public StatusPanel(Holder properties) {
		_myHolder = properties;
		this.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		this.setSize(600, 30);
		this.setBackground(Color.GRAY);
		//create Labels
		_score = new JLabel("Score: 0");
		_level = new JLabel("Level: 0");
		_health = new JLabel("Health: 0");
		
		//format labels
		_score.setFont(new Font("Arial", Font.BOLD, 20));
		_level.setFont(new Font("Arial", Font.BOLD, 20));
		_health.setFont(new Font("Arial", Font.BOLD, 20));
		
		_score.setForeground(Color.WHITE);
		_level.setForeground(Color.WHITE);
		_health.setForeground(Color.WHITE);
		
		
		this.add(_score);
		this.add(_level);
		this.add(_health);
		this.update();
	}


	public void update() {
		//		_score.setText("Score: " + _myHolder.get_cash() + " | "+ _myHolder.get_score());

		_score.setText("Score: " + _myHolder.get_score());
		_level.setText("Level: " + _myHolder.get_level());
		_health.setText("Health: " + _myHolder.get_health());
	}

}
