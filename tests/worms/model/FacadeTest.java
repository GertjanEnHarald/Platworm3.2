/**
 * Test the class Facade.java
 */

package worms.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import worms.model.Facade;
import worms.model.ModelException;
import worms.model.Worm;
import worms.util.Util;

public class FacadeTest {

	private static final double EPS = Util.DEFAULT_EPSILON;

	private IFacade facade;

	@Before
	public void setup() {
		facade = new Facade();
	}


	@Test
	public void testMaximumActionPoints() {
		Worm worm = facade.createWorm(0, 0, 0, 1, "Test");
		assertEquals(4448, facade.getMaxActionPoints(worm));
	}

	@Test
	public void testMoveHorizontal() {
		Worm worm = facade.createWorm(0, 0, 0, 1, "Test");
		facade.move(worm, 5);
		assertEquals(5, facade.getX(worm), EPS);
		assertEquals(0, facade.getY(worm), EPS);
	}

	@Test
	public void testMoveVertical() {
		Worm worm = facade.createWorm(0, 0, Math.PI / 2,  1, "Test");
		facade.move(worm, 5);
		assertEquals(0, facade.getX(worm), EPS);
		assertEquals(5, facade.getY(worm), EPS);
	}

	@Test(expected = ModelException.class)
	public void testJumpException() {
		Worm worm = facade.createWorm(0, 0, 3 * Math.PI / 2, 1, "Test");
		facade.jump(worm);
	}
	
	@Test
	public void testJumpLegalCase() {
		Worm worm = facade.createWorm(0, 0, 0.8*Math.PI / 2, 1, "Test");
		double jumpDistance = worm.getJumpDistance();
		assertEquals(facade.getMaxActionPoints(worm),facade.getActionPoints(worm),EPS);
		facade.jump(worm);
		assertEquals(0,worm.getActionPoints());
		assertEquals(jumpDistance,worm.getCoordinateX(),EPS);
		assertEquals(0,worm.getCoordinateY(),EPS);
	}

	
	
	
	
	
	
	
	
	
	@Test
	public void testConstructorLegalCase1() throws ModelException {
		Worm testWorm = facade.createWorm(-5,0,0,1,"James O'Hare");
		assertEquals(-5,facade.getX(testWorm),EPS);
		assertEquals(0,facade.getY(testWorm),EPS);
		assertEquals(0,facade.getOrientation(testWorm),EPS);
		assertEquals(1,facade.getRadius(testWorm),EPS);
		assertEquals("James O'Hare",facade.getName(testWorm));
	}
	
	@Test
	public void testConstructorLegalCase2() throws ModelException {
		Worm testWorm = facade.createWorm(Double.MAX_VALUE,-4,-3*Math.PI,1.9,"Test \' \" ");
		assertEquals(Double.MAX_VALUE,facade.getX(testWorm),EPS);
		assertEquals(-4,facade.getY(testWorm),EPS);
		assertEquals(Math.PI,facade.getOrientation(testWorm),EPS);
		assertEquals(1.9,facade.getRadius(testWorm),EPS);
		assertEquals("Test \' \" ",facade.getName(testWorm));
	}
	
	@Test
	public void testConstructorLegalCase3() throws ModelException {
		Worm testWorm = facade.createWorm(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,3*Math.PI,1,"James'OhAre \" ");
		assertEquals(Double.NEGATIVE_INFINITY,facade.getX(testWorm),EPS);
		assertEquals(Double.POSITIVE_INFINITY,facade.getY(testWorm),EPS);
		assertEquals(Math.PI,facade.getOrientation(testWorm),EPS);
		assertEquals(1,facade.getRadius(testWorm),EPS);
		assertEquals("James'OhAre \" ",facade.getName(testWorm));
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinatesX() throws ModelException {
		facade.createWorm(Double.NaN,0,0,1,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinatesY() throws ModelException {
		facade.createWorm(0,Double.NaN,0,1,"Bob");
	}	
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusSmallerThanMinimum() throws ModelException{
		facade.createWorm(0,0,0,0.1,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusIsNan() throws ModelException{
		facade.createWorm(0,0,0,Double.NaN,"Bob");
	}
	
	@Test
	public void testConstructorLegalCaseMass() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1.0,"Bob");
		assertEquals(1062*4/3*Math.PI, facade.getMass(testWorm),EPS);		
	}
	
	@Test
	public void testConstructorLegalCaseActionPoints() throws ModelException{
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1.0,"Bob");
		assertEquals(facade.getMaxActionPoints(testWorm),facade.getActionPoints(testWorm),EPS);
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameSingleLetter() throws ModelException{
		facade.createWorm(10.0,-5.0,Math.PI,1.0,"J");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameNoStartingCapital() throws ModelException{
		facade.createWorm(10.0,-5.0,Math.PI,1.0,"james");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameIllegalSymbol() throws ModelException{
		facade.createWorm(10.0,-5.0,Math.PI,1.0,"J*");
	}
	
	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameNumber() throws ModelException{
		facade.createWorm(10.0,-5.0,Math.PI,1.0,"James 5");
	}
	
	@Test
	public void testChangeRadiusLegalCaseMaxMass() {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1.0,"James");
		facade.setRadius(testWorm, 78.44);
	}
	
	@Test(expected = ModelException.class)
	public void testChangeRadiusIllegalCaseMassOverflow() {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1.0,"James");
		facade.setRadius(testWorm, 1.0+78.44);
	}
	
	@Test(expected = ModelException.class)
	public void testChangeRadiusIllegalCaseNullPointer() {
		Worm testWorm = null;
		facade.setRadius(testWorm, 2);
	}
	
	@Test
	public void testTurnLegalCase1(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		facade.turn(testWorm,Math.PI/4);
		assertEquals(Math.PI+Math.PI/4, facade.getOrientation(testWorm),EPS);
		int actionPoints = (int) (facade.getMaxActionPoints(testWorm)- ((int) ((60*Math.PI/4)/(2*Math.PI))));
		assertEquals(actionPoints ,facade.getActionPoints(testWorm));
	}
	
	@Test
	public void testTurnLegalCase2(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		facade.turn(testWorm,-3*Math.PI/2);
		assertEquals(3*Math.PI/2, facade.getOrientation(testWorm),EPS);
		int actionPoints = (int) (facade.getMaxActionPoints(testWorm)- ((int) ((60*Math.PI/2)/(2*Math.PI))));
		assertEquals(actionPoints ,facade.getActionPoints(testWorm));
	}
	
	@Test(expected = ModelException.class)
	public void testTurnIllegalNullPointer(){
		Worm testWorm = null;
		facade.turn(testWorm,-3*Math.PI/2);
	}
	
	@Test
	public void testCanTurn(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		assertEquals(true, facade.canTurn(testWorm, 5));
	}
	
	@Test
	public void testCanTurnMaxValue(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		assertEquals(true, facade.canTurn(testWorm, Integer.MAX_VALUE*2*Math.PI/60.0));
	}
	
	@Test(expected = ModelException.class)
	public void testCanIllegalCaseNullPointer(){
		Worm testWorm = null;
		facade.canTurn(testWorm, 1.0+Integer.MAX_VALUE*2.0*Math.PI/60.0);
	}
	
	@Test
	public void testMoveLegalCase(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI*3.0/5.0,10,"Bob");
		facade.move(testWorm, 15);
		assertEquals(facade.getX(testWorm), 10+10*15*Math.cos(Math.PI*3/5), EPS);
		assertEquals(facade.getY(testWorm), -5+10*15*Math.sin(Math.PI*3/5), EPS);
		assertEquals(facade.getActionPoints(testWorm), facade.getMaxActionPoints(testWorm)-(int)(15*(Math.abs(Math.cos(Math.PI*3.0/5.0)) + 4.0*Math.abs(Math.sin(Math.PI*3.0/5.0)))));
	}
	
	@Test(expected = ModelException.class)
	public void testMoveIllegalCaseInvalidSteps() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI*3/5,1,"Bob");
		facade.move(testWorm, -16);
	}
	
	@Test(expected=ModelException.class)
	public void testMoveIllegalCaseCannotMove() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,0,1,"Bob");
		facade.move(testWorm, facade.getActionPoints(testWorm)+1);
	}
	
	@Test
	public void testMoveLegalCaseMaxValue(){
		Worm testWorm = facade.createWorm(10.0,-5.0,0,78.4,"Bob");
		facade.move(testWorm, (Integer.MAX_VALUE/2));
	}
	
	@Test(expected = ModelException.class)
	public void testMoveIllegalCaseLackOfActionPoints(){
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI/2,1,"Bob");
		facade.move(testWorm, Integer.MAX_VALUE);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveIllegalCaseNullPointer(){
		Worm testWorm = null;
		facade.move(testWorm, 10);
	}
	
	@Test
	public void testJumpLegalCaseDirectionRight() {
		Worm testWorm = facade.createWorm(10.0,-5.0,0,1,"Bob");
		facade.jump(testWorm);
		assertEquals(facade.getX(testWorm),10.0 + (Math.pow((0.5*(5*facade.getMaxActionPoints(testWorm)+facade.getMass(testWorm)*9.80665))/facade.getMass(testWorm), 2)*Math.sin(2*facade.getOrientation(testWorm))/9.80665),EPS);
	}
	
	@Test
	public void testJumpLegalCaseRandomDirection() {
		Worm testWorm = facade.createWorm(10.0,-5.0,(3.0/5.0)*Math.PI,1,"Bob");
		facade.jump(testWorm);
		assertEquals(facade.getX(testWorm),10.0 + (Math.pow((0.5*(5*facade.getMaxActionPoints(testWorm)+facade.getMass(testWorm)*9.80665))/facade.getMass(testWorm), 2)*Math.sin(2*facade.getOrientation(testWorm))/9.80665),EPS);
	}
	
	@Test
	public void testJumpLegalCaseDirectionLeft() {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		facade.jump(testWorm);
		assertEquals(facade.getX(testWorm),10.0 + (Math.pow((0.5*(5*facade.getMaxActionPoints(testWorm)+facade.getMass(testWorm)*9.80665))/facade.getMass(testWorm), 2)*Math.sin(2*facade.getOrientation(testWorm))/9.80665),EPS);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidActionPoints() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI,1,"Bob");
		facade.jump(testWorm);
		facade.jump(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidDirection1() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,Math.PI*(-3.0/5.0),1,"Bob");
		facade.jump(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidDirection2() throws ModelException {
		Worm testWorm = facade.createWorm(10.0,-5.0,(3.0/2.0)*Math.PI,1,"Bob");
		facade.jump(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.jump(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testCanMoveIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.canMove(testWorm,10);	
	}
	
	@Test(expected = ModelException.class)
	public void testGetJumpTimeIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getJumpTime(testWorm);
	}

	@Test(expected = ModelException.class)
	public void testGetJumpStepIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getJumpStep(testWorm,0.1);
	}

	@Test(expected = ModelException.class)
	public void testGetXIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getX(testWorm);
	}
		
	@Test(expected = ModelException.class)
	public void testGetYIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getY(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testGetOrientationIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getOrientation(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testGetRadiusIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getRadius(testWorm);
	}
		
	@Test(expected = ModelException.class)
	public void testGetMinimalRadiusNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getMinimalRadius(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testGetActionPointsIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getActionPoints(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testGetMaxActionPointsIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getMaxActionPoints(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testGetNameIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getName(testWorm);
	}
	
	@Test(expected = ModelException.class)
	public void testRenameIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.rename(testWorm,"Bob");
	}
	
	@Test(expected = ModelException.class)
	public void testGetMassIllegalCaseNullPointer() throws ModelException {
		Worm testWorm = null;
		facade.getMass(testWorm);
	}
		
}
