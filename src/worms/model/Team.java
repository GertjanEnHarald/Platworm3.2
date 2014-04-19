package worms.model;

import java.util.ArrayList;
import java.util.List;
import be.kuleuven.cs.som.annotate.*;

public class Team implements Cloneable {

	private final String name;
	private final List<Worm> worms = new ArrayList<Worm>();
	
	
	/**
	 * Constructor to make a new team.
	 * 
	 * @param 	name
	 * 			The name of the new team.
	 * 
	 * @throws 	ModelException
	 * 			The exception is thrown if the given name is invalid.
	 * 			| ! isValidName(name)
	 */
	public Team(String name) throws ModelException {
		if (! isValidName(name))
			throw new ModelException("Illegal name assignment!");
		this.name = name;
	}
	
	
	
	
	/**
	 * Checks if the given name is a valid name.
	 * 
	 * @param 	name
	 * 			The name that needs to be checked.
	 * 
	 * @return	Returns whether the given name is a valid name.
	 * 			A name is valid if all of the 3 following expressions are true:
	 * 				- the name must have at least 2 characters
	 * 				- the name must start with a capital letter
	 * 				- each character in the name must be a letter.
	 * 			| result == (name.length() >= 2) &&
	 * 			| 			(Character.isUpperCase(name.charAt(0))) &&
	 * 			|			(for each char in name:
	 * 							Character.isLetter(char))
	 */
	public static boolean isValidName(String name) {
		if (name.length() < 2)
			return false;
		if (! Character.isUpperCase(name.charAt(0)))
			return false;
		for (char element: name.toCharArray()){
			if ( !(Character.isLetter(element)) )
				return false;
		}
		return true;
	}
	
	
	/**
	 * Returns the name of this team.
	 */
	@Basic
	@Immutable
	@Raw
	public String getName() {
		return this.name;
	}
	
	
	
	
	/**
	 * Returns all the worms that are in this team.
	 */
	public List<Worm> getWorms() {
		return this.worms;
	}
	
	
	/**
	 * Returns all the worms in this team that are alive.
	 */
	public List<Worm> getLivingWorms() {
		List<Worm> wormsAlive = new ArrayList<Worm>();
		for(Worm worm: this.getWorms()){
			if (worm.isAlive())
				wormsAlive.add(worm);
		}
		return wormsAlive;
	}
	
	
	/**
	 * Add a given worm to this team.
	 * 
	 * @param 	worm
	 * 			The worm that should be added to this team.
	 * 
	 * @post	The worm is in the team.
	 * 			| new.getAllWorms().contains(worm)
	 * 
	 * @throws 	ModelException
	 *			This exception is thrown if this team cannot add the given worm. 
	 *			| ! canHaveAsWorm(worm)
	 */
	public void addWorm(@Raw Worm worm) throws ModelException {
		if (! canHaveAsWorm(worm))
			throw new ModelException("Cannot have this worm in the team!");
		this.worms.add(worm);
	}


	/**
	 * Checks if a given worm can join this team.
	 * 
	 * @param 	worm
	 * 			The worm that needs to be checked.
	 * 
	 * @return	Returns if the worm has a reference to this team.
	 * 			| result == (worm.getTeam() == this)
	 */
	public boolean canHaveAsWorm(@Raw Worm worm) {
		return (worm.getTeam() == this);
	}
	
	
	/**
	 * Returns if this team has proper worms.
	 * 
	 * @return	Returns if all the worms in this team have a reference to this team.
	 * 			| if (for each worm in this.getAllWorms():
	 * 			|		worm.getTeam() == this)
	 * 			| then result == true
	 * 			| else result == false
	 */
	public boolean hasProperWorms() {
		for(Worm worm: this.getWorms()) {
			if (! (worm.getTeam() == this))
				return false;
		}
		return true;
	}
	
	
	/**
	 * Removes the given worm from this team.
	 * 
	 * @param 	worm
	 * 			The worm that needs to be removed from this team.
	 * 
	 * @post	The given worm is no longer part of this team.
	 * 			| ! this.getAllWorms().contains(worm)
	 */
	public void removeFromTeam(Worm worm){
		this.worms.remove(worm);
	}
	
	
	
	
	protected Team clone() throws CloneNotSupportedException {
		return (Team) super.clone();
	}
}
