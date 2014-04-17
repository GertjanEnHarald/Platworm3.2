package worms.model;


import be.kuleuven.cs.som.annotate.*;

public abstract class GameObject {
	

	private double coordinateX;
	private double coordinateY;
	private double radius;
	private boolean isActive;
	private World world;
	private static final double g=9.80665;
	
	
	/**
	 * 
	 * @param 	coordinateX
	 * 			The x coordinate for this new game object.
	 * @param 	coordinateY
	 * 			The y coordinate for this new game object.
	 * @param	radius
	 * 			The radius for this new game object.
	 * @param 	isActive
	 * 			The status for this new game object.
	 * @param 	world
	 * 			The world for this new game object.
	 * 
	 * @post	The new x coordinate of this game object will be equal to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX
	 * @post	The new y coordinate of this game object will be equal to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY
	 * @post	The new status of this game object will be equal to the given isActive.
	 * 			| new.getStatus() == isActive
	 * @post	The new world of this game object will be equal to the given world.
	 * 			| new.getWorld() == world
	 */
	public GameObject(double coordinateX, double  coordinateY, boolean isActive, double radius, World world) {
		this.setWorld(world);
		this.setCoordinates(coordinateX, coordinateY);
		this.setRadius(radius);
		this.setStatus(isActive);
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
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public boolean hasProperWorld() {
		return (this.getWorld() == null) || (this.getWorld().getGameObjects().contains(this));
	}
	
	public void terminate() {
		this.setStatus(false);
		this.getWorld().removeAsGameObject(this);
		this.setWorld(null);
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
	
	
	
	
	protected void setCoordinates(double x, double y){
		setX(x);
		setY(y);
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
	protected void setX(double coordinateX) throws ModelException {
		if (!isValidCoordinate(coordinateX))
			throw new ModelException("Illegal X coordinate!");
		if (isXCoordinateOutOfBounds(coordinateX)){
			this.coordinateX = coordinateX;
			this.terminate();
			}
		else
			this.coordinateX = coordinateX;
	}

	protected boolean isXCoordinateOutOfBounds(double coordinateX) {
		return (coordinateX < 0) || (coordinateX > this.getWorld().getWidth());
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
	protected void setY(double coordinateY) throws ModelException {
		if (!isValidCoordinate(coordinateY))
			
			throw new ModelException("Illegal Y coordinate!");
		if (isYCoordinateOutOfBounds(coordinateY)){
			this.coordinateY = coordinateY;
			this.terminate();
			}
		else
			this.coordinateY = coordinateY;
	}

	protected boolean isYCoordinateOutOfBounds(double coordinateY) {
		return (coordinateY < 0) || (coordinateY > this.getWorld().getHeight());
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
	
	

	protected boolean Overlaps(GameObject obj) {
		double x1 = this.getCoordinateX();
		double y1 = this.getCoordinateY();
		double x2 = obj.getCoordinateX();
		double y2 = obj.getCoordinateY();
		
		double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return distance < (this.getRadius() + obj.getRadius()); 
	}
	
}
