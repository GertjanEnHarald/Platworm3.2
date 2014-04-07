package worms.model;
import java.util.Arrays;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * A class describing the attributes and actions of worms.
 * 
 * @version 1.04
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 * 
 * @invar	The x coordinate of the worm must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the worm must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the worm must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The radius of the worm must be a valid radius.
 * 			| isValidRadius(this.getRadius())
 * @invar	The name of the worm must be a valid name.
 * 			| isValidName(this.getName())
 * @invar	The direction of the worm is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The worm has a valid number of action points.The action points
 * 			must be greater than or equal to zero and smaller than or equal to the maximum action points of the worm.
 * 			| 0 <= this.getActionPoints() && this.getActionPoints() <= this.getMaximumActionPoints()
 *
 */



public class Worm extends MovableObject{
	
	/**
	 * Variable initialization.
	 */
	private int actionPoints;
	private int hitPoints;
	private String name;
	private static final double minimumRadius=0.25;
	private final double density=1062;
	private Projectile projectile;
	private Team team;
	
	
	
	/**
	 * 
	 * Creates worm with given coordinates, direction, radius and Name. The action points are set to the maximum.
	 * 
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
	 * @post 	This worm has the maximum number of action points.
	 * 			| new.getActionPoints() == new.getMaximumActionPoints()
	 * @post	isActive ...
	 * 			| ...
	 * @post	This worm has the maximum number of hit points.
	 * 			| new.getHitPoints() == new.getMaximumHitPoints()
	 * @throws	ModelException
	 * 			The exception is thrown if one or more of the given parameters are illegal 
	 * 			assignments for this worm.
	 * 			| ((! isValidCoordinate(coordinateX)) || (! isValidCoordinate(coordinateY)) ||
	 * 			|		(! isValidDirection(direction)) || (! isValidRadius(radius)) ||
	 * 			|		(! isValidName(name)))
	 * 
	 */
	@Raw
	public Worm(double coordinateX, double coordinateY, double direction, double radius, 
			String name, boolean isActive, World world) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, radius, world, direction);
		this.setActionPoints(this.getMaximumActionPoints());
		this.setHitPoints(this.getMaximumHitPoints());
		this.setName(name);
	}	
	
	
	
	
	/**
	 * Returns the density of this worm.
	 */
	@Basic
	@Immutable
	@Raw
	public final double getDensity(){
		return this.density;
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
	public static double getMinimumRadius() {
		return minimumRadius;
	}
	
	
	/**
	 * Checks if the given radius is a valid radius for this worm.
	 *
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
	public boolean isValidRadius(double radius) {
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
	public double getMass(){
		double mass = this.getMass(this.getRadius());
		if (mass > Integer.MAX_VALUE)
			mass = Integer.MAX_VALUE;
		return mass;
	}
	
	
	/**
	 * Calculates the mass of this worm if it were to have the given radius.
	 *
	 * 
	 * @param 	radius
	 * 			The radius for which the mass should be calculated.
	 * 
	 * @return	Returns the calculated mass.
	 * 			| result == (this.getDensity()*4/3*Math.PI*Math.pow(radius, 3))
	 */
	@Raw
	public double getMass(double radius){
		return (this.getDensity()*(4.0/3.0)*Math.PI*Math.pow(radius, 3));
	}
	
	
	
	
	/**
	 * Returns the current number of action points of this worm.
	 */
	@Basic
	@Raw
	public int getActionPoints(){
		return this.actionPoints;
	}
	
	
	/**
	 * Returns the maximum number of action points that this
	 * worm can have.
	 */
	public int getMaximumActionPoints(){
		return (int) (this.getMass());
	}
	
	
	/**
	 * Aangezien AP nooit echt op 0, moet er een minum zijn waarvoor de beurt eindigt.
	 * @return
	 */
	public int getMinimumActionPoints(){
		return getMaximumActionPoints()/50;
	}
	
	/**
	 * Set the action points that this worm has.
	 * 
	 * 
	 * @param 	actionPoints
	 * 			The new number of action points.
	 * 
	 * @post	If the given number of action points is in the interval [0, getMaximumActionPoints()],
	 * 			the action points of this worm are set to the given actionPoints.
	 * 			| if ((0 <= actionPoints) && (actionPoints <= getMaximumActionPoints()))
	 * 			| 	new.getActionPoints() == actionPoints
	 * @post	If the given number of action points is negative,
	 * 			the actionPoints of this worm are set to 0.
	 * 			| if (actionPoints < 0)
	 * 			| 	new.getActionPoints() == 0
	 * @post	If the given number of action points is larger than the maximum action points,
	 * 			the actionPoints of this worm are set to the maximum number of action points.
	 * 			| if (actionPoints > getMaximumActionPoints())
	 * 			| 	new.getActionPoints() == this.getMaximumActionPoints()
	 */
	protected void setActionPoints(int actionPoints){
		if ((getMinimumActionPoints() < actionPoints) && (actionPoints <= this.getMaximumActionPoints()))
			this.actionPoints = actionPoints;
		else if (actionPoints < 0 || actionPoints == 0 || (getMinimumActionPoints() >= actionPoints)){
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
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	
	/**
	 * Set the hit points that this worm has.
	 * 
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
	private void setHitPoints(int hitPoints) {
		if ((0 <= hitPoints) && (hitPoints <= this.getMaximumHitPoints()))
			this.hitPoints = hitPoints;
		else if (hitPoints < 0)
			this.hitPoints = 0;
		else if (hitPoints > this.getMaximumHitPoints())
			this.hitPoints = this.getMaximumHitPoints();
	}
	
	
	/**
	 * Returns the maximum number of hit points this worm can have.
	 */
	public int getMaximumHitPoints() {
		return (int) this.getMass();
	}
	
	
	public boolean isAlive() {
		return (this.getHitPoints() > 0);
	}
	
	
	
	
	/**
	 * Returns the current name of this worm.
	 */
	@Basic
	@Raw
	public String getName(){
		return this.name;
	}
	
	
	/**
	 * Set the name of this worm to the given name.
	 * 
	 * 
	 * @param 	assignedName
	 * 			New name of this worm.
	 * 
	 * @post	The new name of this worm will be equal to assignedName,
	 * 			if assignedName is a legal name.
	 * 			| new.getName() == assignedName
	 * @throws 	ModelException
	 * 			The assignedName is not a legal name for a worm.
	 * 			| ! isValidName(assignedName)
	 */
	@Raw
	public void setName(String assignedName) throws ModelException {
		if (! isValidName(assignedName))
			throw new ModelException("Illegal assigned name!");
		this.name = assignedName;
	}
	
	
	/**
	 * Checks if the given name is a legal name.
	 * 
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
	 * */
	@Raw
	public static boolean isValidName(String name){
		List<String> validCharacters = Arrays.asList(" ", "\'", "\"");
		if (name.length() < 2)
			return false;
		if (! Character.isUpperCase(name.charAt(0)))
			return false;
		for (char element: name.toCharArray()){
			if ( !((Character.isLetter(element)) || (validCharacters.contains(String.valueOf(element)))) )
				return false;
		}
		return true;
	}
	
	
	
	
	public Team getTeam() {
		return this.team;
	}
	
	
	public void joinTeam(Team team) throws ModelException {
		if (! isValidTeam(team))
			throw new ModelException("Invalid team assignment!");
		if (this.getWorld().getStatus())
			throw new ModelException("Game has already started, cannot assign to team!");
		this.team = team;
	}
	
	
	public static boolean isValidTeam(Team team) {
		return (team != null);
	}
	
	
	public boolean hasProperTeam() {
		return this.getTeam().getWorms().contains(this);
	}




	/**
	 * Changes the direction of this worm by adding given angle and using the appropriate number of action points.
	 * 
	 * 
	 * @param 	angle
	 * 			The angle to be added to the direction.
	 * 
	 * @pre		New direction should be an existing angle and this worm needs enough
	 * 			action points to turn.
	 * 			| this.canTurn(angle)
	 * @post	The given angle is added to this worm's direction.
	 * 			| new.getDirection == changeAngleModulo2PI(this.getDirection() + angle)
	 * @post	The action points needed to execute the turn are subtracted from
	 * 			this worm's action points.
	 * 			| new.getActionPoints() == this.getActionPoints() - (int) usedActionPointsTurn(angle)
	 * 
	 */
	public void turn(double angle){
		assert this.canTurn(angle);
		this.setDirection(this.getDirection()+angle);
		int usedActionPoints = (int) usedActionPointsTurn(angle);
		this.setActionPoints(this.getActionPoints() - usedActionPoints);
	}

	
	/**
	 * Checks if this worm can turn the given angle.
	 * 
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
	public boolean canTurn(double angle){
		double usedActionPoints = usedActionPointsTurn(angle);
		return (usedActionPoints <= this.getActionPoints()) && (isValidDirection(this.getDirection()+angle)) && (usedActionPoints <= Integer.MAX_VALUE);
	}

	
	/**
	 * Calculates the action points needed for a worm to execute a turn over the given angle.
	 *
	 *
	 * @param 	angle
	 * 			The angle the worm should turn.
	 * 
	 * @return	Returns the action points needed to execute the turn.
	 * 			| result ==  ((60*Math.abs(changeAngleModulo2PI(angle))/(2*Math.PI)))
	 */
	@Raw
	public static double usedActionPointsTurn(double angle) {
		return Math.min((60*Math.abs(changeAngleModulo2PI(angle))/(2*Math.PI)), (60*Math.abs(changeAngleModulo2PI(angle)-2*Math.PI)/(2*Math.PI)));
	}
	
	
	
	
	/**
	 * Move this worm a number of steps in his current direction.
	 *
	 * 
	 * @param 	steps
	 * 			The number of steps to move this worm.
	 * 
	 * @post	This worm moved the given number of steps in the current direction.
	 * 			| new.getCoordinateX() == this.getCoordinateX() + this.getRadius()*steps*Math.cos(this.getDirection()))
	 * 			| new.getCoordinateY() == this.getCoordinateY() + this.getRadius()*steps*Math.sin(this.getDirection()))
	 * @post	The number of action points of this worm decreases by 1 for 1 horizontal step and
	 * 			by 4 for 1 vertical step.
	 * 			| new.getActionPoints() == this.getActionPoints() - usedActionPointsMove(steps,this.getDirection())
	 * @throws 	ModelException
	 * 			If this worm cannot move given number of steps.
	 *			|!this.canMove(steps)
	 */
	public void move(int steps) throws ModelException {
		if(! this.canMove(steps))
			throw new ModelException("Illegal number of steps!");
		
		double x = this.getCoordinateX();
		double y = this.getCoordinateY();
		double theta = this.getDirection();
		int currentActionPoints = this.getActionPoints();
		int usedActionPoints = (int) usedActionPointsMove(steps, theta);
		
		this.setCoordinateX(x + 0.5*this.getRadius()*steps*Math.cos(theta));
		this.setCoordinateY(y + 0.5*this.getRadius()*steps*Math.sin(theta));
		this.setActionPoints(currentActionPoints - usedActionPoints);
	}
	
	
	/**
	 * Checks if a worm can move given number of steps.
	 * 
	 * 
	 * @param 	NbSteps
	 * 			The steps to be checked.
	 *
	 * @return	Returns whether a worm has enough action points to move the given number of steps
	 * 			and whether the given number of steps is positive.
	 * 			| result == ((this.getActionPoints() >= usedActionPointsMove(NbSteps, getDirection())) && 
	 * 			|			(NbSteps >0) &&
	 * 			| 			(usedActionPoints <= Integer.MAX_VALUE))
	 */
	public boolean canMove(int NbSteps){
		double theta = this.getDirection();
		int currentActionPoints = this.getActionPoints();
		double usedActionPoints = usedActionPointsMove(NbSteps, theta);
		return ((currentActionPoints >= usedActionPoints) && (NbSteps >0) && (usedActionPoints <= Integer.MAX_VALUE));
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
	public static double usedActionPointsMove(int NbSteps, double theta) {
		double usedActionPoints = NbSteps*( (Math.abs(Math.cos(theta)) + 4*Math.abs(Math.sin(theta))));
		return usedActionPoints;
	}
	
	
	
	
	/**
	 * Changes the position of this worm as the result of a jump in the current direction
	 * of this worm consuming all action points. The direction does not change during the jump.
	 * If this worm is facing downwards, it will not move, but jumping consumes all the action points.
	 *
	 * 
	 * @post	All the action points have been consumed.
	 * 			| new.getActionPoints() == 0;
	 * @post	The new position of this worm is the result of a jump
	 * 			from the previous position of this worm.
	 * 			| new.getCoordinateX() == this.getCoordinateX() + this.getJumpDistance()
	 * @throws	ModelException
	 *			Throws exception when this worm is facing downwards and the jump is thus impossible
	 *			or when this worm has no action points left.
	 *			| (! this.canJump)
	 */
	@Override
	public void jump() throws ModelException{
		if (! this.canJump()) {
			this.setActionPoints(0);
			throw new ModelException("Cannot jump!");
		}
		this.setCoordinateX(this.getCoordinateX()+this.getJumpDistance());
		this.setActionPoints(0);
	}

	
	/**
	 * Returns the initial velocity of the potential jump of this worm.
	 * 
	 * 
	 * @return	The initial velocity of the jump.
	 * 			| F = 5*this.getActionPoints()+this.getMass()*getGravity()
	 *			| result == F*0.5/this.getMass()
	 */
	@Override
	public double getJumpVelocity(){
		double F = 5*this.getActionPoints()+this.getMass()*getGravity();
		return F*0.5/this.getMass();
	}
	
	
	/**
	 * Checks whether this worm can jump in his current situation.
	 * 
	 * 
	 * @return	Returns true if this worm still has some action points and 
	 * 			if he is facing upwards (direction between 0 and Pi radial).
	 * 			| result == ((this.getActionPoints() > 0) &&
	 * 			|			 (this.getDirection() > Math.PI && this.getDirection() < Math.PI*2))
	 */
	@Override
	public boolean canJump() {
		if (this.getActionPoints() == 0)
			return false;
		if (this.getDirection() > Math.PI && this.getDirection() < Math.PI*2)
			return false;
		for(double time = 0; time <= this.getJumpTime(); time = time + this.getJumpTime()/1000.0){
			double[] position = this.getJumpStep(time);
			if (! (this.getWorld()).isPassableArea(position[0], position[1], this.getRadius()))
				return false;
		}
		return true;
	}
	
	
	

	private int currentWeaponNumber = 0;
	
	public int getCurrentWeaponNumber() {
		return this.currentWeaponNumber;
	}
	
	private void setCurrentWeaponNumber(int number) {
		this.currentWeaponNumber = number;
	}
	
	public static int getNumberOfProjectiles() {
		return 2;
	}
	
	
	/**
	 * Returns the projectile of this worm.
	 */
	public Projectile getProjectile(){
		return this.projectile;
	}
	
	
	private void setProjectile() {
		if (this.getCurrentWeaponNumber() == 0)
			this.projectile = new Rifle(this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection()),
					this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection()), 
					true, this.getWorld(), this.getDirection());
		if (this.getCurrentWeaponNumber() == 1)
			this.projectile = new Bazooka(this.getCoordinateX() + this.getRadius()*Math.cos(this.getDirection()),
					this.getCoordinateY() + this.getRadius()*Math.sin(this.getDirection()), 
					true, this.getWorld(), this.getDirection(), this.getPropulsionYield());	
	}
	
	
	public int getPropulsionYield() {
		// TODO
		return 0;
	}
	
	
	public void selectWeapon() {
		this.projectile.terminate();
		this.setCurrentWeaponNumber((this.getCurrentWeaponNumber()+1) % getNumberOfProjectiles());
		this.setProjectile();
	}
	
	
	public void fall() {
		if (this.canFall()) {
			double startY = this.getCoordinateY();
			double finalY = 0;
			for(double Y = startY; (! this.getWorld().isAdjacent(this.getCoordinateX(), Y, this.getRadius()));
					Y = Y - (this.getWorld().getHeight()/this.getWorld().getDimensionInPixels(false))) {
					finalY = Y;
			}
			this.setCoordinateY(finalY);
			this.setHitPoints(this.getHitPoints() - (int) ((startY - finalY)*3.0));
		}
	}
	
	public boolean canFall() {
		return this.getWorld().isAdjacent(this.getCoordinateX(), this.getCoordinateY(), this.getRadius());
	}
}
