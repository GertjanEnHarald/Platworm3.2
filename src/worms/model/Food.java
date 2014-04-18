package worms.model;

public class Food extends GameObject {

	public Food(double coordinateX, double coordinateY, boolean isActive, World world) {
		super(coordinateX, coordinateY, isActive, 0.20,world);
	}

	@Override
	public boolean isValidRadius(double radius) {
		return true;
	}

	@Override
	protected Food clone() throws CloneNotSupportedException {
		return (Food) super.clone();
	}
	
}
