package game;

import java.awt.Graphics;

/**
 * 
 * @author Nathan Kidanemariam and Moosay Hailewold
 * Interface for anything that moves in our code, mainly used with the ball
 *
 */
public interface MoveableCharacter {
	public void move();
	public void paint(Graphics brush);
	public boolean collisionHandler(Polygon p);
}