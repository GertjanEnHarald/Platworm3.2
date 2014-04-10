package worms.model;

import java.util.*;

import worms.util.Util;

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
	private int indexOfActiveWorm=0;
	private final List<Team> teams = new ArrayList<Team>();
	private final List<String> randomNames = Arrays.asList("Bob", "Emmitt","Parker", "Sergio", "Elias", "Clifton",
		    "Gregg", "Derick", "Porter", "Archie", "Robbie", "Salvador", "Erich", "Wilfredo", "Casey",
		    "Sung", "Christopher", "Jude", "Logan", "Roosevelt", "Rich");
	
	
	protected World(double width, double height,boolean[][] passableMap, Random random) 
			throws ModelException {
		this.setHeight(height);
		this.setWidth(width);
		this.passableMap = passableMap;
		this.random = random; 
	}
	
	/**
	 * Returns the random seed of this world.
	 */
	private Random getRandom(){
		return this.random;
	}
	
	/**
	 * Returns all the game objects (that means:food, worms, weapons ...) in this world.
	 */
	protected List<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	
	/**
	 * Returns the active worm. 
	 */
	protected Worm getActiveWorm(){
		return this.getAllWorms().get(getIndexOfActiveWorm());
	}
	
	/**
	 * Returns all the worms in this world.
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
	 * Returns the amount of objects in this world.
	 */
	private int getNbOfGameObjects() {
		return this.getGameObjects().size();
	}
	
	/**
	 * Returns a game object at a certain postion in the list of game objects.
	 */
	protected GameObject getGameObjectAt(int i) throws ModelException {
		if (! (i < this.getNbOfGameObjects()))
			throw new ModelException("Index out of bound!");
		return this.getGameObjects().get(i);
		// TODO clone this given game object
	}
	
	/**
	 * 
	 * @param gameObject
	 * @throws ModelException
	 */
	private void addAsGameObject(GameObject gameObject) throws ModelException {
		if (! this.canHaveAsGameObject(gameObject))
			throw new ModelException("Cannot assign this game object to this world!");
		this.gameObjects.add(gameObject);
	}
	
	protected void removeAsGameObject(GameObject gameObject) throws ModelException{
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


	private boolean canHaveAsGameObject(GameObject gameObject) {
		return (gameObject.getWorld() == this);
	}
	
	private boolean hasProperGameObjects() {
		for(int i=0; i < this.getNbOfGameObjects(); i++) {
			if (! this.canHaveAsGameObject(this.getGameObjectAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Returns the upper bound for the dimensions of a world.
	 */
	private static double getMaxDimension(){
		return maxDimension;
	}
	
	/**
	 * Returns whether the game is active.
	 */
	protected boolean getStatus(){
		return this.status;
	}
	
	
	protected void startGame(){
		this.status = true;
	}
	
	
	protected void nextTurn() {
		
		if (getIndexOfActiveWorm()+1 >= getAllWorms().size()){
			setActionPointsToMax();
			setIndexOfActiveWorm(0);
		}
		else{
			setIndexOfActiveWorm(getIndexOfActiveWorm()+1);
		}
	}
	
	private void setIndexOfActiveWorm(int indexToBeSet) {
		this.indexOfActiveWorm = indexToBeSet;
		
	}

	private int getIndexOfActiveWorm() {
		return this.indexOfActiveWorm;
	}

	private void setActionPointsToMax(){
		for(int counter  = 0;counter < getAllWorms().size(); counter = counter+1){
			getAllWorms().get(counter).setActionPoints(getAllWorms().get(counter).getMaximumActionPoints());
		}
	}
	
	/**
	 * Returns the width of this world.
	 */
	protected double getWidth(){
		return this.width;
	}
	
	
	/**
	 * Returns the height of this world.
	 */
	protected double getHeight(){
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
	private static boolean isValidDimension(double dimension){
		return 0 <= dimension && dimension <= getMaxDimension();
	}
	
	
	/**
	 * Checks if a given pixel of this world is passable 
	 * 
	 * 
	 * @param 	row
	 * 			The row in which the pixel is.
	 * @param	column
	 * 			The column in which the pixel is.
	 * 
	 * @return	
	 * 			|result == this.passableMap[row][column]
	 */
	protected boolean isPassablePixel(int row, int column) throws ModelException{
		if (row < this.getHeightInPixels() && row >= 0 && column < this.getWidthInPixels() && column >= 0) 
			return this.passableMap[row][column];
		throw new ModelException("Tested passable pixel outside of range!");
	}
	
	
	/**
	 * Returns the vertical dimension of this world in a number of pixels.
	 * 
	 * @return	Returns the height in pixels of this world.
	 * 			|	result == this.passableMap.length
	 */
	protected int getWidthInPixels(){
		return this.passableMap[0].length;
		
	}
	
	/**
	 * Returns the horizontal dimension of this world in a number of pixels.
	 * 
	 * @return	Returns the width in pixels of this world.
	 * 			|	result == this.passableMap[0].length
	 */
	protected int getHeightInPixels(){
		return this.passableMap.length;
	}
	
	
	/**
	 * Checks if a given location is passable.
	 * 
	 * 
	 * @param 	x
	 * 			The x coordinate of the location to check.
	 * @param 	y
	 * 			The y coordinate of the location to check.
	 * 
	 * @return	Returns whether the location is passable.
	 * 			|pixelHeight = (getHeight()/getDimensionInPixels(false))
	 * 			|pixelWidth = (getWidth()/getDimensionInPixels(true))
	 *			| result == isPassablePixel((int)(y/pixelHeight),(int)(x/pixelWidth))
	 */
	protected boolean isPassableLocation(double x, double y){
		double pixelHeight = (getHeight()/getHeightInPixels());
		double pixelWidth = (getWidth()/getWidthInPixels());
		return isPassablePixel((getHeightInPixels()-1-(int)(y/pixelHeight)),(int)(x/pixelWidth));
	}
	
	
	private String getRandomName() {
		int random = this.getRandom().nextInt(this.randomNames.size());
		return randomNames.get(random);
	}
	
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
	protected boolean isPassableArea(double x, double y, double radius){
		double step = Math.min(getStep()*10, getStep(radius));
		if ((x-radius) < 0 || (x+radius) > getWidth() || (y-radius) < 0 || (y + radius) > getHeight())
			return false;
		for(double distance=step; distance<=radius;distance = distance +step){
			for(double angle=0; angle<=2*Math.PI; angle= angle + step/distance){
				if (!(isPassableLocation(x+Math.sin(angle)*distance,y+Math.cos(angle)*distance))){
					return false;
				}
			}
			}
		return true;
	}

	protected double getStep(double radius) {
		return 0.03*radius;
	}
	
	protected double getStep() {
		double step = Math.min(getHeight()/getHeightInPixels(), getWidth()/getWidthInPixels())/2.0;
		return step;
	}
	
	
	/**
	 * Checks if entity at location is adjacent to impassable terrain.
	 * 
	 * 
	 * @param 	x
	 * 			x coordinate of location
	 * @param 	y
	 * 			y coordinate of location
	 * @param 	radius
	 * 			radius of entity
	 * 
	 * @return	Returns whether entity at location is adjacent to impassable terrain.
	 * 			| result  == isPassableArea(x,y,1.1*radius)
	 */
	protected boolean isAdjacent(double x, double y,double radius){
		if ((x-radius*1.1) < 0 || (x+radius*1.1) > getWidth() || (y-radius*1.1) < 0 || (y + radius*1.1) > getHeight())
			return false;
		return !isPassableArea(x,y,1.1*radius) && isPassableArea(x,y,radius);
	}
	
	
	private double[] getRandomAdjacentLocation(double radius){
		double X = (getRandom().nextDouble())*(getWidth()-radius*2.0)+radius;
		double Y = 0;
		double angle = Math.tan(getHeight()*0.5/(getWidth()*0.5-X));
		if (Double.isNaN(angle));
			angle = Math.PI/2.0;
		double step = getStep(radius);
		double stepX = Math.cos(angle)*step;
		double stepY = Math.sin(angle)*step;
		while (!(Util.fuzzyEquals(Y, getHeight()/2.0,step*2.0))){
			X = X + stepX;
			Y = Y + stepY;
			if (isAdjacent(X, Y, radius))
				return new double[] {X,Y};
				
		}
		throw new ModelException("Did not find location");
	}
	
	
	
	
	private List<Team> getTeams() {
		return this.teams;
	}
	
	
	private void addAsTeam(Team team) {
		this.teams.add(team);
	}
	
	
	private int getNumberOfTeams() {
		return this.getTeams().size();
	}
	
	
	private Team getTeamAt(int i) throws ModelException {
		if (i >= this.getNumberOfTeams())
			throw new ModelException("Not possible to select that team!");
		return this.getTeams().get(i);
	}
	
	
	protected void addTeam(String name) throws ModelException {
		if (this.getStatus())
			throw new ModelException("Cannot make team once game has started!");
		if (this.getNumberOfTeams() > 10)
			throw new ModelException("World can only have up to 10 teams");
		Team team = new Team(name);
		this.addAsTeam(team);
	}

	protected boolean isGameFinished() {
		Set<Team> set = new HashSet<Team>();
		for(int counter=0;counter<getAllWorms().size();counter++){
			set.add(getAllWorms().get(counter).getTeam());
		}
		if (set.size() > 1)
			return false;
		if (set.size()==1 && set.contains(null) && getAllWorms().size()>1)
			return false;
		return true;
	}

	
	/**
	 * Dit werkt met vierkanten en het moet met cirkels. Dit klopt dus niet.
	 * @param worm
	 * @return
	 */
	protected Food getFoodThatOverlaps(Worm worm) {
		for(int counter=0;counter<getAllFood().size();counter++){
			if ((getAllFood().get(counter).getCoordinateX()-getAllFood().get(counter).getRadius() < worm.getCoordinateX()+worm.getRadius())
					&& 
					(getAllFood().get(counter).getCoordinateX()+getAllFood().get(counter).getRadius() > worm.getCoordinateX()-worm.getRadius())
					&&
					(getAllFood().get(counter).getCoordinateY()-getAllFood().get(counter).getRadius() < worm.getCoordinateY()+worm.getRadius())
					&& 
					(getAllFood().get(counter).getCoordinateY()+getAllFood().get(counter).getRadius() > worm.getCoordinateY()-worm.getRadius())){
						return getAllFood().get(counter);
			}
				
		}
		return null;
	}
	
	
	protected String getWinner(){
		if (getAllWorms().size() ==0)
			return "Nobody";
		if (getAllWorms().get(0).getTeam() == null)
			return getAllWorms().get(0).getName();
		return getAllWorms().get(0).getTeam().getName();
	}
	
}


