package game;

import java.awt.Graphics;
/**
 * 
 * @author Nathan Kidanemariam and Moosay Hailewold
 * Represents our GoalKeeper in the game. This is not a moveable character 
 * although it does move, the user can't move it. 
 *
 */
public class Goal extends Polygon {
    private static double velocity_X; // Velocity in the horizontal direction, we can make it depend on the level
    private double min_X; // Minimum X position (left boundary)
    private double max_X = 800; // Maximum X position (right boundary)
    
    /**
     * Constructor which calls our superclass constructor to make our shape
     * @param inShape
     * @param inPosition
     * @param inRotation
     */
    public Goal(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
        // Set default values for velocityX, minX, and maxX
    }
    /**
	 * This Method paints our Goal onto the screen using a passed in Graphics
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
        
    }
    
    /**
     * this method changes the velocity of the goal.
     */
    public void move() {
        // Update the X position of the goal based on its velocity
    	  double newX = position.getX() + velocity_X;
    	  double rightX = getRightX() + velocity_X; //stops out of bound for right
    	  

        //bound check
    	  if (newX < min_X || rightX > max_X ){
    	        velocity_X = -velocity_X;
    	    }

        position.setX(newX);
    }
    

    /**
     * created a getRightX method that instead of using the offset(center) 
     * coordinate for the shape, we find the right most biggest point and use 
     * that as offset point when checking if the goal doesn’t go out of bound
     * @return the right most point
     */
    public double getRightX() {
    	 Point[] points = getPoints();
         double max_X = points[0].getX();
         for (int i = 0; i < points.length; i++) {
             if ((int)points[i].getX() > (int)max_X) {
                 max_X = points[i].getX();
             }
         }
         return max_X;
    }



    public static void setInitialVelocity(double initialVelocityX) {
        velocity_X = initialVelocityX;
    }
}

