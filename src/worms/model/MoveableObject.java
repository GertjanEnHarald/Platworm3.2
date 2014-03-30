package worms.model;

public abstract class MoveableObject extends GameObject {

	public MoveableObject(double coordinateX, double coordinateY, boolean isActive, double radius) {
		super(coordinateX, coordinateY, isActive, radius);
	}

	public abstract double getMass();
	
}
