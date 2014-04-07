package main;

public abstract class Bullet {
	private int speed;
	private double direction;
	private int damage;
	private boolean splash;
	private int splashRandge;
	Bullet(int speed, double direction,int damage, boolean splash,int splashRange){
		this.speed=speed;
		this.direction=direction;
		this.damage=damage;
		this.splash=splash;
		this.splashRandge=splashRange;
	}
}
