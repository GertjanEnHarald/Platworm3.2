/**
 * Test the class Worm.java
 */

package worms.model;

import static org.junit.Assert.assertEquals;




//import org.junit.Before;
import org.junit.Test;

import worms.util.Util;

public class Tests {
	private static final double EPS = Util.DEFAULT_EPSILON;
	
	@Test
	public void testDensity(){
		Worm testWorm = new Worm(-5,0,0,1,"James O'Hare");
		assertEquals(testWorm.getDensity(), 1062, EPS);
	}
	
	@Test
	public void testGravity(){
		assertEquals(9.80665, Worm.getGravity(), EPS);
	}

	@Test
	public void testConstructorLegalCase1() throws ModelException {
		Worm testWorm = new Worm(-5,0,0,1,"James O'Hare");
		assertEquals(-5,testWorm.getCoordinateX(),EPS);
		assertEquals(0,testWorm.getCoordinateY(),EPS);
		assertEquals(0,testWorm.getDirection(),EPS);
		assertEquals(1,testWorm.getRadius(),EPS);
		assertEquals("James O'Hare",testWorm.getName());
	}
	
	@Test
	public void testConstructorLegalCase2() throws ModelException {
		Worm testWorm = new Worm(Double.MAX_VALUE,-4,-3*Math.PI,1.9,"Test \' \" ");
		assertEquals(Double.MAX_VALUE,testWorm.getCoordinateX(),EPS);
		assertEquals(-4,testWorm.getCoordinateY(),EPS);
		assertEquals(Math.PI,testWorm.getDirection(),EPS);
		assertEquals(1.9,testWorm.getRadius(),EPS);
		assertEquals("Test \' \" ",testWorm.getName());
	}
	
	@Test
	public void testConstructorLegalCase3() throws ModelException {
		Worm testWorm = new Worm(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,3*Math.PI,20,"James'OhAre \" ");
		assertEquals(Double.NEGATIVE_INFINITY,testWorm.getCoordinateX(),EPS);
		assertEquals(Double.POSITIVE_INFINITY,testWorm.getCoordinateY(),EPS);
		assertEquals(Math.PI,testWorm.getDirection(),EPS);
		assertEquals(20,testWorm.getRadius(),EPS);
		assertEquals("James'OhAre \" ",testWorm.getName());
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinatesX() throws ModelException {
		new Worm(Double.NaN,0,0,1,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinatesY() throws ModelException {
		new Worm(0,Double.NaN,0,1,"Bob");
	}	
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusSmallerThanMinimum() throws ModelException{
		new Worm(0,0,0,0.1,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusIsNan() throws ModelException{
		new Worm(0,0,0,Double.NaN,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusIsMaxInteger() throws ModelException{
		new Worm(0,0,0,Integer.MAX_VALUE,"Bob");
	}
	
	@Test
	public void testConstructorLegalCaseMass() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1.0,"Bob");
		assertEquals(1062*4/3*Math.PI, testWorm.getMass(),EPS);		
	}
	
	@Test
	public void testConstructorLegalCaseActionPoints() throws ModelException{
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1.0,"Bob");
		assertEquals(testWorm.getMaximumActionPoints(),testWorm.getActionPoints(),EPS);
		assertEquals(4448,testWorm.getActionPoints());
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameSingleLetter() throws ModelException{
		new Worm(10.0,-5.0,Math.PI,1.0,"J");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameNoStartingCapital() throws ModelException{
		new Worm(10.0,-5.0,Math.PI,1.0,"james");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameIllegalSymbol() throws ModelException{
		new Worm(10.0,-5.0,Math.PI,1.0,"J*");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameNumber() throws ModelException{
		new Worm(10.0,-5.0,Math.PI,1.0,"James 5");
	}
	
	@Test
	public void testChangeRadiusLegalCase() {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.move(100);
		float percentageOld = (((float) testWorm.getActionPoints())/ ((float) testWorm.getMaximumActionPoints()));
		testWorm.changeRadius(50);
		float percentageNew = (((float) testWorm.getActionPoints())/ ((float) testWorm.getMaximumActionPoints()));
		assertEquals(testWorm.getRadius(), 50.0, EPS);
		assertEquals(percentageOld, percentageNew, EPS);
	}
	
	@Test(expected = ModelException.class)
	public void testChangeRadiusIllegalCase() {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.changeRadius(Integer.MAX_VALUE);
	}
	
	@Test
	public void testTurnLegalCase1(){
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.turn(Math.PI/4);
		assertEquals(5*Math.PI/4, testWorm.getDirection(),EPS);
		int actionPoints = (int) (testWorm.getMaximumActionPoints()- (Math.round((60*Math.PI/4)/(2*Math.PI))));
		assertEquals(actionPoints ,testWorm.getActionPoints());
	}
	
	@Test
	public void testTurnLegalCase2(){
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.turn(-9*Math.PI/2);
		assertEquals(Math.PI/2, testWorm.getDirection(),EPS);
		int actionPoints = (int) (testWorm.getMaximumActionPoints()- (Math.round((60*3*Math.PI/2)/(2*Math.PI))));
		assertEquals(actionPoints ,testWorm.getActionPoints());
	}
	
	@Test
	public void testMoveLegalCase1(){
		Worm testWorm = new Worm(10.0,-5.0,Math.PI*3/5,1,"Bob");
		testWorm.move(6);
		assertEquals(testWorm.getCoordinateX(), 10+6*Math.cos(Math.PI*3/5), EPS);
		assertEquals(testWorm.getCoordinateY(), -5+6*Math.sin(Math.PI*3/5), EPS);
		assertEquals(testWorm.getActionPoints(), testWorm.getMaximumActionPoints()-6* ((int) (Math.abs(Math.cos(Math.PI*3/5)) + 4*Math.abs(Math.sin(Math.PI*3/5)))));
	}
	
	@Test(expected = ModelException.class)
	public void testMoveIllegalCaseInvalidSteps() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI*3/5,1,"Bob");
		testWorm.move( -16);
	}
	
	@Test(expected=ModelException.class)
	public void testMoveIllegalCaseCannotMove() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,0,1,"Bob");
		testWorm.move( testWorm.getActionPoints()+1);
	}
	
	@Test
	public void testJumpLegalCaseDirectionRight() {
		Worm testWorm = new Worm(10.0,-5.0,0,1,"Bob");
		testWorm.jump();
		assertEquals(testWorm.getCoordinateX(),10.0 + (Math.pow((0.5*(5*testWorm.getMaximumActionPoints()+testWorm.getMass()*9.80665))/testWorm.getMass(), 2)*Math.sin(2*testWorm.getDirection())/9.80665),EPS);
		assertEquals(testWorm.getActionPoints(),0);
	}
	
	@Test
	public void testJumpLegalCaseRandomDirection() {
		Worm testWorm = new Worm(10.0,-5.0,(3.0/5.0)*Math.PI,1,"Bob");
		testWorm.jump();
		assertEquals(testWorm.getCoordinateX(),10.0 + (Math.pow((0.5*(5*testWorm.getMaximumActionPoints()+testWorm.getMass()*9.80665))/testWorm.getMass(), 2)*Math.sin(2*testWorm.getDirection())/9.80665),EPS);
		assertEquals(testWorm.getActionPoints(),0);
	}
	
	@Test
	public void testJumpLegalCaseDirectionLeft() {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.jump();
		assertEquals(testWorm.getCoordinateX(),10.0 + (Math.pow((0.5*(5*testWorm.getMaximumActionPoints()+testWorm.getMass()*9.80665))/testWorm.getMass(), 2)*Math.sin(2*testWorm.getDirection())/9.80665),EPS);
		assertEquals(testWorm.getActionPoints(),0);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidActionPoints() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI,1,"Bob");
		testWorm.jump();
		testWorm.jump();
		assertEquals(testWorm.getActionPoints(),0);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidDirection1() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,Math.PI*(-3.0/5.0),1,"Bob");
		testWorm.jump();
		assertEquals(testWorm.getActionPoints(),0);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidDirection2() throws ModelException {
		Worm testWorm = new Worm(10.0,-5.0,(3.0/2.0)*Math.PI,1,"Bob");
		testWorm.jump();
		assertEquals(testWorm.getActionPoints(),0);
	}
}


