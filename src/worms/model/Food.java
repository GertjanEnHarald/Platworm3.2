package worms.model;

public class Food extends GameObject {

	public Food(double coordinateX, double coordinateY, boolean isActive, double radius) {
		super(coordinateX, coordinateY, isActive, 0.20);
	}

	@Override
	public boolean isValidRadius(double radius) {
		return true;
	}

}
