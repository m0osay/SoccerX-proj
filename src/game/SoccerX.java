package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author Nathan Kidanemariam and Moosay Hailewold
 * SoccerX is the heart of our Code as it serves as the frame where all of our
 * objects get instantialized, painted, and then updated. 
 *
 */
class SoccerX extends Game {
	
	//Instance Variables that represent our 3 elements, a timer, and the values
	static int counter = 0;
	private SoccerBall e;
	private Goal g;
	private Defender[] d;
	private Timer t;
	private int second = 59;
	private int minute = 3;
	
	/**
	 * this is the constructor which calls the super class (Game) and
	 * instantiates it with the name of the frame, in our case, SoccerX, and a 
	 * width of 800 and a height of 600. Then it creates the first set of points
	 * for the soccer ball which is to be shaped like an octagon or something 
	 * roughly circular. It creates our goal which is rectangular. It also
	 * creates our defender which is a square. Additionally it sets our timer
	 * with a 1s or 1000 ms delay. Since it is a countdown timer we automatically
	 * set the seconds to 59 and the minutes to 3 and made it decrement from there.
	 */
	public SoccerX() {
		super("YourGameName!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		
		//creation of ball
		Point p1 = new Point(8, 12);
		Point p2 = new Point(12, 8);
		Point p3 = new Point(18, 8);
		Point p4 = new Point(22, 12);
		Point p5 = new Point(22, 18);
		Point p6 = new Point(18, 22);
		Point p7 = new Point(12, 22);
		Point p8 = new Point(8, 18);
		
		
		Point[] p = new Point[8];
		p[0] = p1;
		p[1] = p2;
		p[2] = p3;
		p[3] = p4;
		p[4] = p5;
		p[5] = p6;
		p[6] = p7;
		p[7] = p8;
		e = new SoccerBall(p, new Point(385, 500), 50);
		
		//creation of goal
		Point goal1 = new Point(5, 10);
		Point goal2 = new Point(100, 10);
		Point goal3 = new Point(100, 20);
		Point goal4 = new Point(5, 20);

		Point[] goalKeeper = new Point[4];
		goalKeeper[0] = goal1;
		goalKeeper[1] = goal2;
		goalKeeper[2] = goal3;
		goalKeeper[3] = goal4;

		g = new Goal(goalKeeper, new Point(200, 5), 0);
		Goal.setInitialVelocity(1);
		
		//creation of defender. Map method in defender class makes an array.
		Defender defenderx = new Defender();
		d = defenderx.new Level().map();

		t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;

				if (second == -1) {
					second = 59;
					minute--;
				}
				repaint();

				if (second == 0 && minute == 0) {
					t.stop();
				}
			}
		});
		t.start();
	}
	/**
	 * The paint method paints the background green and increments the counter
	 * additionally for each of our objects their respective paint methods are 
	 * called so that we can see them on the screen and their move methods are
	 * called so that we can move them on the screen. Depending on the level we
	 * use timers to determine how long the game should go. 
	 * @param Brush
	 */
	public void paint(Graphics brush) {
		brush.setColor(Color.GREEN);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.WHITE);
		brush.drawString("Counter is " + counter, 10, 10);
		
		//paints our goalkeeper elements and our ball
		e.paint(brush);
		e.move();
		e.paint(brush);
		g.paint(brush);
		g.move();
		g.paint(brush);
		
		/*
		 * if the level is set to hard then depending on the the number the timer
		 * is on the drawString method is called to accurately portray the time
		 * so if minutes and seconds are less than 10 we attach a zero to the front
		 * otherwise we don't
		 */
		if (LevelGUI.getSelectedLevel().equalsIgnoreCase("hard")) {
			if (minute < 10) {
				if (second < 10) {
					brush.drawString("Timer: " + "0" + minute + ":" + "0" + second, 300, 10);
				} else {
					brush.drawString("Timer: " + "0" + minute + ":" + second, 300, 10);
				}
			} else {
				if (second < 10) {
					brush.drawString("Timer: " + minute + ":" + "0" + second, 300, 10);
				}
				brush.drawString("Timer: " + minute + ":" + second, 300, 10);
			}
		}
		
		/*
		 * since we have an array of defenders we can't paint them like we did
		 * with the ball and the defenders and doing them all hand by hand is
		 * inefficent so we used a loop that goes through each defender and 
		 * paints them and moves them.
		 */
		for (int i = 0; i < d.length; i++) {
			d[i].paint(brush);
			d[i].move();
			d[i].paint(brush);
		}
		
		/**
		 * if our ball collides with the goal then we increase the goal and 
		 * print out a goal.
		 */
		if (e.collisionHandler(g)) {
			e.point.increaseGoals();
			JOptionPane.showMessageDialog(null, "GOOOOOOOOOOAL!");
		}
		
		/**
		 * in the case that the ball has crashed with a defender we loop through
		 * each defender and first we decrease the user's goal but we add one
		 * to every single defender allowing the paint method to show us the 
		 * updated score rather than many numbers on top of each other. This
		 * kept happening because each defenders score needed to be updated since
		 * each of them have individual scores but now they're a team.
		 */
		for (int i = 0; i < d.length; i++) {
			if (e.collisionHandler(d[i])) {
				if (e.point.getGoals() > 0) {
					e.point.decreaseGoals();
				}
				for (int x = 0; x < d.length; x++) {
					d[x].point.increaseGoals();
				}
				JOptionPane.showMessageDialog(null, "Defender Hit, Resetting");
			}
		}
		
		/**
		 * if the level is easy or medium the game is played as a best of 5 and
		 * depending on the score a panel pops open with who won and the score.
		 * If it's hard we play until the timer runs out.
		 */
		if (LevelGUI.getSelectedLevel().equalsIgnoreCase("easy")
				|| (LevelGUI.getSelectedLevel().equalsIgnoreCase("medium"))) {
			for (int x = 0; x < d.length; x++) {
				if (e.point.goalsScored == 5 || d[x].point.goalsScored == 5) {
					if (d[x].point.getGoals() > e.point.goalsScored) {
						JOptionPane.showMessageDialog(null,
								"Team 1 Wins! Final Score: " + d[x].point.getGoals() + "-" + e.point.goalsScored);
						System.exit(0);

					} else {
						JOptionPane.showMessageDialog(null,
								"You Win! Final Score: " + d[x].point.getGoals() + "-" + e.point.goalsScored);
						System.exit(0);
					}
				}
			}
		} else if (LevelGUI.getSelectedLevel().equalsIgnoreCase("hard")) {
			if (minute == 0 && second == 0) {
				for (int x = 0; x < d.length; x++) {
					if (e.point.goalsScored > d[x].point.goalsScored) {
						JOptionPane.showMessageDialog(null,
								"You Win! Final Score: " + d[x].point.getGoals() + "-" + e.point.goalsScored);
						System.exit(0);
					}else if(e.point.goalsScored < d[x].point.goalsScored) {
						JOptionPane.showMessageDialog(null,
								"Team 1 Wins! Final Score: " + d[x].point.getGoals() + "-" + e.point.goalsScored);
						System.exit(0);

					}else {
						JOptionPane.showMessageDialog(null,
								"Tie! Final Score: " + d[x].point.getGoals() + "-" + e.point.goalsScored);
						System.exit(0);
					}
				}
			}
		}

		{

		}
		this.addKeyListener(e);
	}

	public static void main(String[] args) {
		HomeScreen h = new HomeScreen();
	}
}