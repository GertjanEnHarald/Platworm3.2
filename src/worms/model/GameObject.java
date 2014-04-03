package worms.model;


import be.kuleuven.cs.som.annotate.*;

public abstract class GameObject {
	

	private double coordinateX;
	private double coordinateY;
	private double radius;
	private boolean isActive;
	private final World world;
	private static final double g=9.80665;
	
	public GameObject(double coordinateX, double  coordinateY, boolean isActive, double radius, World world) {
		this.setCoordinateX(coordinateX);
		this.setCoordinateY(coordinateY);
		this.setRadius(radius);
		this.world = world;
	}
	
	/**
	 *Returns the gravity used in this game. 
	 */
	@Basic
	@Immutable
	@Raw
	public static final double getGravity(){
		return g;
	}
	
	/**
	 * Returns the world in which this object is.
	 */
	@Basic
	@Raw
	@Immutable
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Returns the X coordinate of the position of this game object.
	 */
	@Basic
	@Raw
	public double getCoordinateX(){
		return this.coordinateX;
	}
	
	
	/**
	 * Returns the Y coordinate of the position of this game object.			
	 */
	@Basic
	@Raw
	public double getCoordinateY(){
		return this.coordinateY;
	}
	
	
	/**
	 * Set the x coordinate of this game object to the given coordinateX.
	 * 
	 * 
	 * @param 	coordinateX
	 * 			The new x coordinate of this game object.
	 * 
	 * @post	The new x coordinate of this game object is equal to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX 
	 * @throws	ModelException 
	 * 			The given coordinateX is not a valid coordinate for a game object.
	 *       	| ! isValidCoordinate(coordinateX)
	 */
	@Raw
	protected void setCoordinateX(double coordinateX) throws ModelException {
		if (!isValidCoordinate(coordinateX))
			throw new ModelException("Illegal X coordinate!");
		this.coordinateX = coordinateX;
	}
	
	
	/**
	 * Set the y coordinate of this game object to the given coordinateY.
	 * 
	 * 
	 * @param 	coordinateY
	 * 			The new y coordinate of this game object.
	 * 
	 * @post	The new y coordinate of this game object is equal to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY 
	 * @throws	ModelException 
	 * 			The given coordinateY is not a valid coordinate for a game object.
	 *       	| ! isValidCoordinate(coordinateY)
	 */
	@Raw
	protected void setCoordinateY(double coordinateY) throws ModelException {
		if (!isValidCoordinate(coordinateY))
			throw new ModelException("Illegal Y coordinate!");
		this.coordinateY = coordinateY;
	}
	
	
	/**
	 * Check whether the given coordinate is a valid coordinate for a game object.
	 * 
	 * 
	 * @param  	coordinate
	 *         	The coordinate to check.
	 *         
	 * @return 	True if and only if the given coordinate is a number or infinity.
	 *       	| result == (! Double.isNaN(coordinate))
	 */
	@Raw
	public static boolean isValidCoordinate(double coordinate) {
		return ! Double.isNaN(coordinate);
	}
	
	
	
	
	/**
	 * Returns the current radius of this game object.
	 */
	@Basic
	@Raw
	public double getRadius(){
		return this.radius;
	}

	
	/**
	 * Set the radius of this game object to the given radius.
	 * 
	 * 
	 * @param 	radius
	 * 			The new radius of this game object.
	 * 
	 * @post	The new radius of this game object is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @throws 	ModelException
	 * 			The given radius for this game object is not a legal radius.
	 * 			| ! isValidRadius(radius)
	 */
	public void setRadius(double radius) throws ModelException {
		if (! isValidRadius(radius))
			throw new ModelException("Illegal radius!");
		this.radius = radius;
	}
	
	
	public abstract boolean isValidRadius(double radius);
	
	
	
	
	/**
	 * Returns whether a game object is currently active or not.
	 */
	public boolean getStatus() {
		return this.isActive;
	}
	
	/**
	 * Set the status of a game object to the given status.
	 * 
	 * 
	 * @param 	status
	 * 			The new status of this game object.
	 * 
	 * 
	 * @post	The new status of this game object is equal to the given status.
	 * 			| new.getStatus() == status
	 */
	protected void setStatus(boolean status) {
		this.isActive = status;
	}
	
	
}
