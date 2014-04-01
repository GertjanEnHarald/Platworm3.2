package worms.model;

import java.util.*;
import java.util.Random;

/**
 * A class describing the world in which the game worms takes place.
 * 
 * @author The Originator
 *
 *
 *@invar	Checks if width of world is valid.	
 *			|isValidDimension(this.getWidth())
 *@invar	Checks if height of world is valid.	
 *			|isValidDimension(this.getHeight())
 *@invar	World contains at most one live projectile.
 *			| ...
 *@invar	Each object appears only once in this world.
 *			| ...
 *@invar	The position of all objects must be within the bounds of the world.
 *			| ...
 */
public class World {
	
	/**
	 * Variable initialization.
	 */
	private static double maxDimension=Double.MAX_VALUE;
	private double height;
	private double width;
	private final boolean[][] passableMap;
	private boolean status;
	private Random random;
	private final List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public World(double width, double height,boolean[][] passableMap, Random random) 
			throws ModelException {
		this.setHeight(height);
		this.setWidth(width);
		this.passableMap = passableMap;
		this.random = random; 
	}
	
	/**
	 * Returns the random seed of this world.
	 */
	public Random getRandom(){
		return this.random;
	}
	
	
	/**
	 * Returns the upper bound for the dimensions of a world.
	 */
	public static double getMaxDimension(){
		return maxDimension;
	}
	
	/**
	 * Returns whether the game is active.
	 */
	public boolean getStatus(){
		return this.status;
	}
	
	/**
	 * Returns the width of this world.
	 */
	public double getWidth(){
		return this.width;
	}
	
	
	/**
	 * Returns the height of this world.
	 */
	public double getHeight(){
		return this.height;
	}
	
	
	/**
	 * Set the height of this world.
	 * 
	 * 
	 * @param 	height
	 * 			Height to be assigned to world
	 * 
	 * @post	The height has been set.
	 * 			| new.getHeight() == height
	 * @throws	 ModelException
	 * 			The dimension is invalid
	 * 			|!isValidDimension(height)
	 */
	public void setHeight(double height) throws ModelException{
		if (!isValidDimension(height))
			throw new ModelException("Invalid height for world!");
		this.height = height;
	}
	
	
	
	/**
	 * Set the width of this world.
	 * 
	 * 
	 * @param 	width
	 * 			Width to be assigned to world
	 * 
	 * @post	The width has been set.
	 * 			| new.getWidth() == width
	 * @throws	 ModelException
	 * 			The dimension is invalid
	 * 			|!isValidDimension(width)
	 */
	public void setWidth(double width){
		if (!isValidDimension(width))
			throw new ModelException("Invalid width for world!");
		this.width = width;
	}
	
	
	/**
	 * Checks if given dimension is valid.
	 * 
	 * 
	 * @param 	dimension
	 * 			dimension to be checked
	 * 
	 * @return	Returns whether the dimension is positive and below the upper bound.
	 * 			| result == 0 <= dimension && dimension <= getMaxDimension()
	 */	
	public static boolean isValidDimension(double dimension){
		return 0 <= dimension && dimension <= getMaxDimension();
	}
	
	
	/**
	 * Checks if a given pixel of this world is passable 
	 * 
	 * @param 	row
	 * 			The row in which the pixel is.
	 * @param	column
	 * 			The column in which the pixel is.
	 * @return	
	 * 			|result == this.passableMap[row][column]
	 */
	public boolean isPassablePixel(int row, int column){
		return this.passableMap[row][column];
	}
	
	/**
	 * Returns the vertical or horizontal dimension of this world in a number of pixels.
	 * 
	 * @param 	heightOrWidth
	 * 			This boolean signifies whether or not to return the vertical or horizontal 
	 * 			dimension(true for horizontal and false for vertical).
	 * 
	 * @return	Returns the dimension wanted.
	 * 			|if (heightOrWidth)
	 * 			|	result == this.passableMap[0].length
	 * 			|else 
	 * 			|	this.passableMap.length
	 */
	public int getDimensionInPixels(boolean heightOrWidth){
		if (heightOrWidth)
			return this.passableMap[0].length;
		else
			return this.passableMap.length;
	}
	
	/**
	 * Checks if a given location is passable.
	 * 
	 * @param 	x
	 * 			The x coordinate of the location to check.
	 * @param 	y
	 * 			The y coordinate of the location to check.
	 * @return	Returns whether the location is passable.
	 * 			|pixelHeight = (getHeight()/getDimensionInPixels(false))
	 * 			|pixelWidth = (getWidth()/getDimensionInPixels(true))
	 *			| result == isPassablePixel((int)(y/pixelHeight),(int)(x/pixelWidth))
	 */
	public boolean isPassableLocation(double x, double y){
		double pixelHeight = (getHeight()/getDimensionInPixels(false));
		double pixelWidth = (getWidth()/getDimensionInPixels(true));
		return isPassablePixel((int)(y/pixelHeight),(int)(x/pixelWidth));
	}
	
	
	/**
	 * Checks if a circular area with a given radius with the centre
	 *  on the given locations x and y is passable.
	 *  
	 *  
	 *  Rare methode, waarschijnlijk fout dus documentatie laat voor later.
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public boolean isPassableArea(double x, double y, double radius){
		for(double angle=0; angle<=2*Math.PI; angle= angle + Math.PI/8.0){
			for(double distance=0; distance<=radius;distance = distance + radius*0.05 ){
				if (!(isPassableLocation(x+Math.sin(angle)*distance,y+Math.cos(angle)*distance))){
					return false;
				}
			}
			}
		return true;
	}
	
	
	/**
	 * Checks if entity at location is adjacent to impassable terrain.
	 * 
	 * 
	 * @param 	x
	 * 			x coordinate of loaction
	 * @param 	y
	 * 			y coordinate of location
	 * @param 	radius
	 * 			radius of entity
	 * @return	Returns whethe entity at location is adjacent to impassable terrain.
	 * 			| result  == isPassableArea(x,y,1.1*radius)
	 */
	public boolean isAdjacent(double x, double y,double radius){
		return !isPassableArea(x,y,1.1*radius) && isPassableArea(x,y,radius);
	}
	
	
	
	
	
}
