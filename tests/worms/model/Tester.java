package worms.model;

public class Tester {

	public static void main(String[] args) {
		//for (int number = 1; number <= 12; number++) {
		//    System.out.println(number + " squared is " + (number * number));
		//}
		Worm worm = new Worm(0, 0, 0, 0, null, false, null);
		worm.setCoordinateX(1);
		System.out.println(worm.getCoordinateX());
	
	}
}
