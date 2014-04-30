package main;

import java.awt.Graphics;
import java.awt.Point;


public class RocketTower extends Tower {
	private int tick;

	private class Rocket extends Bullet {
		Rocket(double speed, int damage,Point start, Tower t, Blob target) {
			super(speed, 0, damage, false, 0, start, t);
		}

		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			
		}

	}

	RocketTower(Player p, int ti) {
		super(p, ti);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			getBullets().add(new Rocket(getSpeed(), getDamage(), getPoint(),this,closest));
		}

	}

	@Override
	public int getRange() {
		return 125;
	}

	public int getDamage() {
		return 40;
	}

	@Override
	public void tick() {
		tick++;
		if (tick % 15 == 0) {
			shoot();
		}
		for (int i = 0; i < getBullets().size(); i++) {
			getBullets().get(i).tick();
		}

	}

	public double getSpeed() {
		return 15;
	}

}
