package worms.model;

import java.util.ArrayList;
import java.util.List;
import be.kuleuven.cs.som.annotate.*;

public class Team implements Cloneable {

	private final String name;
	private final List<Worm> worms = new ArrayList<Worm>();
	
	public Team(String name) throws ModelException {
		if (! isValidName(name))
			throw new ModelException("Illegal name assignment!");
		this.name = name;
	}
	
	
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
	
	@Basic
	@Immutable
	@Raw
	public String getName() {
		return this.name;
	}
	
	
	public List<Worm> getWorms() {
		return this.worms;
	}
	
	
	public List<Worm> getLivingWorms() {
		List<Worm> wormsAlive = new ArrayList<Worm>();
		for(Worm worm: this.getWorms()){
			if (worm.isAlive())
				wormsAlive.add(worm);
		}
		return wormsAlive;
	}
	
	
	public void addWorm(@Raw Worm worm) throws ModelException {
		if (! canHaveAsWorm(worm))
			throw new ModelException("Cannot have this worm in the team!");
		this.addWorm(worm);
	}


	public boolean canHaveAsWorm(@Raw Worm worm) {
		return (worm.getTeam() == this);
	}
	
	
	public boolean hasProperWorms() {
		for(Worm worm: this.getWorms()) {
			if (! (worm.getTeam() == this))
				return false;
		}
		return true;
	}
	
	public void removeFromTeam(Worm worm){
		this.worms.remove(worm);
	}
	
	protected Team clone() throws CloneNotSupportedException {
		return (Team) super.clone();
	}
}
