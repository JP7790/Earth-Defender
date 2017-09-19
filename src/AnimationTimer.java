/**
 * Take home assignment 4
 * @author Joe Passanante
 * Purpose of class: Runs the update method, drives the movement of the aircrafts.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AnimationTimer extends javax.swing.Timer{
	private update _update;
	public AnimationTimer(update update) {
		super(15, null);
		_update = update;
		//add the action listener to the timer
		super.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			_update.update();
		}
		});
		
	}
}
