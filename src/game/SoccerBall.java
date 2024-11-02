package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * @author Nathan Kidanemariam and Moosay Hailewold
 * Represents the ball in our program. User can control this ball and must 
 * dribble up the field and score.
 *
 */
public class SoccerBall extends Polygon implements KeyListener, MoveableCharacter {
	//Instance Variables for various functions of our ball
	private boolean forward;
	private boolean left;
	private boolean right;
	private boolean back;
	private boolean shoot;
	public SoccerBall.points point;
	private boolean collided;
	private Timer wait;
	private Point prevPos;
	private boolean stop;
	
	/**
	 * Constructor creates our polygon with an array of points and sets the
	 * starting position of the ball and how much it should rotate. Also creates
	 * an instance of the inner class which holds the points for the player as
	 * well as a copy of the starting position for resetting during collision
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 */
	public SoccerBall(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		point = new SoccerBall.points();
		prevPos = new Point(position.x, position.y);
	}
	
	/**
	 * This Method paints our ball onto the screen using a passed in Graphics
	 * variable. From the array of points passed in it creates an int array of
	 * x and y coordinates and fills the polygon. It also sets the ball to black
	 * creates a font so that we can paint how many goals were scored by the 
	 * user.
	 * @param brush
	 */
	public void paint(Graphics brush) {
		Point[] points = this.getPoints();

		int[] xCoordinates = new int[points.length];
		int[] yCoordinates = new int[points.length];

		for (int i = 0; i < points.length; i++) {
			xCoordinates[i] = (int) points[i].getX();
			yCoordinates[i] = (int) points[i].getY();
		}

		brush.fillPolygon(xCoordinates, yCoordinates, points.length);
		brush.setColor(Color.black);
		Font f = new Font("BOLD", Font.BOLD, 15);
		brush.setFont(f);
		brush.drawString("Goals: " + point.goalsScored, 725, 11);
	}
	
	/**
	 * As long as the object hasn't been stopped such as when resetting this 
	 * method moves the object forward, backward, and sideways, depending on the
	 * keyboard input of the user. The shoot, back, right, and left movements 
	 * also have a bounds check and when they hit the walls the opposite side
	 * is set to true to make it "bounce" off and from there the user can 
	 * redirect it.
	 */
	public void move() {
		if (!stop) {
			if (forward) {
				double stepSize = 1.5;

				double yCoordinate = stepSize * Math.sin(Math.toRadians(rotation));

				if (position.getY() - yCoordinate > 100) {
					position.setY(position.getY() - yCoordinate);
				}
			}

			if (shoot) {
				double stepSize = 10;
				double yCoordinate = stepSize * Math.sin(Math.toRadians(rotation));
				
				/**
				 * shoot is set to false to avoid the ball shooting again and 
				 * again. once it crashes with the top boarder back is set
				 * to true so it can "bounce" back and go left.
				 */
				if (position.getY() - yCoordinate <= 0) {
					yCoordinate *= -1;
					shoot = false;
					back = true;
					left = true;
				}

				if (position.getY() - yCoordinate >= 0) {
					position.setY(position.getY() - yCoordinate);
				}
			}

			if (left) {
				double stepSize = 1.5;
				double xCoordinate = stepSize * Math.cos(Math.toRadians(rotation));
				
				/**
				 * bouncing effect if it crashes on the left boarder it 
				 * immidiately goes right
				 */
				if (position.getX() - xCoordinate <= 0) {
					xCoordinate *= -1;
					right = true;
					left = false;
				}

				if (position.getX() - xCoordinate >= 0) {
					position.setX(position.getX() - xCoordinate);
				}
			}

			if (right) {
				double stepSize = 1.5;
				double xCoordinate = stepSize * Math.cos(Math.toRadians(rotation));
				
				/**
				 * immidiately goes left.
				 */
				if (position.getX() + xCoordinate >= 780) {
					xCoordinate *= -1;
					left = true;
					right = false;
				}

				if (position.getX() + xCoordinate <= 780) {
					position.setX(position.getX() + xCoordinate);
				}
			}

			if (back) {
				double stepSize = 1.5;
				double yCoordinate = stepSize * Math.sin(Math.toRadians(rotation));
				
				/**
				 * changes direction and goes forward
				 */
				if (position.getY() + yCoordinate >= 550) {
					yCoordinate *= -1;
					forward = true;
					back = false;
				}

				if (position.getY() + yCoordinate <= 550) {
					position.setY(position.getY() + yCoordinate);
				}

			}
		}
	}
	
	/**
	 * When a key is pressed namely up, down, space, right, left, then the other
	 * respective values are set to true.
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			forward = true;
			left = false;
			back = false;
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			forward = false;
			back = false;
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			left = false;
			forward = false;
			back = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			back = true;
			forward = false;
			left = false;
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			shoot = true;
		}
	}
	
	/**
	 * when they are released then the respective key is set to false.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			forward = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			back = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
	
	/**
	 * In the collisionHandler we make an array of points that correspond to the
	 * other polygon and if it's an instance of either goal or defender we deal
	 * with it by calling the contains method in polygon, and if it says that 
	 * we hit the vertice of the goal then we reset the ball turn off shoot
	 * and set collided to true when this returns the paint method in soccerX 
	 * stops the game to print out the collision message. If it's a defender 
	 * then stop is turned to true via the method stopMoving so that the user 
	 * can't move until they press ok on the JPanel.
	 * @param p
	 */
	public boolean collisionHandler(Polygon p) {
		Point[] other = p.getPoints();
		if (p instanceof Goal && !collided) {
			for (int i = 0; i < other.length; i++) {
				if (contains(other[i])) {
					//if collision occurs reset and cool down 
					position.setX(prevPos.x);
					position.setY(prevPos.y);
					shoot = false;
					collided = true;
					isCooling();
					return true;
				}
			}
		} else if (p instanceof Defender) {
			for (int i = 0; i < other.length; i++) {
				if (contains(other[i])) {
					position.setX(prevPos.x);
					position.setY(prevPos.y);
					collided = true;
					shoot = false;
					stopMoving();
					isCooling();
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * turns collided to true
	 */
	public void reset() {
		collided = false;
	}
	/**
	 * turns stop to true
	 */
	public void stopMoving() {
		stop = true;
	}
	
	/**
	 * creates a timer and a timerTask anonymous class so that when we shoot the
	 * ball and it the vertices of the goal it only dectects one collision and
	 * not 4 or 5 since we don't want our points method increasing by more than 
	 * 1.
	 */
	private void isCooling() {
		wait = new Timer();
		wait.schedule(new TimerTask() {
			public void run() {
				reset();
				stop = false;
				wait.cancel();
			}
		}, 1000);
	}
	
	/**
	 * 
	 * @author Nathan Kidanemariam and Moosay Hailewold
	 * contains the amount of goals scored by the user.
	 * static because we don't want to create an element object to access the 
	 * points.
	 */
	public static class points {
		public int goalsScored; 
		
		/**
		 * adds one to the goalsscored instance variable
		 */
		public void increaseGoals() {
			goalsScored += 1;
		}
		
		/**
		 * returns the goalsscored instance variable
		 */
		public int getGoals() {
			return goalsScored;
		}
		
		/**
		 * subtracts one to the goalsscored instance variable
		 */
		public void decreaseGoals() {
			goalsScored -= 1;
		}
	}
}
