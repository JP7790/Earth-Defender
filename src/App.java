/**
 * Earth Defender: Final Project
 * Authors: Joe Passanante Matt Chieco
 * 
 * This class creates: A frame with the three main panels inside of including gamepanel statuspanel and controlpanel. 
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class App extends javax.swing.JFrame implements update {
	StatusPanel stat;
	GamePanel game;
	ControlPanel control;
	public App(){
		super("EARTH DEFENDER");
		super.setResizable(false);
		Holder properties = new Holder();
		JPanel mainPanel = new JPanel();
		AnimationTimer timer = new AnimationTimer(this);
			timer.start();
		//set Layout
		mainPanel.setLayout(new BorderLayout());
		
		//create the panels
		stat = new StatusPanel(properties); game = new GamePanel(properties); control = new ControlPanel(properties, timer);
		
		//add panels
		this.add(mainPanel);
		mainPanel.add(stat, BorderLayout.NORTH);
		mainPanel.add(game, BorderLayout.CENTER);
		mainPanel.add(control, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,860);
	}
	public void update(){
		stat.update();
		game.update();
		control.update();
	}

	public static void main(String[]args){
		new App();
	}
}
