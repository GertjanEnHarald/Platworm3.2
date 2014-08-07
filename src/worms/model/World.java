package worms.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import worms.util.Util;

/**
 * A class describing the world in which the game worms takes place.
 * 
 * @version 2.18
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 *
 * @invar	This world has valid dimensions.	
 *			|isValidDimension(this.getWidth()) && isValidDimension(this.getHeight())
 * @invar	Each game object appears only once in this world.
 * 			| for each object1 in this.getGameObjects()
 * 			|	for each object2 in this.getGameObjects()
 * 			|		object1 != object2 || this.getGameObjects().getIndex(object1) == this.getGameObjects().getIndex(object2)
 * @invar	The position of all objects must be within the bounds of the world.
 *			| for each object in this.getGameObjects()
 *			|	this.isInWorld(object.getCoordinateX(),object.getCoordinateY(),object.getRadius())
 * @invar	The world has proper game objects.
 *			| this.hasProperGameObjects()
 * @invar	The world has proper teams.
 *			| this.hasProperTeams()
 */
public class World implements Cloneable {
	
	
	
	 //
	 // Variable initialization
	 //
	
	/**
	 *The gravity working in this world
	 */
	private final double g=9.80665;
	/**
	 * The maximum dimension(for width or height allowed in this world)
	 */
	private final static double maxDimension=Double.MAX_VALUE;
	/**
	 * The height of this world.
	 */
	private double height;
	/**
	 * The width of this world.
	 */
	private double width;
	/**
	 * The Boolean map respresnting whether the corresponding pixels of the
	 * map are passable.
	 */
	private final boolean[][] passableMap;
	/**
	 * A boolean representing whether the game has started.
	 */
	private boolean status;
	/**
	 * The random seeded used to generate random numbers.
	 */
	private final Random random;
	/**
	 * The list containing all the game objects that are in this world.
	 */
	private final List<GameObject> gameObjects = new ArrayList<GameObject>();
	/**
	 * The index of the active worm in the list of all worms.
	 */
	private int indexOfActiveWorm=0;
	/**
	 * A list containing all the teams that are in this world.
	 */
	private final List<Team> teams = new ArrayList<Team>();
	/**
	 * A list of random names that are used for the worms created in this world.
	 */
	private final List<String> randomNames = Arrays.asList("Bob", "Emmitt","Parker", "Sergio", "Elias", "Clifton",
		    "Gregg", "Derick", "Porter", "Archie", "Robbie", "Salvador", "Erich", "Wilfredo", "Casey",
		    "Sung", "Christopher", "Jude", "Logan", "Roosevelt", "Rich");
	
	
	
	
	
	
	
	
	
	//
	// Constructor
	//
	
	/**
	 * Constructor of the world.
	 * 
	 * @param 	width
	 * 			The width for this new world.
	 * @param 	height
	 * 			The height for this new world.
	 * @param 	passableMap
	 * 			The passable map for this new world.
	 * @param 	random
	 * 			The random for this new world.
	 * 
	 * @post	The new width of this world is equal to the given width.
	 * 			| new.getWidth() == width
	 * @post	The new height of this world is equal to the given height.
	 * 			| new.getHeight() == height
	 * @post	The new passable map of this world is equal to the given map.
	 * 			| new.getPassableMap() == passableMap
	 * @post	The new random of this world is equal to the given random.
	 * 			| new.getRandom() == random
	 * 
	 * @throws 	ModelException
	 * 			The exception is thrown if height or width aren't valid dimension.
	 * 			| isValidDimension(width) || isValidDimension(height)
	 */
	protected World(double width, double height,boolean[][] passableMap, Random random) 
			throws ModelException {
		this.setHeight(height);
		this.setWidth(width);
		this.passableMap = passableMap;
		this.random = random; 
	}
	
	
	
	
	//
	//Getters and other inspectors.
	//
	
	/**
	 *Returns the gravity used in this game. 
	 */
	@Basic
	@Immutable
	@Raw
	protected final double getGravity(){
		return g;
	}
	
	
	/**
	 * Returns the random seed of this world.
	 */
	@Basic
	@Raw
	@Immutable
	private final Random getRandom(){
		return this.random;
	}
	
	/**
	 * Returns the list of possible random names for worms.
	 */
	@Raw
	@Basic
	@Immutable
	private final List<String> getRandomNames(){
		return this.randomNames;
	}
	
	/**
	 * Returns a random name for a worm.
	 * 
	 * @return	The result is in the list of random names.
	 * 			|this.getRandomNames().contains(result)
	 */
	@Raw
	private String getRandomName() {
		int random = this.getRandom().nextInt(this.randomNames.size());
		return getRandomNames().get(random);
	}
	
	
	/**
	 * Returns all the teams in this world.
	 */
	@Basic
	@Raw
	private List<Team> getTeams() {
		return this.teams;
	}
	
	
	/**
	 * Returns the amount of teams in this world.
	 * 
	 * @return 	The size of the list of teams.
	 * 			| result == this.getTeams().size()
	 */
	@Basic
	@Raw
	private int getNumberOfTeams() {
		return this.getTeams().size();
	}
	
	
	/**
	 * Returns the team at a given index in the list of teams this world has.
	 * 
	 * @param 	index
	 * 			The given index.
	 * 			
	 * @return	The team at given index.
	 * 			| result == this.getTeams().get(index)
	 * 
	 * @throws 	ModelException
	 * 			The given index does not exist.
	 * 			| index >= this.getNumberOfTeams()
	 */
	@Basic
	@Raw
	private Team getTeamAt(int index) throws ModelException {
		if (index >= this.getNumberOfTeams() || index < 0)
			throw new ModelException("Not possible to select that team!");
		return this.getTeams().get(index);
	}
	
	
	/**
	 * Returns the width of this world.
	 */
	@Basic
	@Raw
	protected double getWidth(){
		return this.width;
	}
	
	
	/**
	 * Returns the height of this world.
	 */
	@Basic
	@Raw
	protected double getHeight(){
		return this.height;
	}
	
	
	/**
	 * Returns the passable map of this world.
	 */
	@Basic
	@Raw
	@Immutable
	protected final boolean[][] getPassableMap() {
		return this.passableMap;
	}
	
	
	/**
	 * Returns the upper bound for the dimensions of a world.
	 */
	@Basic
	@Raw
	@Immutable
	private static final double getMaxDimension(){
		return maxDimension;
	}
	
	
	/**
	 * Checks if given dimension is valid.
	 * 
	 * @param 	dimension
	 * 			dimension to be checked
	 * 
	 * @return	Returns whether the dimension is positive and below the upper bound.
	 * 			| result == ((0 <= dimension) && (dimension <= getMaxDimension())
	 */	
	@Raw
	private static boolean isValidDimension(double dimension){
		return 0 <= dimension && dimension <= getMaxDimension();
	}
	
	
	/**
	 * Returns whether the game has started.
	 */
	@Basic
	@Raw
	protected boolean getStatus(){
		return this.status;
	}
	
	
	/**
	 * Returns the vertical dimension of this world in a number of pixels.
	 * 
	 * @return	Returns the height in pixels of this world.
	 * 			|	result == this.getPassableMap().length
	 */
	@Basic
	@Raw
	@Immutable
	protected final int getHeightInPixels(){
		return this.passableMap.length;
	}

	
	
	/**
	 * Returns the horizontal dimension of this world in a number of pixels.
	 * 
	 * @return	Returns the width in pixels of this world.
	 * 			|	result == this.getPassableMap()[0].length
	 */
	@Basic
	@Raw
	@Immutable
	protected final int getWidthInPixels(){
		return this.passableMap[0].length;
	}
	
	
	/**
	 * Checks if a given pixel of this world is passable .
	 * 
	 * @param 	row
	 * 			The row in which the pixel is.
	 * @param	column
	 * 			The column in which the pixel is.
	 * 
	 * @return 	The boolean value at the given indices in the boolean map.
	 * 			|result == this.getPassableMap[row][column]
	 * 
	 * @throws 	ModelException 
	 * 			The given indices are invalid for the boolean map of this world.
	 * 			| !(row < this.getHeightInPixels() && row >= 0 && column < this.getWidthInPixels() && column >= 0) 
	 */
	@Basic
	@Raw
	protected boolean isPassablePixel(int row, int column) throws ModelException{
		if (row < this.getHeightInPixels() && row >= 0 && column < this.getWidthInPixels() && column >= 0) 
			return this.passableMap[row][column];
		throw new ModelException("Tested passable pixel outside of range!");
	}
	
	
	/**
	 * Checks if a given location is passable.
	 * 
	 * @param 	x
	 * 			The x coordinate of the location to check.
	 * @param 	y
	 * 			The y coordinate of the location to check.
	 * 
	 * @return	Returns whether the pixel in which the given location is, is a passable pixel.
	 * 			|pixelHeight = (getHeight()/getDimensionInPixels(false))
	 * 			|pixelWidth = (getWidth()/getDimensionInPixels(true))
	 *			| result == isPassablePixel((int)(y/pixelHeight),(int)(x/pixelWidth))
	 */
	@Raw
	private boolean isPassableLocation(double x, double y){
		double pixelHeight = (getHeight()/getHeightInPixels());
		double pixelWidth = (getWidth()/getWidthInPixels());
		return isPassablePixel((getHeightInPixels()-1-(int)(y/pixelHeight)),(int)(x/pixelWidth));
	}
	
	
	/**
	 * Checks if a circular area with a given radius with the center
	 * on the given locations x and y is passable.
	 * 
	 * @param  	x
	 * 			The x coordinate of the center of the circle to be checked.
	 * @param 	y
	 * 			The x coordinate of the center of the circle to be checked.
	 * @param 	radius
	 * 			The radius of the circle to be checked.
	 * 
	 * @return	Whether all the pixels within the circle are passable pixels.
	 *			|step = 0.5*Math.min(getHeight()/(1.0*getHeightInPixels()), getWidth()/(1.0*getWidthInPixels()))
	 *			|if (! isInWorld(x, y, radius))
	 *			| 	result == false
	 *			|
	 *			|else if (!(isPassableLocation(x+Math.sin(angle)*distance,y+Math.cos(angle)*distance)))
	 *			|	for any distance in {x| x in 0..radius & x = radius - n*step (with n integer)}
	 *			|		for any angle in {x| x in 0..Pi*2 & x = n*step/distance (with n integer)}
	 *			|			then result == false
	 *			| 	
	 *			|else
	 *			|	result == true
	 */
	protected boolean isPassableArea(double x, double y, double radius){
		double step = 0.5*Math.min(getHeight()/(1.0*getHeightInPixels()), getWidth()/(1.0*getWidthInPixels()));
		if (! isInWorld(x, y, radius))
			return false;
		for(double distance=0.999*radius; distance>0;distance = distance -step){
			for(double angle=0; angle<=2*Math.PI; angle= angle + step/distance){
				if (!(isPassableLocation(x+Math.sin(angle)*distance,y+Math.cos(angle)*distance))){
					return false;
				}
			}
			}
		return true;
	}
	
	
	/**
	 * Checks if a circular area with a given radius with the center
	 * on the given locations x and y is located within the bounds of this world.
	 * If any of the inputs are double.NaN, this function will return false, this 
	 * is desirable as this logically means that the circular area is not really in
	 * the world.
	 * 
	 * @param  	x 
	 * 			The x coordinate of the center of the circle to be checked. 
	 * 			If this is not a number, the result will be false which is what is desired.
	 * @param 	y
	 * 			The x coordinate of the center of the circle to be checked. 
	 * 			If this is not a number, the result will be false which is what is desired.
	 * @param 	radius
	 * 			The radius of the circle to be checked. 
	 * 			If this is not a number, the result will be false which is what is desired.
	 * 
	 * @return	Whether the circle is within the bounds of the map.
	 * 			|result == !((x-radius) < 0 || (x+radius) > this.getWidth() 
	 * 			|		|| (y-radius) < 0 || (y + radius) > this.getHeight())
	 */
	protected boolean isInWorld(double x, double y, double radius) {
		return !((x-radius) < 0 || (x+radius) > getWidth() || (y-radius) < 0 || (y + radius) > getHeight());
	}

	
	/**
	 * Returns the step size with which most of the methods in world work.
	 * This step size is based on the radius of the object on which the methods
	 * which need this step size will be working. This is favorable because it
	 * eliminates errors that could occur when using the isAdjacent method.
	 * 
	 * @param 	radius
	 * 			The given radius on which to base the step size.
	 * 
	 * @return	The step size
	 * 			| result == 0.02*radius
	 */
	protected double getStep(double radius) {
		return 0.02*radius;
	}
	
	
	/**
	 * Checks if a circular area with a given radius with the center
	 * on the given locations x and y is adjacent to impassable terrain..
	 * 
	 * @param  	x
	 * 			The x coordinate of the center of the circle to be checked.
	 * @param 	y
	 * 			The x coordinate of the center of the circle to be checked.
	 * @param 	radius
	 * 			The radius of the circle to be checked.
	 * 
	 * @return	Returns whether entity at location is adjacent to impassable terrain.
	 * 			|if (!(isInWorld(x, y, radius*1.1)))
	 *			|	result == false
	 * 			|result  == (! isPassableArea(x,y,1.1*radius)) && isPassableArea(x, y, radius)
	 */
	protected boolean isAdjacent(double x, double y,double radius){
		if (!(isInWorld(x, y, radius*1.1)))
			return false;
		return !isPassableArea(x,y,1.1*radius) && isPassableArea(x,y,radius);
	}
	
	
	/**
	 * Returns a random valid location to put a new worm or food based on 
	 * the radius of the entity.
	 * 
	 * @param 	radius
	 * 			The radius of the entity for which to find a random location.
	 * 
	 * @return 	The x and y position of the random location that was found as a list.
	 * 			|x = (getRandom().nextDouble())*(getWidth()-radius*2.0)+radius
	 *			|y = 0
	 *			|if (x == getWidth()/2.0)
	 *			|	angle = Math.PI/2.0
	 *			|else
	 *			|	angle = Math.atan(getHeight()*0.5/Math.abs(getWidth()*0.5-x))
	 *			|step = getStep(radius)
	 *			|stepY = Math.sin(angle)*step
	 *			|
	 *			|if (!isAdjacent(y/tan(angle), y, radius))
	 *			|	for each y in {y| y in 0..(result[1]-stepY)  & y= n*stepY (with n integer)}
	 *			|		then result == [result[1]/tan(angle),result[1]]
	 *
	 *@throws	No location was found.
	 *			| If the nothing is returned during the while loop.
	 */		
	//TODO commentaar world
	private double[] getRandomAdjacentLocation(double radius) throws ModelException {
		double[][] possibles = {{(getRandom().nextDouble())*(getWidth()-radius*2.0)+radius,0},
								{(getRandom().nextDouble())*(getWidth()-radius*2.0)+radius,getHeight()},
								{0, (getRandom().nextDouble())*(getHeight()-radius*2.0)+radius},
								{getWidth(), (getRandom().nextDouble())*(getHeight()-radius*2.0)+radius}};
		double angle;
		int randomInt = Math.abs(getRandom().nextInt())%4;
		double x = possibles[randomInt][0];
		double y = possibles[randomInt][1];
		if (x == getWidth()/2.0)
			angle = Math.PI/2.0;
		else if (y == getHeight()/2.0)
			angle = 0.0;
		else
			angle = Math.atan(Math.abs(getHeight()*0.5-y)/Math.abs(getWidth()*0.5-x));
		double step = getStep(radius);
		double stepX = Math.cos(angle)*step;
		double stepY = Math.sin(angle)*step;
		
		if (x > getWidth()/2.0)
			stepX = stepX*(-1);
		if (y > getHeight()/2.0)
			stepY = stepY*(-1);
		
		while (!(Util.fuzzyEquals(y, getHeight()/2.0,step*2.0))){
			x = x + stepX;
			y = y + stepY;
			if (isAdjacent(x, y, radius)) {
				return new double[] {x,y};
				}
				
		}
		throw new ModelException("Did not find location");
	}
	
	
	/**
	 * Returns all the game objects (that means:food, worms, weapons ...) in this world.
	 */
	@Basic
	@Raw
	protected List<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	

	/**
	 * Returns the amount of objects in this world.
	 * 
	 * @return	The size of the list of game objects this world has.
	 * 			|result == this.getGameObjects().size()
	 */
	@Basic
	@Raw
	private int getNbOfGameObjects() {
		return this.getGameObjects().size();
	}
	
	
	/**
	 * Returns all the worms in this world.
	 * 
	 * @return	Returns all the worms that are currently in this world as a list.
	 * 			|for each gameObject in this.getGameObjects
	 * 			| 	if gameObject instanceof Worm
	 * 			|		result.contains(gameObject) 
	 */
	protected List<Worm> getAllWorms(){
		List<Worm> worms = new ArrayList<Worm>();
		List<GameObject> objects = getGameObjects();
		for(int counter = 0; counter < (objects.size()); counter = counter +1){
			if (objects.get(counter) instanceof Worm)
				worms.add((Worm) objects.get(counter));
		}
		return worms;
	}
	
	
	/**
	 * Returns all the food in this world.
	 * 
	 * @return	Returns all the food that are currently in this world as a list.
	 * 			|for each gameObject in this.getGameObjects
	 * 			| if gameObject instanceof Food
	 * 			|		result.contains(gameObject)
	 */
	protected List<Food> getAllFood(){
		List<Food> food = new ArrayList<Food>();
		List<GameObject> objects = getGameObjects();
		for(int counter = 0; counter < (objects.size()); counter = counter +1){
			if (objects.get(counter) instanceof Food)
				food.add((Food) objects.get(counter));
		}
		return food;
			
	}
	
	
	/**
	 * Returns the food that overlaps with a given worm if there is a food that overlaps.
	 * 
	 * @param	worm
	 * 			The worm to check.
	 * 
	 * @return	Returns the requested food or null if no food is found that overlaps.
	 * 			| for any food in this.getAllFood()
	 * 			| if worm.Overlaps(food)
	 * 			|		result == food
	 * 			| else
	 * 			| 		result == null
	 */
	protected Food getFoodThatOverlaps(Worm worm) {
		for(Food food: this.getAllFood()){
			if (worm.overlapsGameObject(food))
				return food;
		}
		return null;
	}

	
	/**
	 * Returns the worm that overlaps with a given projectile if there is a worm that overlaps.
	 * 
	 * @param	projectile
	 * 			The projectile to check.
	 *
	 * @return	Returns the requested worm or null if no worm is found that overlaps.
	 * 			| for any worm in this.getAllWorms()
	 * 			| if projectile.Overlaps(worm)
	 * 			|		result == worm
	 * 			| else
	 * 			| 		result == null
	 */
	protected Worm getWormThatOverlaps(Projectile projectile) {
		for (Worm worm: this.getAllWorms()) {
			if (projectile.overlapsGameObject(worm)) {
				return worm;
			}
		}
		return null;
	}
	
	
	
	/**
	 * Checks if a circle with given centre and radius overlaps 
	 * with a worm.
	 * 
	 * @param 	x
	 * 			The x coordinate of the centre.
	 * @param 	y
	 * 			The y coordinate of the centre.
	 * @param 	radius
	 * 			The radius of the circle.
	 * 
	 * @return	Returns if a worm overlaps with the given circle.
	 * 			| for any worm in this.getAllWorms()
	 * 			| 	if (GameObjects.overlaps(x,y,radius,worm.getCoordinateX(), worm.getCoordinateY, worm.getRadius()))
	 * 			|		then result == true
	 * 			| result == false
	 */	
	protected boolean coordinatesOverlapsWorm(double x, double y, double radius) {
		for (Worm worm: this.getAllWorms()) {
			if (worm != this.getActiveWorm())
				if (GameObject.overlaps(worm.getCoordinateX(),worm.getCoordinateY(),worm.getRadius(),
									x,y,radius)) {
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns whether a given projectile overlaps with a worm.
	 * 
	 * @param	projectile
	 * 			The projectile to check.
	 *
	 * @return	Returns true if the given projectile overlaps with a worm that is not the active worm
	 * 			(the worm that fired the projectile).
	 *			| result == (this.getWormThatOverlapsProjectile(projectile) != null) 
	 *			|			&& (this.getWormThatOverlapsProjectile(projectile) != this.getActiveWorm())
	 */
	protected boolean projectileOverlapsWorm(Projectile projectile) {
		Worm worm = this.getWormThatOverlaps(projectile);
		return (worm != null) && (worm != this.getActiveWorm());
	}
	
	
	/**
	 * Returns the index of the active worm in the list of all the worms in this world.
	 */
	@Basic
	@Raw
	private int getIndexOfActiveWorm() {
		return this.indexOfActiveWorm;
	}
	
	
	/**
	 * Returns the active worm(the worm who's turn it is). 
	 * 
	 * @return	The active worm.
	 * 			|result == this.getAllWorms().get(getIndexOfActiveWorm())
	 */
	protected Worm getActiveWorm(){
		return this.getAllWorms().get(getIndexOfActiveWorm());
	}
	
	
	/**
	 * Returns the projectile equipped by the active worm.
	 * 
	 * @return	The projectile.
	 * 			| result == this.getActiveWorm().getProjectile()
	 */
	protected Projectile getActiveProjectile() {
		return this.getActiveWorm().getProjectile();
	}
	
	
	/**
	 * Returns a game object at a certain position in the list of game objects.
	 * 
	 * @param	i
	 * 			The position of the wanted game object.
	 * 
	 * @return	The game object at the given index in the list of game objects.
	 * 			| result == this.getGameObjects().get(i)
	 * 
	 * @throws	ModelException
	 * 			The given index does not exist.
	 * 			| (! (i < this.getNbOfGameObjects()))
	 */
	protected GameObject getGameObjectAt(int i) throws ModelException {
		if (! (i < this.getNbOfGameObjects()))
			throw new ModelException("Index out of bound!");
		return this.getGameObjects().get(i);
	}
	
	
	/**
	 * Checks if this world can have the given game object. 
	 * 
	 * @param 	gameObject
	 * 			The game object to be checked.
	 * 
	 * @return	True if this world can have the game object as a game object.
	 * 			This means true if the given game object already has this world
	 * 			as it's world.
	 * 			| result == (gameObject.getWorld() == this)
	 */
	@Basic
	@Raw
	private boolean canHaveAsGameObject(GameObject gameObject) {
		return (gameObject.getWorld() == this);
	}
	
	
	/**
	 * Checks if all the game objects this world has are legal objects for this world to have.
	 * 
	 * @return	If each game object in this world, has this world as world.
	 * 			| for each object in this.getGameObjects()
	 * 			|		if ! this.canHaveAsGameObject(object)
	 * 			|				result == false
	 * 			| result == true
	 */
	@SuppressWarnings("unused")
	private boolean hasProperGameObjects() {
		for(int i=0; i < this.getNbOfGameObjects(); i++) {
			if (! this.canHaveAsGameObject(this.getGameObjectAt(i)))
				return false;
		}
		return true;
	}
	
	
	/**
	 * Checks if the game is over.
	 * 
	 * @return	Creates a list of all the remaining teams still in the game.
	 * 			With null being the team of the worms that are not in a team.
	 * 			If this list contains more than 1 element the game is not over,
	 * 			and if the list contains only null, but there are multiple worms
	 * 			in the "null team" the game is also not yet over. In all the other 
	 * 			cases the game is over.
	 * 			|Set<Team> set = new HashSet<Team>();
	 * 			|
	 * 			|for each worm in getAllWorms()
	 * 			| 	set.add(worm.getTeam())
	 *			|
	 *			|if (set.size() > 1)
	 * 			|	result == false
	 *			|else if (set.size()==1 && set.contains(null) && getAllWorms().size()>1)
	 *			|	result == false
	 *			|else
	 *			|	result == true
	 */
	protected boolean isGameFinished() {
		Set<Team> set = new HashSet<Team>();
		for(int counter=0;counter<getAllWorms().size();counter++){
			set.add(getAllWorms().get(counter).getTeam());
		}
		if (set.size() > 1)
			return false;
		else if (set.size()==1 && set.contains(null) && getAllWorms().size()>1)
			return false;
		else
			return true;
	}
	
	
	/**
	 * Returns the winner of the game (only applicable if the game is over).
	 * 
	 * @return	Returns the name of the winning worm or the name
	 * 			of the winning team if the worm that is still
	 * 			alive was part of a team. If no worms are still 
	 * 			alive "nobody" will be returned.
	 * 			|if (getAllWorms().size() ==0)
	 *			|	result == "Nobody"
	 *			|else if (getAllWorms().get(0).getTeam() == null)
	 *			|	result == getAllWorms().get(0).getName()
	 *			|else
	 *			|	result == getAllWorms().get(0).getTeam().getName()
	 */
	protected String getWinner(){
		if (getAllWorms().size() ==0)
			return "Nobody";
		if (getAllWorms().get(0).getTeam() == null)
			return getAllWorms().get(0).getName();
		return getAllWorms().get(0).getTeam().getName();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	//
	//Setters and other mutators.
	//

	/**
	 * Set the height of this world.
	 * 
	 * @param 	height
	 * 			Height to be assigned to world
	 * 
	 * @post	The height has been set.
	 * 			| new.getHeight() == height
	 * 
	 * @throws	ModelException
	 * 			The dimension is invalid
	 * 			|!isValidDimension(height)
	 */
	@Raw
	private void setHeight(double height) throws ModelException{
		if (!isValidDimension(height))
			throw new ModelException("Invalid height for world!");
		this.height = height;
	}
	
	
	/**
	 * Set the width of this world.
	 * 
	 * @param 	width
	 * 			Width to be assigned to world
	 * 
	 * @post	The width has been set.
	 * 			| new.getWidth() == width
	 * 
	 * @throws	 ModelException
	 * 			The dimension is invalid
	 * 			|!isValidDimension(width)
	 */
	@Raw
	private void setWidth(double width){
		if (!isValidDimension(width))
			throw new ModelException("Invalid width for world!");
		this.width = width;
	}
	
	
	/**
	 * Starts the game.
	 * 
	 * @post 	The game has started
	 * 			|new.getStatus() == true
	 */
	protected void startGame(){
		this.status = true;
	}
	
	
	/**
	 * Adds given game object to the world.
	 * 
	 * @param 	gameObject
	 * 			Game object to be added.
	 * 
	 * @post 	The given game object is in the world.
	 * 			| new.gameObjects.contains(gameObject)
	 * 
	 * @throws 	ModelException
	 * 			The world cannot have this object as a game object.
	 * 			|(! this.canHaveAsGameObject(gameObject))
	 */
	protected void addAsGameObject(@Raw GameObject gameObject) throws ModelException {
		if (! this.canHaveAsGameObject(gameObject))
			throw new ModelException("Cannot assign this game object to this world!");
		this.gameObjects.add(gameObject);
	}
	
	
	/**
	 * Removes given game object from the world.
	 * 
	 * @param 	gameObject
	 * 			Game object to be removed.
	 * 
	 * @post	The object has been removed from the world.
	 * 			|! this.gameObjects.contains(gameObject)
	 * @post	If the object that has been removed is a worm,
	 * 			and this worm's index in the list of worms is
	 * 			smaller than the index of the active worm.
	 * 			The index of the active worm is adjusted.
	 * 			|if (gameObject instanceof Worm){
	 *			|	if (index < getIndexOfActiveWorm())
	 *			|		new.getIndexOfActiveWorm == getIndexOfActiveWorm-1
	 * 
	 * @effect	If the object that is removed is the active worm,
	 * 			The next worm's turn is started.
	 * 			|if (gameObject instanceof Worm){
	 * 			|	if (index == getIndexOfActiveWorm())
	 *			|		this.nextTurn()
	 */
	protected void removeAsGameObject(@Raw GameObject gameObject) throws ModelException {
		int index = getAllWorms().indexOf(gameObject);
		this.gameObjects.remove(gameObject);
		if (gameObject instanceof Worm){
			if (index == getIndexOfActiveWorm()){
				nextTurn();
			}
			if (index < getIndexOfActiveWorm()){
				setIndexOfActiveWorm(getIndexOfActiveWorm()-1);
			}
		}
	}
	
	
	/**
	 * Adds a new worm to the world at a random adjacent location.
	 * 
	 * @post	The new Worm has been added at a random adjacent location on the map.
	 *			Or no worm has been added. The radius of this worm is 0.3. This worm
	 *			will receive a random name and may or may not be in a team.
	 *			| new.getAllWorms() == this.getAllWorms()+1 || new.getAllWorms() == this.getAllWorms()
	 *			| if (new.getAllWorms() == this.getAllWorms()+1)
	 *			|	radius = new.getAllWorms().get(new.getAllWorms().size-1).getRadius()
	 *			|	x = new.getAllWorms().get(new.getAllWorms().size-1).getCoordinateX
	 *			|	y = new.getAllWorms().get(new.getAllWorms().size-1).getCoordinateY
	 * 			|	isAdjacent(x,y,radius)
	 * 
	 * @throws	ModelException
	 * 			The game has already started.
	 * 			| getStatus()
	 */
	protected void addWorm(){
		if (getStatus())
			throw new ModelException("Cannot place worms once game has started!");
		double radius = 0.3;
		try {
		double[] position = getRandomAdjacentLocation(radius);
		Worm worm = new Worm(position[0],position[1],random.nextDouble()*Math.PI*2.0,radius,
							this.getRandomName(),true,this);
		this.addAsGameObject(worm);
		
		int random = getRandom().nextInt(this.getNumberOfTeams()+1);
		if (! (random == this.getNumberOfTeams()))
			worm.joinTeam(this.getTeamAt(random));
		}
		catch(ModelException modelException){
		}	
	}
	
	
	/**
	 * Adds a new food to the world at a random adjacent location.
	 * 
	 * @post	The new food has been added at a random adjacent location on the map.
	 *			Or no food has been added.
	 *			| new.getAllFood() == this.getAllFood()+1 || new.getAllFood() == this.getAllFood()
	 *			| if (new.getAllWorms() == this.getAllWorms()+1)
	 *			|	x = new.getAllFood().get(new.getAllFood().size-1).getCoordinateX
	 *			|	y = new.getAllFood().get(new.getAllFood().size-1).getCoordinateY
	 * 			|	isAdjacent(x,y,0.2)
	 * 
	 * @throws	ModelException
	 * 			The game has already started.
	 * 			| getStatus()
	 */
	protected void addFood(){
		if (getStatus())
			throw new ModelException("Cannot place worms once game has started!");
		try {
		double[] position = getRandomAdjacentLocation(0.20);
		Food food = new  Food(position[0],position[1],true,this);
		this.addAsGameObject(food);
		}
		catch(ModelException modelException){
		}
	}
	
	
	/**
	 * Adds given team to this world
	 * 
	 * @param 	team
	 * 			The team to be added.
	 * 
	 * @post	The team has been added.
	 * 			|new.getTeams().contains(team)
	 */
	private void addAsTeam(Team team) {
		this.teams.add(team);
	}

	/**
	 * Creates a team with the given name and adds it to this world.
	 * 
	 * @param 	name
	 * 			The name of the team to be created
	 * 
	 * @post	The team with the given name has been created and added to this world.
	 * 			|new.getTeams().get(new.teams.size-1).getName() == name
	 * 
	 * @throws 	ModelException
	 * 			The maximum number of teams has been reached.
	 * 			| (this.getNumberOfTeams() > 10)
	 * @throws	ModelException
	 * 			The game has already started.
	 * 			| getStatus()
	 */
	protected void addTeam(String name) throws ModelException {
		if (this.getStatus())
			throw new ModelException("Cannot make team once game has started!");
		if (this.getNumberOfTeams() > 10)
			throw new ModelException("World can only have up to 10 teams");
		Team team = new Team(name);
		this.addAsTeam(team);
	}
	
	
	/**
	 * Checks whether all the teams in this world are proper teams.
	 * 
	 * @return	Returns if each team in this world is a proper team.
	 * 			| for each team in this.getTeams()
	 * 			|	if (! this.isProperTeam(team))
	 * 			|		result == false
	 * 			| result == true
	 */
	protected boolean hasProperTeams() {
		for(Team team: this.getTeams()) {
			if (! this.isProperTeam(team))
				return false;
		}
		return true;
	}
	
	
	/**
	 * Checks if the given team is a proper team.
	 * 
	 * @param 	team
	 * 			The team that needs to be checked.
	 * 
	 * @return	Returns if the team has proper worms and if all the worms are in this world.
	 * 			| if (! team.hasProperWorms())
	 * 			|	result == false
	 * 			| for each worm in team.getLivingWorms()
	 * 			|	if (! (worm.getWorld() == this))
	 * 			|		result == false
	 * 			| result == true
	 */
	protected boolean isProperTeam(Team team) {
		if (! team.hasProperWorms())
			return false;
		for(Worm worm: team.getLivingWorms()) {
			if (! (worm.getWorld() == this))
				return false;
		}
		return true;
	}

	
	/**
	 * Starts the next turn of the game. This means that it is the next worm's turn to move/jump/shoot.
	 * If all worms in the world have had their turn, all action points are set to the max, the hit
	 * points are increased by 10 and the it is the first worm's turn again.
	 * 
	 * @post	The index of the active worm is now the index of the next worm 
	 * 			or the first worm if all worms have had their turn this round.
	 * 			|if (this.getIndexOfActiveWorm()+1 >= this.getAllWorms().size())
	 *			|	new.getIndexOfActiveWorm() == 0
	 *			|else 
	 *			|	new.getIndexOfActiveWorm() == this.getIndexOfActiveWorm()+1
	 * 
	 * @effect	If the next round is started the hit points of all worms are increased
	 * 			by 10 and their action points set to max.
	 * 			|if (this.getIndexOfActiveWorm()+1 >= this.getAllWorms().size())
	 * 			|	this.setActionPointsToMaxAndAdd10HitPoints()
	 */
	protected void nextTurn() {
		if (getIndexOfActiveWorm()+1 >= getAllWorms().size()){
			setActionPointsToMaxAndAdd10HitPoints();
			setIndexOfActiveWorm(0);
		}
		else{
			setIndexOfActiveWorm(getIndexOfActiveWorm()+1);
		}
	}
	
	
	/**
	 * Sets the index of the active worm in the list of all the worms in this world.
	 * 
	 * @param 	indexToBeSet
	 * 			The index to be set.
	 * 
	 * @post 	The index has been set.
	 * 			new.getIndexOfActiveWorm() == indexToBeSet
	 */
	@Raw
	private void setIndexOfActiveWorm(int indexToBeSet) {
		this.indexOfActiveWorm = indexToBeSet;
		
	}
	
	
	/**
	 * These are the consequences of a new round starting. This means that
	 * the action points of all the worms are set to the max and the hit
	 * points of each worm are increased by 10.
	 * 
	 * @post 	The action points have been set to the max and the hit points
	 * 			been increased by 10.
	 * 			| for each oldWorm in this.getAllWorms() and each newWorm in new.getAllWorms()
	 * 			|	newWorm.getActionPoints() == newWorm.getMaximumActionPoints() &&
	 * 			|		newWorm.getHitPoints() == oldWorm.getHitpoints() +10
	 */
	private void setActionPointsToMaxAndAdd10HitPoints(){
		for(int counter  = 0;counter < getAllWorms().size(); counter = counter+1){
			getAllWorms().get(counter).setActionPoints(getAllWorms().get(counter).getMaximumActionPoints());
			getAllWorms().get(counter).setHitPoints(getAllWorms().get(counter).getHitPoints()+10);
		}
	}
	
	
	
	@Override
	protected World clone() throws CloneNotSupportedException {
		return (World) super.clone();
	}

}


