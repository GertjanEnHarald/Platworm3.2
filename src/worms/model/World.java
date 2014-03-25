package worms.model;

/**
 * A class describing the world in which the game worms takes place.
 * 
 * @author The Originator
 *
 *
 *@invar	Checks if width of world is valid.	
 *			|isValidDimension(this.getWidth())
 *
 *@invar	Checks if height of world is valid.	
 *			|isValidDimension(this.getHeight())
 */
public class World {
	
	/**
	 * Variable initialization.
	 */
	private static double maxDimension=Double.MAX_VALUE;
	private double height;
	private double width;
	
	
	/**
	 * Returns the upper bound for the dimensions of a world.
	 */
	public static double getMaxDimension(){
		return maxDimension;
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
	private void setHeight(double height) throws ModelException{
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
	private void setWidth(double width){
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
}
