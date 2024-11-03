package game;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author  Moosay Hailewold
 * Represents our defender in the game. This is not a moveable character 
 * although it does move, the user can't move it. 
 *
 */
public class Defender extends Polygon {
	private double speed; // Velocity in the horizontal direction, we can make it depend on the level
	private double min_X; // Minimum X position (left boundary)
	private double max_X = 800; // Maximum X position (right boundary)
	private static String selectedLevel = LevelGUI.getSelectedLevel();
	private boolean oppSpeed;
	public SoccerBall.points point; 
	
	/**
	 * calls the superclass constructor to create our defender
	 * and then creates a local variable of our inner class which contains
	 * the possible levels in the game. It also instantializes our inner class
	 * in Element which contains the points since a defender can also have 
	 * points.
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 */
	public Defender(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);

		Level level = new Level();
		level.level();
		point = new SoccerBall.points();
	}

	/**
	 * Constructor for moving objects in the opposite direction created using
	 * the previous constructor. Complies without super because it's using the
	 * above super constructor,
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 * @param oppSpeed
	 */
	public Defender(Point[] inShape, Point inPosition, double inRotation, boolean oppSpeed) {
		this(inShape, inPosition, inRotation);
		this.oppSpeed = oppSpeed;
	}
	
	/**
	 * Default constructor if nothing is specified.
	 */
	public Defender() {
		super(new Point[] { new Point(0, 0) }, new Point(0, 0), 0);
		Level level = new Level();
		level.level();
	}
	
	/**
	 * This Method paints our defender onto the screen using a passed in Graphics
	 * variable. From the array of points passed in it creates an int array of
	 * x and y coordinates and fills the polygon. It also sets the defender 
	 * to black and creates a font so that we can paint how many goals were 
	 * scored by the defender when they steal the ball.
	 * @param brush
	 */
	public void paint(Graphics brush) {
		Point[] points = this.getPoints();

		// x-coordinate
		int[] xCoordinates = new int[points.length];
		// y- coordinate
		int[] yCoordinates = new int[points.length];

		for (int i = 0; i < points.length; i++) {
			xCoordinates[i] = (int) points[i].getX();
			yCoordinates[i] = (int) points[i].getY();
		}

		brush.fillPolygon(xCoordinates, yCoordinates, points.length);
		brush.setColor(Color.black);
		Font f = new Font("BOLD", Font.BOLD, 15);
		brush.setFont(f);
		brush.drawString("Team 1: " + point.goalsScored, 100, 11);
	}
	
	/**
	 * Updates the position of the defender
	 */
	public void move() {
		// update the X position of the defender based on its speed
		double newX = position.getX() + speed;

		// Bound check
		if (newX < min_X || newX > max_X) {
			// Reverse the speed to move in the opposite direction
			speed = -speed;
		}

		// updates the position
		position.setX(newX);
	}
	
	/**
	 * changes the speed instance variable
	 * @param Changespeed
	 */
	public void setSpeed(double Changespeed) {
		speed = Changespeed;

	}
	/**
	 * moves the calling defender to the opposite direction
	 * @return the current object
	 */
	public Defender rightMode() {
		oppSpeed = true;

		return this;
	}
	
	/**
	 * 
	 * @return the oppositeSpeed if true or false
	 */
	public boolean getOppSpeed() {
		return oppSpeed;
	}
	
	/**
	 * Gets the level from the LevelGUI and sets it here 
	 * @param selectedLevel
	 */
	public void setSelectedLevel(String selectedLevel) {
		Defender.selectedLevel = selectedLevel;
	}
	
	/**
	 * Inner class level which sets the speed depending on what level is 
	 * specified by the GUI. 
	 * @author Nathan Kidanemariam and Moosay Hailewold
	 *
	 */
	public class Level {

		public void level() {
			
			/**
			 * if the level is easy speed is 2.5 and if opposite is on then 
			 * the negative version is turned on. The goal is also set to 2.5
			 */
			if (selectedLevel.equalsIgnoreCase("easy")) {
				if (oppSpeed) {
					setSpeed(-2.5);
				} else {
					setSpeed(2.5);
				}
				Goal.setInitialVelocity(2.5);
				
				/**
				 * if the level is easy speed is 5.5 and if opposite is on then 
				 * the negative version is turned on. The goal is also set to 5.5
				 */
			} else if (selectedLevel.equalsIgnoreCase("Medium")) {
				if (oppSpeed) {
					setSpeed(-5.5);
				} else {
					setSpeed(5.5);
				}
				Goal.setInitialVelocity(5.5);
				
				/**
				 * if the level is easy speed is 7.5 and if opposite is on then 
				 * the negative version is turned on. The goal is also set to 7.5
				 */
			} else {
				if (oppSpeed) {
					setSpeed(-7.5);
				} else {
					setSpeed(7.5);
				}
				Goal.setInitialVelocity(7.5);
			}
		}
		
		/**
		 * returns an array of defenders depending on the level
		 * @return an array of defenders used for the game
		 */
		public Defender[] map() {
			if (selectedLevel.equalsIgnoreCase("easy")) {
				return generateEasyMap();
			} else if (selectedLevel.equalsIgnoreCase("Medium")) {
				return generateMediumMap();
			} else {
				return generateHardMap();
			}

		}
		
		/**
		 * for an easy game we have 11 players on the field all moving at around
		 * 2.5
		 * @return an array of defenders
		 */

		private Defender[] generateEasyMap() {
			Point[] defender = new Point[4];

			Point defender1 = new Point(0, 0);
			Point defender2 = new Point(10, 0);
			Point defender3 = new Point(10, 10);
			Point defender4 = new Point(0, 10);
			defender[0] = defender1;
			defender[1] = defender2;
			defender[2] = defender3;
			defender[3] = defender4;
			Defender[] d = new Defender[11];
			d[0] = new Defender(defender, new Point(100, 100), 0, true);
			d[1] = new Defender(defender, new Point(180, 200), 0, true);
			d[2] = new Defender(defender, new Point(260, 250), 0);
			d[3] = new Defender(defender, new Point(180, 100), 0, true);
			d[4] = new Defender(defender, new Point(260, 100), 0);

			// midfielders
			d[5] = new Defender(defender, new Point(100, 310), 0, true);
			d[6] = new Defender(defender, new Point(190, 310), 0, true);
			d[7] = new Defender(defender, new Point(280, 310), 0, true);
			d[8] = new Defender(defender, new Point(360, 310), 0, true);

			// Strikers
			d[9] = new Defender(defender, new Point(100, 430), 0);
			d[10] = new Defender(defender, new Point(150, 430), 0);
			return d;
		}
		
		/**
		 * for an easy game we have 18 players on the field all moving at around
		 * 5.5
		 * @return an array of defenders
		 */
		private Defender[] generateMediumMap() {
			Point[] defender = new Point[4];

			Point defender1 = new Point(0, 0);
			Point defender2 = new Point(10, 0);
			Point defender3 = new Point(10, 10);
			Point defender4 = new Point(0, 10);
			defender[0] = defender1;
			defender[1] = defender2;
			defender[2] = defender3;
			defender[3] = defender4;
			Defender[] d = new Defender[18];
			d[0] = new Defender(defender, new Point(50, 100), 0, true);
			d[1] = new Defender(defender, new Point(225, 100), 0);
			d[2] = new Defender(defender, new Point(350, 120), 0, true);
			d[3] = new Defender(defender, new Point(475, 120), 0);
			d[4] = new Defender(defender, new Point(600, 100), 0, true);

			d[5] = new Defender(defender, new Point(150, 210), 0, true);
			d[6] = new Defender(defender, new Point(350, 210), 0);
			d[7] = new Defender(defender, new Point(550, 260), 0, true);
			d[8] = new Defender(defender, new Point(650, 260), 0);

			d[9] = new Defender(defender, new Point(50, 320), 0, true);
			d[10] = new Defender(defender, new Point(225, 320), 0);
			d[11] = new Defender(defender, new Point(350, 350), 0, true);
			d[12] = new Defender(defender, new Point(475, 350), 0);
			d[13] = new Defender(defender, new Point(600, 320), 0, true);

			d[14] = new Defender(defender, new Point(150, 460), 0, true);
			d[15] = new Defender(defender, new Point(350, 460), 0);
			d[16] = new Defender(defender, new Point(550, 470), 0, true);
			d[17] = new Defender(defender, new Point(650, 470), 0);

			return d;

		}
		
		/**
		 * for an easy game we have 27 players on the field all moving at around
		 * 7.5
		 * @return an array of defenders
		 */
		private Defender[] generateHardMap() {
			Point[] defender = new Point[4];

			Point defender1 = new Point(0, 0);
			Point defender2 = new Point(10, 0);
			Point defender3 = new Point(10, 10);
			Point defender4 = new Point(0, 10);
			defender[0] = defender1;
			defender[1] = defender2;
			defender[2] = defender3;
			defender[3] = defender4;
			Defender[] d = new Defender[27];
			d[0] = new Defender(defender, new Point(20, 65), 0, true);
			d[1] = new Defender(defender, new Point(220, 65), 0);
			d[2] = new Defender(defender, new Point(420, 65), 0, true);
			d[3] = new Defender(defender, new Point(620, 65), 0);
			d[4] = new Defender(defender, new Point(750, 65), 0, true);

			d[5] = new Defender(defender, new Point(110, 165), 0, true);
			d[6] = new Defender(defender, new Point(310, 165), 0);
			d[7] = new Defender(defender, new Point(510, 165), 0, true);
			d[8] = new Defender(defender, new Point(660, 165), 0);

			d[9] = new Defender(defender, new Point(20, 265), 0, true);
			d[10] = new Defender(defender, new Point(220, 265), 0);
			d[11] = new Defender(defender, new Point(420, 265), 0, true);
			d[12] = new Defender(defender, new Point(620, 265), 0);
			d[13] = new Defender(defender, new Point(750, 265), 0, true);

			d[14] = new Defender(defender, new Point(110, 360), 0, true);
			d[15] = new Defender(defender, new Point(310, 360), 0);
			d[16] = new Defender(defender, new Point(510, 360), 0, true);
			d[17] = new Defender(defender, new Point(660, 360), 0);

			d[18] = new Defender(defender, new Point(20, 425), 0, true);
			d[19] = new Defender(defender, new Point(220, 425), 0);
			d[20] = new Defender(defender, new Point(420, 425), 0, true);
			d[21] = new Defender(defender, new Point(620, 425), 0);
			d[22] = new Defender(defender, new Point(750, 426), 0, true);

			d[23] = new Defender(defender, new Point(110, 475), 0, true);
			d[24] = new Defender(defender, new Point(310, 475), 0);
			d[25] = new Defender(defender, new Point(510, 475), 0, true);
			d[26] = new Defender(defender, new Point(660, 475), 0);

			return d;

		}

	}
}
