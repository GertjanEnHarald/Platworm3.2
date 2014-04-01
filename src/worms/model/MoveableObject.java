package worms.model;

public abstract class MoveableObject extends GameObject {

	public MoveableObject(double coordinateX, double coordinateY, boolean isActive, double radius, World world) {
		super(coordinateX, coordinateY, isActive, radius,world);
	}

	public abstract double getMass();
	
	public abstract void jump();
	
}
