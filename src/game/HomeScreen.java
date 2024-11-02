package game;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Nathan Kidanemariam and Moosay Hailewold
 * HomeScreen to make our game more appealing. We used an image of a man 
 * controlling a soccer ball with the title on the side.
 *
 */
public class HomeScreen {
	/**
	 * Creates frame, panel, image, and button instance variables
	 */
	private JFrame frame;
	private JPanel panel;
	private ImageIcon background;
	private JLabel label;
	private JButton start;
	public HomeScreen() {
		//uses the path to the image to find it and set it as the background
		background = new ImageIcon(this.getClass().getResource("/game/StartScreen.jpg"));
		//puts the background on the label and sets it's size
		label = new JLabel(background);
		label.setSize(500, 350);
		
		/**
		 * creates our frames, button, panels and panels get added to the frame.
		 */
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(500, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("VERTICE SOCCER");
		panel.setLayout(null);
		panel.add(label);
		frame.add(panel);
		
		start = new JButton("Begin");
		start.setBounds(200, 260, 80, 25);
		//start.setFocusable(false);
		panel.add(start);
		frame.setVisible(true);
		
		/*
		 * this lambda expression is created and passed in as an arguement for 
		 * our actionlistener and when the start button is pressed it leads us
		 * straight to our Level GUI and exits this frame
		 */
		start.addActionListener((ActionEvent e) -> {
			LevelGUI.createAndShowGUI();
			frame.dispose();
		});
		
	}
}