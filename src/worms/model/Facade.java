package worms.model;

import java.util.Collection;
import java.util.Random;

public class Facade implements IFacade {

	@Override
	public void addEmptyTeam(World world, String newName) {
		world.addTeam(newName);
	}

	@Override
	public void addNewFood(World world) {
		world.addFood();
	}

	@Override
	public void addNewWorm(World world) {
		world.addWorm();
	}

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public Food createFood(World world, double x, double y) {
		return new Food(x,y,true,world);
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		 return new World(width,height,passableMap,random);
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name) {
		return new Worm(x, y, direction, radius, name, true, world);
	}

	@Override
	public void fall(Worm worm) {
		worm.fall();
		
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.getActiveWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return world.getAllFood();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		return projectile.getJumpStep(t);
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		//TODO Something with timestep
		return projectile.getJumpTime();
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaximumActionPoints();
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaximumHitPoints();
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return getMinimalRadius(worm);
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Food food) {
		return food.getRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		return worm.getProjectile().getName();
	}

	@Override
	public String getTeamName(Worm worm) {
		try {
			return worm.getTeam().getName();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	@Override
	public String getWinner(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getAllWorms();
	}

	@Override
	public double getX(Food food) {
		return food.getCoordinateX();
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getCoordinateX();
	}

	@Override
	public double getX(Worm worm) {
		return worm.getCoordinateX();
	}

	@Override
	public double getY(Food food) {
		return food.getCoordinateY();
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getCoordinateY();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getCoordinateY();
	}

	@Override
	public boolean isActive(Food food) {
		return food.getStatus();
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return false;
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius){		
		return world.isPassableArea(x, y, radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.getStatus();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		
		return !world.isPassableArea(x, y, radius);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		//TODO Something with timestep
		projectile.jump();
		
	}

	@Override
	public void jump(Worm worm, double timeStep) {
		//TODO Something with timestep
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Worm worm) {
		worm.move();
	}

	@Override
	public void rename(Worm worm, String newName) {
		worm.setName(newName);
		
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectWeapon();
		
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		worm.setRadius(newRadius);
	}

	@Override
	public void shoot(Worm worm, int yield) {
		worm.shoot(yield);
		
	}

	@Override
	public void startGame(World world) {
		world.startGame();
	}

	@Override
	public void startNextTurn(World world) {
		world.nextTurn();
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}
	

}	

