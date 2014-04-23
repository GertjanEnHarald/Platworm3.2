package worms.model;
import java.util.Arrays;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class describing the attributes and actions of worms.
 * 
 * @version 2.38
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 * 
 * @invar	The x coordinate of the worm must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the worm must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the worm must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The direction of the worm is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The radius of the worm must be a valid radius.
 * 			| this.isValidRadius(this.getRadius())
 * @invar	The name of the worm must be a valid name.
 * 			| isValidName(this.getName())
 * @invar	The worm has a valid number of action points. The action points
 * 			must be greater than or equal to zero and smaller than or equal to the maximum action points of the worm.
 * 			| 0 <= this.getActionPoints() && this.getActionPoints() <= this.getMaximumActionPoints()
 * @invar	The worm has a valid number of hit points. The hit points
 * 			must be greater than or equal to zero and smaller than or equal to the maximum hit points of the worm.
 * 			| 0 <= this.getHitPoints() && this.getHitPoints() <= this.getMaximumHitPoints()
 * @invar	The worm must have a proper world. This worm should be in a world that contains this worm.
 * 			| this.hasProperWorld()
 * @invar	The worm must be in a proper team. If this worm has a team, that team should contain this worm.
 * 			| this.hasProperTeam()
 */



public class Worm extends MovableObject{
	
	/**
	 * Variable initialization.
	 */
	/**
	 * The action points of this worm.
	 */
	private int actionPoints;
	/**
	 * The hit points of this worm.
	 */
	private int hitPoints;
	/**
	 * The name of this worm.	
	 */
	private String name;
	/**
	 * The minimum radius of this worm.
	 */
	private static final double minimumRadius=0.25;
	/**
	 * The density of this worm.
	 */
	private final double density=1062;
	/**
	 * The projectile that this worm currently carries.
	 */
	private Projectile projectile;
	/**
	 * The team of this worm.
	 */
	private Team team;
	/**
	 * A boolean representing if this worm has just eaten worm food.
	 */
	private boolean hasJustEaten;
	/**
	 * The number of the current weapon of this worm.
	 */
	private int currentWeaponNumber = 0;
	
	
	
	/**
	 * Creates worm with given coordinates, direction, radius and Name. The action points are set to the maximum.
	 * 
	 * @param 	coordinateX
	 * 			The x coordinate for this new worm.
	 * @param 	coordinateY
	 * 			The y coordinate for this new worm.
	 * @param	radius
	 * 			The radius for this new worm.
	 * @param 	isActive
	 * 			The status for this new worm.
	 * @param 	world
	 * 			The world for this new worm.
	 * @param 	direction
	 * 			The direction for this new worm.
	 * @param	name
	 * 			The name for this new worm.
	 * @param	isActive
	 * 			The status of this new worm.
	 * @param	world
	 * 			The world of this new worm.
	 * 
	 * @post	The x coordinate of this worm is set to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX
	 * @post	The y coordinate of this worm is set to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY
	 * @post	The direction of this worm is set to the given direction.
	 * 			| new.getDirection() == changeAngleModulo2PI(direction)
	 * @post	The radius of this worm is set to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The name of this worm is set to the given name.
	 * 			| new.getName() == name
	 * @post	The world of this worm is set to the given world.
	 * 			| new.getWorld() == world
	 * @post	The new status of this worm is set to the given isActive.
	 * 			| new.getStatus() == isActive
	 * @post 	This worm has the maximum number of action points.
	 * 			| new.getActionPoints() == new.getMaximumActionPoints()
	 * @post	This worm has the maximum number of hit points.
	 * 			| new.getHitPoints() == new.getMaximumHitPoints()
	 * @post	This worm is equipped with a weapon.
	 * 			| new.getProjectile() isinstance Projectile
	 * 
	 * @effect	If one of the given coordinates is not in this world, this worm is terminated.
	 * 			| if (this.isXCoordinateOutOfBounds(coordinateX) || this.isYCoordinateOutOfBounds(coordinateY))
	 * 			|		then this.terminate()
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if one or more of the given parameters are illegal 
	 * 			assignments for this worm.
	 * 			| ((! isValidCoordinate(coordinateX)) || (! isValidCoordinate(coordinateY)) ||
	 * 			|		(! isValidDirection(direction)) || (! isValidRadius(radius)) ||
	 * 			|		(! isValidName(name)))
	 * 
	 */
	@Raw
	protected Worm(double coordinateX, double coordinateY, double direction, double radius, 
			String name, boolean isActive, World world) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, radius, world, direction);
		if (this.getStatus()){
			this.setActionPoints(this.getMaximumActionPoints());
			this.setHitPoints(this.getMaximumHitPoints());
			this.setName(name);
			this.setProjectile();
		}
	}	
	
	
	
	
	/**
	 * Returns the density of this worm.
	 */
	@Basic
	@Immutable
	@Raw
	protected final double getDensity(){
		return this.density;
	}

	
	
	
	/**
	 * Sets the coordinates of this worm.
	 * 
	 * @post	If this worm does not overlap with a food object, it will not have just eaten.
	 * 			| if (this.getWorld.getFoodThatOverlaps(this) != null)
	 * 			|		then new.hasJustEaten() == true
	 * 			| else new.hasJustEaten() == false
	 *
	 * @effect	If this worm overlaps with a food object, this worm will eat it.
	 * 			| if (this.getWorld.getFoodThatOverlaps(this) != null)
	 * 			|		then this.eat()
	 */
	@Override
	protected void setCoordinates(double x, double y) throws ModelException  {
		this.hasJustEaten = false;
		super.setCoordinates(x, y);
		// If setCoordinates terminates this worm, this function will throw a NullPointer.
		try {
			eatFoodIfPossible();
		} catch (NullPointerException exc) {}
	}
	
	
	/**
	 * Set the x coordinate to the given coordinate.
	 * 
	 * @effect	If this worm is still active, then update projectile.
	 * 			| if(this.getStatus())
	 * 			|	then this.updateProjectile()
	 */
	@Override
	protected void setX(double coordinateX) throws ModelException {
		super.setX(coordinateX);
		if (this.getStatus())
			this.updateProjectile();
	}
	
	
	/**
	 * Set the y coordinate to the given coordinate.
	 * 
	 * @effect	If this worm is still active, then update projectile.
	 * 			| if(this.getStatus())
	 * 			|	then this.updateProjectile()
	 */
	@Override
	protected void setY(double coordinateY) throws ModelException {
		super.setY(coordinateY);
		if (this.getStatus())
			this.updateProjectile();
	}
	
	
	
	/**
	 * Returns the minimum radius of this worm.
	 * 
	 * @return 	The radius is a positive number.
	 * 			| result > 0
	 */
	@Basic
	@Raw
	@Immutable
	protected static double getMinimumRadius() {
		return minimumRadius;
	}
	
	
	/**
	 * Checks if the given radius is a valid radius for this worm.
	 * 
	 * @param 	radius
	 *			The radius to check.
	 *
	 * @return	True if and only if the given radius is larger than the minimum radius 
	 * 			and the radius is a number or infinity and the mass for this radius is
	 * 			a valid integer.
	 * 			| result == ((radius >= getMinimumRadius()) && (! Double.isNaN(radius)))
	 * 			| && getMass(radius) <= Integer.MAX_VALUE
	 */
	@Override
	protected boolean isValidRadius(double radius) {
		return (radius >= getMinimumRadius()) && (! Double.isNaN(radius)) && (this.getMass(radius) <= Integer.MAX_VALUE);
	}

	
		
	
	/**
	 * Returns the current mass of this worm.
	 * 
	 * @return	Returns the current mass of this worm.
	 * 			If the mass is larger than Integer.Max_Value,
	 * 			Integer.Max_Value is returned.
	 * 			| if (this.getMass(this.getRadius()) > Integer.MAX_VALUE)
	 * 			|		result == Integer.MAX_VALUE
	 * 			| else 
	 * 			|		result == this.getMass(this.getRadius())
	 */
	@Basic
	@Override
	protected double getMass(){
		double mass = this.getMass(this.getRadius());
		if (mass > Integer.MAX_VALUE)
			mass = Integer.MAX_VALUE;
		return mass;
	}
	
	
	/**
	 * Calculates the mass of this worm if it were to have the given radius.
	 * 
	 * @param 	radius
	 * 			The radius for which the mass should be calculated.
	 * 
	 * @return	Returns the calculated mass.
	 * 			| result == (this.getDensity()*4/3*Math.PI*Math.pow(radius, 3))
	 */
	@Raw
	protected double getMass(double radius){
		return (this.getDensity()*(4.0/3.0)*Math.PI*Math.pow(radius, 3));
	}
	
	
	
	
	/**
	 * Returns the current number of action points of this worm.
	 */
	@Basic
	@Raw
	protected int getActionPoints(){
		return this.actionPoints;
	}
	
	
	/**
	 * Returns the maximum number of action points that this
	 * worm can have.
	 */
	protected int getMaximumActionPoints(){
		return (int) (Math.round(this.getMass()));
	}
	
	
	/**
	 * Set the action points that this worm has.
	 * 
	 * @param 	actionPoints
	 * 			The new number of action points.
	 * 
	 * @post	If the given number of action points is in the interval [0, getMaximumActionPoints()],
	 * 			the action points of this worm are set to the given actionPoints.
	 * 			| if ((0 <= actionPoints) && (actionPoints <= getMaximumActionPoints()))
	 * 			| 	then new.getActionPoints() == actionPoints
	 * @post	If the given number of action points is negative,
	 * 			the actionPoints of this worm are set to 0.
	 * 			| if (actionPoints < 0)
	 * 			| 	then new.getActionPoints() == 0
	 * @post	If the given number of action points is larger than the maximum action points,
	 * 			the actionPoints of this worm are set to the maximum number of action points.
	 * 			| if (actionPoints > getMaximumActionPoints())
	 * 			| 	then new.getActionPoints() == this.getMaximumActionPoints()
	 * 
	 * @effect	If the action points are set to zero, the turn of this worm ends.
	 * 			| if (new.getActionPoints() == 0)
	 * 			|		then this.getWorld().nextTurn()
	 */
	protected void setActionPoints(int actionPoints){
		if ((0 < actionPoints) && (actionPoints <= this.getMaximumActionPoints()))
			this.actionPoints = actionPoints;
		else if (actionPoints <= 0){
			this.actionPoints = 0;
			this.getWorld().nextTurn(); 
		}
		else if (actionPoints > this.getMaximumActionPoints())
			this.actionPoints = this.getMaximumActionPoints();
	}
	
	
	
	
	/**
	 * Returns the current hit points of this worm.
	 */
	@Basic
	@Raw
	protected int getHitPoints() {
		return this.hitPoints;
	}
	
	
	/**
	 * Set the hit points that this worm has.
	 * 
	 * @param 	hitPoints
	 * 			The new number of hit points.
	 * 
	 * @post	If the given number of hit points is in the interval [0, getMaximumHitPoints()],
	 * 			the hit points of this worm are set to the given hitPoints.
	 * 			| if ((0 <= hitPoints) && (hitPoints <= getMaximumHitPoints()))
	 * 			| 	new.getHitPoints() == hitPoints
	 * @post	If the given number of hit points is negative,
	 * 			the hitPoints of this worm are set to 0.
	 * 			| if (hitPoints < 0)
	 * 			| 	new.getHitPoints() == 0
	 * @post	If the given number of hit points is larger than the maximum hit points,
	 * 			the hitPoints of this worm are set to the maximum number of hit points.
	 * 			| if (actionPoints > getMaximumHitPoints())
	 * 			| 	new.getHitPoints() == this.getMaximumHitPoints()	
	 */
	protected void setHitPoints(int hitPoints) {
		if ((0 <= hitPoints) && (hitPoints <= this.getMaximumHitPoints()))
			this.hitPoints = hitPoints;
		else if (hitPoints < 0) {
			this.hitPoints = 0;
			this.terminate();
		}
		else if (hitPoints > this.getMaximumHitPoints())
			this.hitPoints = this.getMaximumHitPoints();
	}
	
	
	/**
	 * Returns the maximum number of hit points this worm can have.
	 */
	protected int getMaximumHitPoints() {
		return (int) Math.round(this.getMass());
	}
	
	
	/**
	 * Returns if this worm is alive or not.
	 * 
	 * @return	This worm is alive if its number of hitpoints is 
	 * 			larger than 0.
	 * 			| result == (this.getHitPoints() > 0)
	 */
	protected boolean isAlive() {
		return (this.getHitPoints() > 0);
	}
	
	
	
	
	/**
	 * Returns the current name of this worm.
	 */
	@Basic
	@Raw
	protected String getName(){
		return this.name;
	}
	
	
	/**
	 * Set the name of this worm to the given name.
	 * 
	 * @param 	assignedName
	 * 			New name of this worm.
	 * 
	 * @post	The new name of this worm will be equal to assignedName.
	 * 			| new.getName() == assignedName
	 * 
	 * @throws 	ModelException
	 * 			The assignedName is not a legal name for a worm.
	 * 			| ! isValidName(assignedName)
	 */
	@Raw
	protected void setName(String assignedName) throws ModelException {
		if (! isValidName(assignedName))
			throw new ModelException("Illegal assigned name!");
		this.name = assignedName;
	}
	
	
	/**
	 * Checks if the given name is a legal name.
	 * 
	 * @param 	name
	 * 			The name that needs to be checked.
	 * 
	 * @return	The result is true if and only if the 3 following conditions are all true:
	 * 			- The length of the name must be at least two characters long.
	 * 			- The first character of the name must be an uppercase letter.
	 * 			- The name can only contain letters (uppercase and lowercase),
	 * 			  quotes (single and double) and spaces.
	 * 			| return == (name.length() >= 2) &&
	 * 			|			(Character.isUpperCase(name.charAt(0))) &&
	 * 			|			(for each character in name:
	 * 			|				(isLetter(character) || character in {" ", "'", """}))
	 */
	@Raw
	protected static boolean isValidName(String name){
		List<String> validCharacters = Arrays.asList(" ", "\'", "\"");
		if (name.length() < 2)
			return false;
		if (! Character.isUpperCase(name.charAt(0)))
			return false;
		for (char element: name.toCharArray()){
			if ( !((Character.isLetterOrDigit(element)) || (validCharacters.contains(String.valueOf(element)))) )
				return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * Returns the team of this worm.
	 * If the worm has not joined a team, null is returned.
	 */
	protected Team getTeam() {
		return this.team;
	}
	
	
	/**
	 * Let this worm join the given team, before the game has started.
	 * 
	 * @param 	team
	 * 			The new team of this worm.
	 * 
	 * @post	The worm is assigned to the given team.
	 * 			| new.getTeam() == team
	 * 
	 * @throws 	ModelException
	 * 			The exception is thrown if the assignment happens 
	 * 			after the game has already started.
	 * 			| this.getWorld().getStatus()
	 */
	protected void joinTeam(Team team) throws ModelException {
		if (this.getWorld().getStatus())
			throw new ModelException("Game has already started, cannot assign to team!");
		this.setTeam(team);
	}
	
	
	/**
	 * Set the team of this worm.
	 * 
	 * @param 	team
	 * 			The new team of this worm.
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if the given team is invalid.
	 * 			| (! isValidTeam(team))
	 */
	private void setTeam(Team team) throws ModelException {
		if (! isValidTeam(team))
			throw new ModelException("Invalid team assignment!");
		this.team = team;
	}
	
	
	/**
	 * Checks whether the given team is a valid team.
	 * 
	 * @param 	team
	 * 			The team that needs to be checked.
	 * 
	 * @return	Returns if the given team isn't null.
	 * 			| result == (team != null)
	 */
	protected static boolean isValidTeam(Team team) {
		return (team != null);
	}
	
	
	/**
	 * Checks whether this worm has a proper team.
	 * 
	 * @return	Returns if the worm is not in a team or
	 * 			if his team contains this worm.
	 * 			| result == (this.getTeam() == null)
	 * 			|				|| (this.getTeam().getWorms.contains(this))
	 */
	protected boolean hasProperTeam() {
		return (this.getTeam() == null) || (this.team.getWorms().contains(this));
	}




	/**
	 * Changes the direction of this worm by adding given angle and using the appropriate number of action points.
	 * 
	 * @param 	angle
	 * 			The angle to be added to the direction.
	 * 
	 * @pre		New direction should be an existing angle and this worm needs enough
	 * 			action points to turn.
	 * 			| this.canTurn(angle)

	 * @post	The given angle is added to this worm's direction.
	 * 			| new.getDirection() == changeAngleModulo2PI(this.getDirection() + angle)
	 * @post	The action points needed to execute the turn are subtracted from
	 * 			this worm's action points.
	 * 			| new.getActionPoints() == this.getActionPoints() - ((int)usedActionPointsTurn(angle)+1)
	 * @post	The direction of the worm's projectile is also updated.
	 * 			| new.getProjectile().getDirection() == new.getDirection()
	 */
	protected void turn(double angle){
		assert this.canTurn(angle);
		this.setDirection(this.getDirection()+angle);
		int usedActionPoints = (int) (usedActionPointsTurn(angle)) + 1;
		this.setActionPoints(this.getActionPoints() - usedActionPoints);
		this.updateProjectile();
	}

	
	/**
	 * Checks if this worm can turn the given angle.
	 * 
	 * @param 	angle
	 * 			The angle this worm should turn.
	 * 
	 * @return	Returns true if this worm can turn the given angle.
	 * 			The new direction should be a valid direction and there should be enough action points
	 * 			to execute the turn.
	 * 			| result == (usedActionPointsTurn(angle) <= this.getActionPoints()) && 
	 * 			| 			(isValidDirection(this.getDirection()+angle)) && 
	 * 			|			(usedActionPointsTurn(angle) < Integer.MAX_VALUE)
	 */
	protected boolean canTurn(double angle){
		double usedActionPoints = usedActionPointsTurn(angle);
		return (usedActionPoints <= this.getActionPoints()) 
				&& (isValidDirection(this.getDirection()+angle)) && (usedActionPoints <= Integer.MAX_VALUE);
	}

	
	/**
	 * Calculates the action points needed for a worm to execute a turn over the given angle.
	 *
	 * @param 	angle
	 * 			The angle the worm should turn.
	 * 
	 * @return	Returns the minimum action points needed to execute the turn.
	 * 			The minimum is calculated of both possible rotations, clockwise and counterclockwise.
	 * 			| result ==  MIN(((60*Math.abs(changeAngleModulo2PI(angle))/(2*Math.PI))),
	 * 			|					((60*Math.abs(changeAngleModulo2PI(angle)-2*Math.PI)/(2*Math.PI))))
	 */
	@Raw
	protected static double usedActionPointsTurn(double angle) {
		return Math.min((60*Math.abs(changeAngleModulo2PI(angle))/(2*Math.PI)), (60*Math.abs(changeAngleModulo2PI(angle)-2*Math.PI)/(2*Math.PI)));
	}
	
	
	
	
	/**
	 * Changes the position of this worm as the result of a jump in the current direction
	 * of this worm, consuming all action points. The direction does not change during the jump.
	 * 
	 * @param	timeStep
	 * 			A time interval during which the worm will not move completely trough impassable terrain.
	 * 
	 * @effect	The new x and y coordinates are assigned to this worm. The end position of the jump 
	 * 			is calculated by getting the step at the last position of the jump.
	 * 			| position = this.getJumpStep(this.getJumpRealTimeInAir(10^-5))
	 * 			| this.setCoordinates(position[0], position[1])
	 * 
	 * @post	If the worm is still active after the jump, 
	 * 			then all action points are used, even if this worm couldn't jump in its current situation.
	 * 			| if this.getStatus()
	 * 			| 	then new.getActionPoints() == 0
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if this worm cannot jump in its current situation.
	 * 			| ! this.canJump()
	 */
	@Override
	protected void jump(double timeStep) throws ModelException {
		if (! this.canJump()) {
			this.setActionPoints(0);
			throw new ModelException("Cannot jump!");
		}
				
		double[] position = this.getJumpStep(this.getJumpRealTimeInAir(Math.pow(10, -5)));
		this.setCoordinates(position[0], position[1]);

		//	If setCoordinates terminates this worm, these function shall only work if the status is active.

		if (this.getStatus())
			this.setActionPoints(0);

	}

	
	/**
	 * Returns the initial velocity of the potential jump of this worm.
	 * 
	 * @return	The initial velocity of the jump.
	 * 			| F = 5*this.getActionPoints()+this.getMass()*this.getWorld().getGravity()
	 *			| result == F*0.5/this.getMass()
	 */
	@Override
	protected double getJumpVelocity(){
		double F = 5*this.getActionPoints()+this.getMass()*this.getWorld().getGravity();
		return F*0.5/this.getMass();
	}
	
	
	/**
	 * Checks whether this worm can jump in his current situation.
	 * 
	 * @return	Returns true if this worm still has some action points and is still alive.
	 * 			| result == (this.isAlive()) && ((this.getActionPoints() > 0)
	 */
	@Override
	protected boolean canJump() {
		return (this.isAlive()) && (this.getActionPoints() > 0);
	}
	
	
	/**
	 * Calculates the time that this worm will be in the air.
	 * 
	 * @param	step
	 * 			A time interval during which the worm will not move completely trough impassable terrain.
	 * 
	 * @return	Returns the time that this worm is in the air until it reaches impassable terrain
	 * 			or it leaves the world. If the worm leaves the world, extra time is provided to make 
	 * 			the worm visually disappear.
	 * 			| for each time in {t | t in 0..result & t = n*step (with n integer)}
	 * 			|	position = this.getJumpStep(t)
	 * 			|	this.getWorld().isPassableArea(position[0],position[1], this.getRadius()) == true
	 * 			| 
	 * 			| nextPosition = this.getJumpStep(result+step)
	 * 			| ! this.getWorld().isPassableArea(nextPosition[0], nextPosition[1], this.getRadius())  == true
	 * 			|
	 * 			| if(! this.getWorld().isInWorld(position[0], position[1], this.getRadius()))
	 * 			|	then result = result + 0.20 
	 */
	@Override
	protected double getJumpRealTimeInAir(double step) {
		double time = 0.0;
		boolean hasLanded = false;
		step = 100.0*step;

		for (double t = step; (! hasLanded) ; t = t + step) {
			double[] position = this.getJumpStep(t);
			if (this.getWorld().isPassableArea(position[0], position[1], this.getRadius())) {
				time = t;}
			else if(! this.getWorld().isInWorld(position[0], position[1], this.getRadius())) {
				time = time+0.20;
				hasLanded = true;
			}
			else {
				hasLanded = true;}
		}

		return time;
	}
	
	
	

	/**
	 * Returns the number of the current weapon.
	 */
	protected int getCurrentWeaponNumber() {
		return this.currentWeaponNumber;
	}
	
	
	/**
	 * Set the current weapon number to the given number.
	 * 
	 * @param 	number
	 * 			The new weapon number of this worm.
	 * 
	 * @post	The new weapon number of this worm is equal to the given number.
	 * 			| new.getCurrentWeaponNumber()  == number
	 * 
	 * @throws	ModelException
	 * 			Throws an exception if there is no weapon that corresponds to the given number.
	 * 			| getNumberOfProjectiles() <= number
	 */
	private void setCurrentWeaponNumber(int number) throws ModelException{
		if (getNumberOfProjectiles() <= number)
			throw new ModelException("Illegal weapon assignment!");
		this.currentWeaponNumber = number;
	}
	
	
	/**
	 * Returns the number of possible weapons to propel projectiles.
	 */
	protected static int getNumberOfProjectiles() {
		return 2;
	}
	
	
	/**
	 * Returns the current projectile of this worm.
	 */
	protected Projectile getProjectile(){
		return this.projectile;
	}
	
	
	/**
	 * Creates a new projectile for this worm.
	 * 
	 * @post	The new projectile's x coordinate lies at the border of this worm, according to its direction.
	 * 			| (new this.getProjectile()).getCoordinateX() == this.getCoordinateX() 
	 * 			|												+ this.getRadius()*Math.cos(this.getDirection())
	 * @post	The new projectile's y coordinate lies at the border of this worm, according to its direction.
	 * 			| (new this.getProjectile()).getCoordinateY() == this.getCoordinateY() 
	 * 			|												+ this.getRadius()*Math.sin(this.getDirection())
	 * @post	The new projectile is active.
	 * 			| (new this.getProjectile()).getStatus()
	 * @post	The new projectile is in the same world as this worm.
	 * 			| (new this.getProjectile()).getWorld() == this.getWorld()
	 * @post	The new projectile has the same direction as this worm.
	 * 			| (new this.getProjectile()).getDirection() == this.getDirection()
	 * @post	The new projectile is fired from a rifle or a bazooka, according to the current waepon number.
	 * 			| if (this.getCurrentWeaponNumber() == 0)
	 * 			|		then this.getProjectile() isinstance Rifle
	 * 			| else this.getProjectile() isinstance Bazooka
	 */
	private void setProjectile() {
		if (this.getCurrentWeaponNumber() == 0)
			this.projectile = new Rifle(this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection()),
					this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection()), 
					true, this.getWorld(), this.getDirection());
		if (this.getCurrentWeaponNumber() == 1)
			this.projectile = new Bazooka(this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection()),
					this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection()), 
					true, this.getWorld(), this.getDirection());	
	}
	
	
	/**
	 * Selects the newt weapon for this worm and terminates the old weapon.
	 * 
	 * @effect	Terminates the old projectile.
	 * 			| this.getProjectile().terminate()
	 * @effect	Creates a new projectile for this worm, corresponding with the new current weapon number.
	 * 			| this.setWeapon()
	 * 
	 * @post	The current weapon number is increased with one 
	 * 			and becomes 0 if it equals getNumberOfProjectiles()
	 * 			| new.getCurrentWeaponNumber() == (this.getCurrentWeaponNumber() + 1) % getNumberOfProjectiles()
	 */
	protected void selectWeapon() {
		this.projectile.terminate();
		this.setCurrentWeaponNumber((this.getCurrentWeaponNumber()+1) % getNumberOfProjectiles());
		this.setProjectile();
	}
	
	
	/**
	 * Shoot a projectile from this worm's current weapon with the given yield.
	 * 
	 * @param 	yield
	 * 			The yield to determine the force to propel the projectile.
	 * 
	 * @post	Set the yield of this projectile to the given yield.
	 * 			| (new this.getProjectile()).getYield == yield
	 * @post	Subtract the action points that it requires to shoot this weapon.
	 * 			| new.getActionPoints() == this.getActionPoints() - this.getProjectile().getCostActionPoints() 
	 */
	protected void shoot(int yield) throws ModelException {
		this.projectile.setYield(yield);
		this.setActionPoints(this.getActionPoints()-this.projectile.getCostActionPoints());
	}
	
	
	/**
	 * Update a projectile's position and direction according to this worm.
	 * 
	 * @post	The new x coordinate of this worm's projectile is equal to the sum of this worm's x coordinate
	 * 			and the projection of this worm's radius to the x-axis.
	 * 			| (new this.getProjectile()).getCoordinateX() == this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection())
	 * @post	The new y coordinate of this worm's projectile is equal to the sum of this worm's y coordinate
	 * 			and the projection of this worm's radius to the y-axis.
	 * 			| (new this.getProjectile()).getCoordinateY() == this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection())
	 * @post	The new direction of this worm's projectile is equal to this worm's direction.
	 * 			| (new this.getProjectile()).getDirection() == this.getDirection()
	 */
	private void updateProjectile() throws ModelException {
		if (this.getProjectile() != null) {
			this.projectile.setCoordinates(this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection()),
										   this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection()));
			this.projectile.setDirection(this.getDirection());
		}
	}
	
	
	
	
	/**
	 * Let this worm fall until it leaves the world or hit impassable terrain.
	 * 3 hit points are deduced for each meter of this fall.
	 * 
	 * @post	3 hit points are deduced for each meter that the worm has fallen.
	 * 			| new.getHitPoints() == this.getHitPoints() - 3*(new.getCoordinateY() - this.getCoordinateY())
	 * 
	 * @effect	Set the y coordinate to the first coordinate underneath
	 * 			from where this worm cannot fall any further.
	 * 			| Y = this.getCoordinateY()
	 * 			| while this.canFall() && Y > 0.1*this.getRadius()
	 * 			|	do: Y = Y - (this.getWorld().getStep(this.getRadius())))
	 * 			| this.setY(Y)
	 */
	protected void fall() throws ModelException {
		if (this.canFall()) {
			double startY = this.getCoordinateY();
			double finalY = startY;
			for(double Y = startY; this.canFall(this.getCoordinateX(), finalY)&&(Y>-0.1*getRadius());
					Y = Y - (this.getWorld().getStep(this.getRadius()))) {
					finalY = Y; 
			}
			this.setY(finalY);
			if (this.getStatus()) {
				this.setHitPoints(this.getHitPoints() - (int) (Math.round((startY - finalY)*3.0)));
			}
		}
	}
	
	
	/**
	 * Checks if this worm can fall.
	 * 
	 * @return	This worm can fall if it is not at an adjacent location and if has not just eaten.
	 * 			| result == this.canFall(this.getCoordinateX(), this.getCoordinateY())
	 * 			|				&& (! this.hasJustEaten())
	 */
	protected boolean canFall() {
		return this.canFall(this.getCoordinateX(), this.getCoordinateY()) && !this.hasJustEaten();
	}
	
	
	/**
	 * Checks if this worm could fall from a given position x,y.
	 * 
	 * @return	This worm can fall if it is not located at an adjacent location inn its world.
	 * 			| result == (! this.getWorld().isAdjacent(x, y, this.getRadius()))
	 */
	protected boolean canFall(double x, double y) {
		return (! this.getWorld().isAdjacent(x, y, this.getRadius()));
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Calculates the amount of action points needed to move the given number of steps.
	 *
	 * 
	 * @param 	NbSteps
	 * 			The number of steps the worm should move.
	 * @param 	theta
	 * 			The direction of movement.
	 * 
	 * @return	Returns the amount of action points needed to execute the moves.
	 * 			| result == NbSteps*((Math.abs(Math.cos(theta)) + 4*Math.abs(Math.sin(theta))))
	 */
	@Raw
	protected static double usedActionPointsMove(int NbSteps, double theta) {
		double usedActionPoints = NbSteps*( (Math.abs(Math.cos(theta)) + 4*Math.abs(Math.sin(theta))));
		return usedActionPoints;
	}
	
	
	/**
	 *  Moves this worm between 0.1m and the worm's radius in approximately the worm's direction,
	 *  if such a move is possible. In most cases multiple valid positions are possible for the worm 
	 *  to end up in as a result of such a move. In this case this method will always choose ending up
	 *  in an adjacent position above ending up in a passable position. When choosing between different
	 *  passable positions or different adjacent positions to end up in. The method will try a choose a 
	 *  position that keeps the divergence minimal and the covered distance maximal. The divergence is
	 *  the difference between the current direction of the worm and the direction in which the worm 
	 *  would have to move to end up in the position in question.
	 *  
	 * @effect	Executes the optimal move, as described above. 
	 * 			|if (Math.abs(getMaxCoverableDistanceAdjacent(direction)/(Math.abs(direction-getDirection())+0.5))) >= (Math.abs(getMaxCoverableDistanceAdjacent(direction2)/(Math.abs(direction2-getDirection())+0.5)))
	 *			|   	for each direction,direction2 in {direction| direction in (getDirection()-0.7875)..(getDirection()+0.7875)  & direction= getDirection()-n*0.0175 (with n integer)}
	 *			|			then
	 *			|				toBeExecutedDirectionAdjacent = direction
	 *			|				toBeExecutedStepsAdjacent = getMaxCoverableDistanceAdjacent(direction)
	 *			|if (Math.abs(getMaxCoverableDistancePassable(direction)/(Math.abs(direction-getDirection())+0.5))) >= (Math.abs(getMaxCoverableDistancePassable(direction2)/(Math.abs(direction2-getDirection())+0.5)))
	 *			|   	for each direction,direction2 in {direction| direction in (getDirection()-0.7875)..(getDirection()+0.7875)  & direction= getDirection()-n*0.0175 (with n integer)}
	 *			|			then
	 *			|				toBeExecutedDirectionPassable = direction
	 *			|				toBeExecutedStepsPassable = getMaxCoverableDistancePassable(direction)
	 *			|
	 *			|if toBeExecutedStepsAdjacent > 0
	 *			|	then this.move(toBeExecutedStepsAdjacent,toBeExecutedDirectionAdjacent)
	 *			|else
	 *			|	then this.move(toBeExecutedStepsPassable,toBeExecutedDirectionPassable)
	 *
	 * @throws	ModelException
	 *			This worm cannot move.
	 *			| !this.canMove()	
	 *
	 */
	protected void move() throws ModelException {
		if (!canMove())
			throw new ModelException("This worm cannot move!");
		double maxSuccesOfMoveValue = 0.0;
		double toBeExecutedDirection = getDirection();
		double toBeExecutedSteps = 0.0;
		for(double direction = getDirection()-0.7875;direction <= getDirection()+0.7875;direction = direction + 0.0175){
			double possibleMaxSuccesOfMoveValue = Math.abs(getMaxCoverableDistanceAdjacent(direction)/(Math.abs(direction-getDirection())+0.5));
			if (maxSuccesOfMoveValue < possibleMaxSuccesOfMoveValue){
				maxSuccesOfMoveValue = possibleMaxSuccesOfMoveValue;
				toBeExecutedSteps = getMaxCoverableDistanceAdjacent(direction);
				toBeExecutedDirection = direction;
				
			}
			}
		if (toBeExecutedSteps > 0.0){
			move(toBeExecutedSteps,toBeExecutedDirection);
			return;
		}
			
		for(double direction = getDirection()-0.7875;direction <= getDirection()+0.7875;direction = direction + 0.0175){
				double possibleMaxSuccesOfMoveValue = Math.abs(getMaxCoverableDistancePassable(direction)/(Math.abs(direction-getDirection())+0.5));
				if (maxSuccesOfMoveValue < possibleMaxSuccesOfMoveValue){
					maxSuccesOfMoveValue = possibleMaxSuccesOfMoveValue;
					
					toBeExecutedSteps = getMaxCoverableDistancePassable(direction);
					toBeExecutedDirection = direction;
		}
		}
		if (toBeExecutedSteps > 0.0)
			move(toBeExecutedSteps,toBeExecutedDirection);
	}
	
	
	/**
	 * Moves the worm a given amount of steps in the given direction if that
	 * move is possible(the end position should be valid and the worm should
	 * have enough action points to execute the move).
	 * 
	 * @param 	steps
	 * 			The steps to be moved
	 * @param 	direction
	 * 			The direction to be moved in.
	 * 
	 * @post 	The new position of the worm is the result of a move from the previous
	 * 			position in the given direction a given amount of steps.
	 * 			|new.getCoordinateX() == this.getCoordinateX()+this.getRadius()*steps*Math.cos(direction)
	 * 			|new.getCoordinateY() == this.getCoordinateY()+this.getRadius()*steps*Math.sin(direction)
	 * @post 	The necessary amount of action points needed for this move have been deducted
	 * 			from this worm's action points.
	 * 			|new.getActionPoints() == this.getActionPoints() -((int) usedActionPointsMove((int)steps+1, theta) +1)
	 * 
	 * @throws 	ModelException
	 * 			The requested move is not possible
	 * 			|(! this.canMove(steps,direction))
	 */
	protected void move(double steps,double direction) throws ModelException {
		if(! this.canMove(steps,direction))
			throw new ModelException("Illegal number of steps!");
		
		double x = this.getCoordinateX();
		double y = this.getCoordinateY();
		double theta = direction;
		int currentActionPoints = this.getActionPoints();
		int usedActionPoints = (int) usedActionPointsMove((int)steps+1, theta) +1;
		this.setCoordinates(x + this.getRadius()*steps*Math.cos(theta),y + this.getRadius()*steps*Math.sin(theta));
		this.setActionPoints(currentActionPoints - usedActionPoints);
	}
	
	
	
	/**
	 * Returns whether the worm can move. This means that it can move between 0.1m and the
	 * worms radius. And it can do that in approximately it's current direction.
	 * 
	 * @return	Returns true if the worm can move anywhere between 0.1m and its radius in any direction that 
	 * 			lies within the boundaries, (this worms direction -0.7875) and (this worms 
	 * 			direction +0.7875).
	 * 			| if (canMove(steps,direction))
	 * 			|	for any steps {steps| steps in 0.1/(getRadius()..1.0  & y= 1-n*0.1 (with n integer) & canMoveAdjacent(steps,direction)}
	 *			|   	for any direction in {direction| direction in (getDirection()-0.7875)..(getDirection()+0.7875)  & direction= getDirection()-n*0.0175 (with n integer)}
	 *			|			then result == true
	 *			|else
	 *			| 	result == false
	 */
	protected boolean canMove(){
		if (!getStatus())
			return false;
		for(double steps= 1.0; steps >= 0.1/(getRadius());steps = steps -0.1){
			for(double direction = getDirection()-0.7875; direction <= getDirection()+0.7875;direction = direction + 0.0175){
				if (canMove(steps,direction))
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns whether it is possible to move the given amount of steps in the given direction.
	 * For the move to be possible the resulting position must be valid(this means passable or 
	 * adjacent) and the worm must have enough action points to execute the move.
	 * 
	 * @param 	steps
	 * 			The steps to be checked.
	 * @param 	direction
	 * 			The direction to be checked.
	 * 
	 * @return	Returns true if the worm can move in the given direction the given amount of steps
	 * 			and end up in an adjacent or a passable position. Any adjacent location is also passable.
	 * 			| result == canMovePassable(steps,direction)
	 */
	protected boolean canMove(double steps, double direction){
		return canMovePassable(steps,direction);
	}
	
	
	/**
	 * Returns whether it is possible to move with the given amount of steps in the given direction
	 * and end up in an passable position.This method also considers whether this worm has enough 
	 * action points to do this.
	 * 
	 * @param 	steps
	 * 			The steps to be checked.
	 * @param 	direction
	 * 			The direction to be checked.
	 * 
	 * @return	Returns true if the resulting position is passable and if the worm has enough action points to execute the move.
	 * 			result == (getWorld().isPassable(getCoordinateX()+Math.cos(direction)*steps*getRadius(), getCoordinateY()+Math.sin(direction)*steps*getRadius(), getRadius()))
	 * 						&&
	 * 						isPossibleMoveWithCurrentActionPoints((int)steps +1, direction)
	 */						
	protected boolean canMovePassable(double steps, double direction) {
			if (!getWorld().isPassableArea(getCoordinateX()+Math.cos(direction)*steps*getRadius(), getCoordinateY()+Math.sin(direction)*steps*getRadius(), getRadius()))
				return false;
			return isPossibleMoveWithCurrentActionPoints((int)steps +1, direction);
		}

	
	/**
	 * Returns whether it is possible to move with the given amount of steps in the given direction
	 * and end up in an adjacent position.This method also considers whether this worm has enough 
	 * action points to do this.
	 * 
	 * @param 	steps
	 * 			The steps to be checked.
	 * @param 	direction
	 * 			The direction to be checked.
	 * 
	 * @return	Returns true if the resulting position is adjacent and if the worm has enough action points to execute the move.
	 * 			| result == (getWorld().isAdjacent(getCoordinateX()+Math.cos(direction)*steps*getRadius(), getCoordinateY()+Math.sin(direction)*steps*getRadius(), getRadius()))
	 * 			|			&&
	 * 			|			isPossibleMoveWithCurrentActionPoints((int)steps +1, direction)
	 */						
	protected boolean canMoveAdjacent(double steps,double direction){
		if (!getWorld().isAdjacent(getCoordinateX()+Math.cos(direction)*steps*getRadius(), getCoordinateY()+Math.sin(direction)*steps*getRadius(), getRadius()))
			return false;
		return isPossibleMoveWithCurrentActionPoints((int)steps +1, direction);
	}
	
	
	/**
	 * Returns whether it is possible to move in the given direction, the given
	 * amount of steps and with the current amount of action points. This method
	 * disregards whether the resulting position would be a valid position.
	 * 
	 * @param 	steps
	 *			The amount of steps to be checked. 
	 * @param 	direction
	 * 			The direction to be checked.
	 * 
	 * @return	Returns true if the move is possible. This means if this worm has more action points than would be needed to execute this move.
	 * 			|result == ((this.getActionPoints() >= usedActionPointsMove(steps, direction)) && (steps >0) && (usedActionPointsMove(steps, direction) <= Integer.MAX_VALUE))
	 */
	protected boolean isPossibleMoveWithCurrentActionPoints(int steps,double direction){
		int currentActionPoints = this.getActionPoints();
		double usedActionPoints = usedActionPointsMove(steps, direction);
		return ((currentActionPoints >= usedActionPoints) && (steps >0) && (usedActionPoints <= Integer.MAX_VALUE));
	}
	
	
	/**
	 * Returns the maximum distance that can be covered in a given direction,
	 * if this worm were to move one step and the resulting position would
	 * have to be adjacent.
	 * 
	 * @param 	direction
	 * 			The given direction.
	 * 
	 * @return	Returns a value between 0 and 1 that represents the fraction
	 * 			of the radius that can be moved in the given direction.
	 * 			|result >= steps
	 * 			|	for each steps in {steps| steps in 0.1/(getRadius()..1.0  & y= 1-n*0.1 (with n integer) & canMoveAdjacent(steps,direction)}
	 */
	protected double getMaxCoverableDistanceAdjacent(double direction){
		for(double steps= 1.0; steps >= 0.1/(getRadius());steps = steps -0.1){
			if (canMoveAdjacent(steps, direction))
				return steps;
		}
		return 0.0;
	}
	
	
	/**
	 * Returns the maximum distance that can be covered in a given direction.
	 * If this worm were to move one step and the resulting position would
	 * have to be passable.
	 * 
	 * @param 	direction
	 * 			The given direction.
	 * 
	 * @return	Returns a value between 0 and 1 that represents the fraction
	 * 			of the radius that can be moved in the given direction.
	 * 			|result >= steps
	 * 			|	for each steps in {steps| steps in 0.1/(getRadius()..1.0  & y= 1-n*0.1 (with n integer) & canMovePassable(steps,direction)}
	 */
	protected double getMaxCoverableDistancePassable(double direction){
		for(double steps= 1.0; steps >= 0.1/(getRadius());steps = steps -0.1){
			if (canMovePassable(steps, direction))
				return steps;
		}
		return 0.0;
	}
	
	
	
	
	/**
	 * Terminates this worm and terminates all relations of this worm.
	 * 
	 * @post	If this worm is part of a team, that team no longer holds this worm.
	 * 			| if (this.getTeam() != null)
	 * 			| 		then (! this.getTeam().getAllWorms().contains(this))
	 * @post	This worm is no longer part of a team.
	 * 			| new.getTeam() == null
	 * 
	 * @effect	This worm's projectile is terminated.
	 * 			| this.getProjectile().terminate()
	 */
	@Override
	protected void terminate(){
		super.terminate();
		if (this.getProjectile() != null)
			this.projectile.terminate();
		if (getTeam() != null){
			this.team.removeFromTeam(this);
			this.team=null;
		}
	}
		
	
	
	
	/**
	 * This worm eats the possible food if it overlaps with a food object.
	 * 
	 * @effect	If this worm overlaps with a food object in its world,
	 * 			this worm will eat that food object.
	 * 			| if (this.getWorld().getFoodThatOverlaps(this) != null)
	 * 			|		then eat(this.getWorld().getFoodThatOverlaps(this))
	 */
	private void eatFoodIfPossible(){
		if (getWorld().getFoodThatOverlaps(this)!=null)
			eat(getWorld().getFoodThatOverlaps(this));
	}


	/**
	 * This worm eats the given food.
	 * 
	 * @param 	food
	 * 			The food that the worm will eat.
	 * 
	 * @post	The radius of this worm grows with 10 percent.
	 * 			| new.getRadius() == 1.1*this.getRadius()
	 * @post	This worm has just eaten.
	 * 			| new.hasJustEaten()
	 * 
	 * @effect	The given food no longer exists.
	 * 			| food.terminate()
	 * 
	 */
	private void eat(Food food) {
		food.terminate();
		this.setRadius(1.1*this.getRadius());
		this.hasJustEaten = true;
	}
	
	
	/**
	 * Returns if this worm has just eaten.
	 */
	protected boolean hasJustEaten() {
		return this.hasJustEaten;
	}
	
	
	
	
	@Override
	protected Worm clone() throws CloneNotSupportedException {
		Worm cloned = (Worm) super.clone();
		if (this.getTeam() != null)
			cloned.setTeam(this.getTeam().clone());
		cloned.setProjectile();
		return cloned;
	}
	
}
	