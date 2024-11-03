package game;

import javax.swing.*;

/**
 * 
 * @author  Moosay Hailewold
 * This GUI allows the user to select the level depending on a button and then
 * the button links to the game
 *
 */
public class LevelGUI {
private static String selectedLevel;
private static boolean guiHasRun = false;

/**
 * This method creates our GUI using a JFrame and a JPanel. 
 */
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Level Selection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 100);

		JPanel welcomePanel = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to the Game!");
		welcomePanel.add(welcomeLabel);
		frame.add(welcomePanel, "North");

		JPanel levelPanel = new JPanel();
		frame.add(levelPanel);
		/**
		 * If this is pressed then the static variable is set to easy for the
		 * entire game and a pane pops up showing us our decision then an 
		 * instance of the game is created and this frame is collected.
		 */
		JButton easyButton = new JButton("Easy");
		easyButton.addActionListener(e -> {
			selectedLevel = "Easy";

			JOptionPane.showMessageDialog(frame, "You selected Easy level.");
			SoccerX x = new SoccerX();
			x.repaint();
			frame.dispose();
			guiHasRun = true;

		});

		/**
		 * If this is pressed then the static variable is set to easy for the
		 * entire game and a pane pops up showing us our decision then an 
		 * instance of the game is created and this frame is collected.
		 */
		JButton mediumButton = new JButton("Medium");
		mediumButton.addActionListener(e -> {
			selectedLevel = "Medium";
			JOptionPane.showMessageDialog(frame, "You selected Medium level.");
			SoccerX x = new SoccerX();
			x.repaint();
			frame.dispose();
			guiHasRun = true;

		});
		
		/**
		 * If this is pressed then the static variable is set to easy for the
		 * entire game and a pane pops up showing us our decision then an 
		 * instance of the game is created and this frame is collected.
		 */
		JButton hardButton = new JButton("Hard");
		hardButton.addActionListener(e -> {
			selectedLevel = "Hard";
			JOptionPane.showMessageDialog(frame, "You selected Hard level.");
			SoccerX x = new SoccerX();
			x.repaint();
			frame.dispose();
			guiHasRun = true;

		});

		levelPanel.add(easyButton);
		levelPanel.add(mediumButton);
		levelPanel.add(hardButton);

		frame.setVisible(true);

	}
	/**
	 * 
	 * @return a string representing the button pressed
	 */
	public static String getSelectedLevel() {
		return selectedLevel;
	}
	
	/**
	 * 
	 * @return true if the GUI has run, false if it hasn't
	 */
	public static boolean GuiHasRun() {
		return guiHasRun;
	}
}
