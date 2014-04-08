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
	private final List<Worm> wormsWhoHaveHadTheirTurn = new ArrayList<Worm>();
	private Worm activeWorm;
	private final List<Team> teams = new ArrayList<Team>();
	private final List<String> randomNames = Arrays.asList("Bob", "Emmitt","Parker", "Sergio", "Elias", "Clifton",
		    "Gregg", "Derick", "Porter", "Archie", "Robbie", "Salvador", "Erich", "Wilfredo", "Casey",
		    "Sung", "Christopher", "Jude", "Logan", "Roosevelt", "Rich");
	
	
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
	
	
	public List<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	
	public Worm getActiveWorm(){
		if (!getStatus())
			return null;
		if (this.activeWorm.getStatus())
			return this.activeWorm;
		nextTurn();
		return this.activeWorm;
	}
	
	public List<Worm> getAllWorms(){
		List<Worm> worms = new ArrayList<Worm>();
		List<GameObject> objects = getGameObjects();
		for(int counter = 0; counter < (objects.size()); counter = counter +1){
			if (objects.get(counter) instanceof Worm)
				worms.add((Worm) objects.get(counter));
		}
		return worms;
	}
		
	public List<Food> getAllFood(){
		List<Food> food = new ArrayList<Food>();
		List<GameObject> objects = getGameObjects();
		for(int counter = 0; counter < (objects.size()); counter = counter +1){
			if (objects.get(counter) instanceof Food)
				food.add((Food) objects.get(counter));
		}
		return food;
			
	}
	
	public int getNbOfGameObjects() {
		return this.getGameObjects().size();
	}
	
	public GameObject getGameObjectAt(int i) throws ModelException {
		if (! (i < this.getNbOfGameObjects()))
			throw new ModelException("Index out of bound!");
		return this.getGameObjects().get(i);
		// TODO clone this given game object
	}
	
	public void addAsGameObject(GameObject gameObject) throws ModelException {
		if (! this.canHaveAsGameObject(gameObject))
			throw new ModelException("Cannot assign this game object to this world!");
		this.gameObjects.add(gameObject);
	}
	
	public void removeAsGameObject(GameObject gameObject) throws ModelException{
		this.gameObjects.remove(gameObject);
		if (getActiveWorm()==gameObject)
			nextTurn();
	}


	public boolean canHaveAsGameObject(GameObject gameObject) {
		return (gameObject.getWorld() == this);
	}
	
	public boolean hasProperGameObjects() {
		for(int i=0; i < this.getNbOfGameObjects(); i++) {
			if (! this.canHaveAsGameObject(this.getGameObjectAt(i)))
				return false;
		}
		return true;
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
	
	private List<Worm> getAllWormsWhoHaveHadTheirTurn(){
		return this.wormsWhoHaveHadTheirTurn;
	}
	
	public void startGame(){
		this.status = true;
		this.nextTurn();
	}
	
	public void setActiveWorm(Worm worm){
		this.activeWorm = worm;
	}
	
	public void nextTurn() {
		
		if (getAllWormsWhoHaveHadTheirTurn().size() == getAllWorms().size()){
			setActionPointsToMax();
			getAllWormsWhoHaveHadTheirTurn().clear();
		}
		for(int counter  = 0;counter < getAllWorms().size(); counter = counter+1){
			if (!getAllWormsWhoHaveHadTheirTurn().contains(getAllWorms().get(counter))){
					setActiveWorm(getAllWorms().get(counter));
					getAllWormsWhoHaveHadTheirTurn().add(getAllWorms().get(counter));
					return;
			}
		}
	}
	
	public void setActionPointsToMax(){
		for(int counter  = 0;counter < getAllWormsWhoHaveHadTheirTurn().size(); counter = counter+1){
			getAllWormsWhoHaveHadTheirTurn().get(counter).setActionPoints(getAllWormsWhoHaveHadTheirTurn().get(counter).getMaximumActionPoints());
		}
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
	 * 
	 * @param 	row
	 * 			The row in which the pixel is.
	 * @param	column
	 * 			The column in which the pixel is.
	 * 
	 * @return	
	 * 			|result == this.passableMap[row][column]
	 */
	public boolean isPassablePixel(int row, int column) throws ModelException{
		if (row < this.getDimensionInPixels(false) && row >= 0 && column < this.getDimensionInPixels(true) && column >= 0) 
			return this.passableMap[row][column];
		throw new ModelException("Tested passable pixel outside of range!");
	}
	
	
	/**
	 * Returns the vertical or horizontal dimension of this world in a number of pixels.
	 * 
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
	public boolean isPassableLocation(double x, double y){
		double pixelHeight = (getHeight()/getDimensionInPixels(false));
		double pixelWidth = (getWidth()/getDimensionInPixels(true));
		return isPassablePixel((getDimensionInPixels(false)-1-(int)(y/pixelHeight)),(int)(x/pixelWidth));
	}
	
	
	public String getRandomName() {
		int random = this.getRandom().nextInt(this.randomNames.size());
		return randomNames.get(random);
	}
	
	public void addWorm(){
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
	
	public void addFood(){
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
	public boolean isPassableArea(double x, double y, double radius){
		double step = Math.min(getStep(), radius*0.01);
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

	private double getStep() {
		double step = Math.min(getHeight()/getDimensionInPixels(false), getWidth()/getDimensionInPixels(true))/2.0;
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
	public boolean isAdjacent(double x, double y,double radius){
		if ((x-radius*1.1) < 0 || (x+radius*1.1) > getWidth() || (y-radius*1.1) < 0 || (y + radius*1.1) > getHeight())
			return false;
		return !isPassableArea(x,y,1.1*radius) && isPassableArea(x,y,radius);
	}
	
	
	public double[] getRandomAdjacentLocation(double radius){
		double X = (getRandom().nextDouble())*(getWidth()-radius*2.0)+radius;
		double Y = 0;
		double angle = Math.tan(getHeight()*0.5/(getWidth()*0.5-X));
		if (Double.isNaN(angle));
			angle = Math.PI/2.0;
		double step = Math.min(getStep(), radius*0.01);
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
	
	
	
	
	public List<Team> getTeams() {
		return this.teams;
	}
	
	
	public void addAsTeam(Team team) {
		this.teams.add(team);
	}
	
	
	public int getNumberOfTeams() {
		return this.getTeams().size();
	}
	
	
	public Team getTeamAt(int i) throws ModelException {
		if (i >= this.getNumberOfTeams())
			throw new ModelException("Not possible to select that team!");
		return this.getTeams().get(i);
	}
	
	
	public void addTeam(String name) throws ModelException {
		if (this.getStatus())
			throw new ModelException("Cannot make team once game has started!");
		if (this.getNumberOfTeams() > 10)
			throw new ModelException("World can only have up to 10 teams");
		Team team = new Team(name);
		this.addAsTeam(team);
	}
}
