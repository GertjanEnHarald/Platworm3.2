package worms.model;

import java.util.Collection;
import java.util.Random;

public class Facade implements IFacade {

	@Override
	public void addEmptyTeam(World world, String newName) {
		try {
			world.addTeam(newName);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void addNewFood(World world) {
		try{
			world.addFood();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void addNewWorm(World world) {
		try {
			world.addWorm();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean canFall(Worm worm) {
		try {
			return worm.canFall();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean canMove(Worm worm) {
		try {
			return worm.canMove();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		try {
			return worm.canTurn(angle);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public Food createFood(World world, double x, double y) {
		try {
			return new Food(x,y,true,world);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
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
		try {
			worm.fall();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public int getActionPoints(Worm worm) {
		try {
			return worm.getActionPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		try {
			return world.getActiveProjectile();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public Worm getCurrentWorm(World world) {
		try {
			return world.getActiveWorm();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public Collection<Food> getFood(World world) {
		try {
			return world.getAllFood();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public int getHitPoints(Worm worm) {
		try {
			return worm.getHitPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		try {
			return projectile.getJumpStep(t);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		try {
			return worm.getJumpStep(t);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		try {
			return projectile.getJumpRealTimeInAir(timeStep);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		try {
			return worm.getJumpRealTimeInAir(timeStep);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getMass(Worm worm) {
		try {
			return worm.getMass();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		try {
			return worm.getMaximumActionPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		try {
			return worm.getMaximumHitPoints();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		try {
			return getMinimalRadius(worm);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public String getName(Worm worm) {
		try {
			return worm.getName();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getOrientation(Worm worm) {
		try {
			return worm.getDirection();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getRadius(Food food) {
		try {
			return food.getRadius();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getRadius(Projectile projectile) {
		try {
			return projectile.getRadius();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getRadius(Worm worm) {
		try {
			return worm.getRadius();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		try {
			return worm.getProjectile().getName();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
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
		try {
			return world.getWinner();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		try {
			return world.getAllWorms();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getX(Food food) {
		try {
			return food.getCoordinateX();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getX(Projectile projectile) {
		try {
			return projectile.getCoordinateX();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getX(Worm worm) {
		try {
			return worm.getCoordinateX();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getY(Food food) {
		try {
			return food.getCoordinateY();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getY(Projectile projectile) {
		try {
			return projectile.getCoordinateY();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public double getY(Worm worm) {
		try {
			return worm.getCoordinateY();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isActive(Food food) {
		try {
			return food.getStatus();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isActive(Projectile projectile) {
		try {
			return projectile.isTerminated();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius){		
		try {
			return world.isAdjacent(x, y, radius);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isAlive(Worm worm) {
		try {
			return worm.getStatus();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isGameFinished(World world) {
		try {
			return world.isGameFinished();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		try {
			return !world.isPassableArea(x, y, radius);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		try {
			projectile.jump(timeStep);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void jump(Worm worm, double timeStep) {
		try {
			worm.jump(timeStep);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void move(Worm worm) {
		try {
			worm.move();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void rename(Worm worm, String newName) {
		try {
			worm.setName(newName);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		try {
			worm.selectWeapon();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
		
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		try {
			worm.setRadius(newRadius);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void shoot(Worm worm, int yield) {
		try {
			worm.shoot(yield);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
		
	}

	@Override
	public void startGame(World world) {
		try {
			world.startGame();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void startNextTurn(World world) {
		try {
			world.nextTurn();
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}

	@Override
	public void turn(Worm worm, double angle) {
		try {
			worm.turn(angle);
		} catch (NullPointerException exc) {
			throw new ModelException("Null is invalid object!");
		}
	}
	

}	

