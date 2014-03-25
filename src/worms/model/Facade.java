package worms.model;

public class Facade implements IFacade {
	
	
	public Worm createWorm(double x, double y, double direction, double radius, String name){
		return new Worm(x, y,direction,radius,name);
	}
	
	public boolean canMove(Worm worm, int nbSteps){
		try {
			return worm.canMove(nbSteps);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public void move(Worm worm, int nbSteps){
		try {
			worm.move(nbSteps);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public boolean canTurn(Worm worm, double angle){
		try {
			return worm.canTurn(angle);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
		
	public void turn(Worm worm, double angle){
		try {
			worm.turn(angle);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public void jump(Worm worm){
		try {
			worm.jump();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getJumpTime(Worm worm){
		try {
			return worm.getJumpTime();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double[] getJumpStep(Worm worm, double t){
		try{
			return worm.getJumpStep(t);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getX(Worm worm){
		try {
			return worm.getCoordinateX();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getY(Worm worm){
		try {
			return worm.getCoordinateY();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getOrientation(Worm worm){
		try {
			return worm.getDirection();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getRadius(Worm worm){
		try {
			return worm.getRadius();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public void setRadius(Worm worm, double newRadius){
		try {
			worm.changeRadius(newRadius);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getMinimalRadius(Worm worm){
		try {	
			return worm.getMinimumRadius();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public int getActionPoints(Worm worm){
		try {
			return worm.getActionPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public int getMaxActionPoints(Worm worm){
		try {
			return worm.getMaximumActionPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	
	public String getName(Worm worm){
		try {
			return worm.getName();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public void rename(Worm worm, String newName){
		try {
			worm.setName(newName);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
	
	public double getMass(Worm worm){
		try {
			return worm.getMass();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid argument!");
		}
	}
}	

