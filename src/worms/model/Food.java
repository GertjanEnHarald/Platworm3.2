package worms.model;

public class Food extends GameObject {

	/**
	 * Constructor to make a new food.
	 * 
	 * @param 	coordinateX
	 * 			The x coordinate for this new food.
	 * @param 	coordinateY
	 * 			The y coordinate for this new food.
	 * @param	radius
	 * 			The radius for this new food.
	 * @param 	isActive
	 * 			The status for this new food.
	 * @param 	world
	 * 			The world for this new food.
	 * 
	 * @post	The new x coordinate of this food will be equal to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX
	 * @post	The new y coordinate of this food will be equal to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY
	 * @post	The new status of this food will be equal to the given isActive.
	 * 			| new.getStatus() == isActive
	 * @post	The new world of this food will be equal to the given world.
	 * 			| new.getWorld() == world
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if one or more of the given parameters are illegal 
	 * 			assignments for this food.
	 * 			| (! isValidCoordinate(coordinateX)) || (! isValidCoordinate(coordinateY))
	 * 			|		|| (! isValidRadius(radius))
	 */
	public Food(double coordinateX, double coordinateY, boolean isActive, World world) {
		super(coordinateX, coordinateY, isActive, 0.20,world);
	}

	
	/**
	 * Returns whether this food has a valid radius.
	 */
	@Override
	public boolean isValidRadius(double radius) {
		return true;
	}

	@Override
	protected Food clone() throws CloneNotSupportedException {
		return (Food) super.clone();
	}
	
}
