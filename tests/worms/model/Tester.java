package worms.model;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Worm testWorm = new Worm(-5,0,Math.PI/2,1,"James O'Hare");
		System.out.println(testWorm.getJumpTime());
		System.out.println(10/Math.cos(Math.PI/2));
	}

}
