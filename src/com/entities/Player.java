package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.graphics.Spritesheet;
import com.graphics.UI;
import com.main.Game;
import com.world.Camera;
import com.world.Map;
import com.world.SandTile;
import com.world.Tile;
import com.world.WallTile;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	
	public static double speed = 3;
	public static double normalSpeed = 3;
	
	public static int power = 1, maxPower = 5;
	
	public boolean mouseShoot = false;
	
	public static boolean transform = false;
	
	//private static Tile[] tiles;
	
	public static int mx, my;
	
	public double maxhp = 100, hp = 100;
	public double maxsp = 100, sp = 20;
	public double maxmp = 200, mp = 50;
	
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	private BufferedImage[] rightPlayer, leftPlayer, upPlayer, downPlayer;
	
	public Rectangle playerRect;
	
	public boolean moving = false;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		
		for (int i = 0; i < 4; i++) {                                                       
			rightPlayer [i] = Game.spritesheet.getSprite((i*64) + 256, 64, 64, 64);               
		}                                                                                   
		for (int i = 0; i < 4; i++) {                                                       
			leftPlayer [i] = Game.spritesheet.getSprite((i*64) + 256, 0, 64, 64);          
		}                                                                                   
		for (int i = 0; i < 4; i++) {                                                       
			upPlayer [i] = Game.spritesheet.getSprite((i*64), 64, 64, 64);           
		}
		for (int i = 0; i < 4; i++) {
			downPlayer [i] = Game.spritesheet.getSprite((i*64), 0, 64, 64);
		}
		
		this.setMask(22, 17, 30, 40);
	}
	
	
	public void tick() {
		
		//System.out.println(transform);
		/*int x1 = (int) ((x+speed) / Map.TILE_SIZE);
		int y1 = (int) (y / Map.TILE_SIZE);
		
		int x2 = (int) ((x-speed) + Map.TILE_SIZE)/ Map.TILE_SIZE;
		int y2 = (int) (y / Map.TILE_SIZE);
		
		int x3 = (int) (x / Map.TILE_SIZE);
		int y3 = (int) ((y+speed) + Map.TILE_SIZE)/ Map.TILE_SIZE;
		
		int x4 = (int) (x + Map.TILE_SIZE)/ Map.TILE_SIZE;
		int y4 = (int) ((y-speed) + Map.TILE_SIZE)/ Map.TILE_SIZE;
		
		System.out.println(x + (y / Map.WIDTH));
		System.out.println("x1 "+x1);
		System.out.println("y1 "+y1);
		System.out.println("x2 "+x2);
		System.out.println("y2 "+y2);
		System.out.println("x3 "+x3);
		System.out.println("y3 "+y3);
		System.out.println("x4 "+x4);
		System.out.println("y4 "+y4);
		
		int xy1 = x1 + (y1*Map.WIDTH);
		int	xy2 = x2 + (y2*Map.WIDTH);
		int	xy3	= x3 + (y3*Map.WIDTH);
		int xy4	= x4 + (y4*Map.WIDTH);
		
		System.out.println("xy1 "+xy1);
		System.out.println("xy2 "+xy2);
		System.out.println("xy3 "+xy3);
		System.out.println("xy4 "+xy4);
		
		Tile[] tiles = new Tile [Map.WIDTH * Map.HEIGHT];
		
		boolean wall1 = tiles [x1 + (y1*Map.WIDTH)] instanceof WallTile;
		boolean	wall2 = tiles [x2 + (y2*Map.WIDTH)] instanceof WallTile;
		boolean	wall3 = tiles [x3 + (y3*Map.WIDTH)] instanceof WallTile;
		boolean	wall4 = tiles [x4 + (y4*Map.WIDTH)] instanceof WallTile;
		
		System.out.println(Map.isFree((int)(x+speed), y));
		System.out.println(Map.isFree((int)(x-speed), y));
		System.out.println(Map.isFree(x, (int)(y+speed)));
		System.out.println(Map.isFree(x, (int)(y-speed)));*/
		
		shoot();
		SandTile.slow(this.getX(), this.getY());
		LifeFlame.heal();
		if (hp > maxhp) {
			hp = maxhp;
		}
		moving = false;
		if (right && Map.isFree((int)(x+speed), this.getY())) {
			moving = true;
			dir = right_dir;
			x+=speed;
			
			
		}else if (left && Map.isFree((int)(x-speed), this.getY())) {
			moving = true;
			dir = left_dir;
			x-=speed;
		
		}if (up && Map.isFree(this.getX(), (int)(y-speed))) {
			dir = up_dir;
			moving = true;
			y-=speed;
		
		}else if (down && Map.isFree(this.getX(), (int)(y+speed))) {
			dir = down_dir;
			moving = true;
			y+=speed;
		}

		if (moving) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		
		isColiddingLifeFLame();
		isColiddingFirePower();
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, Map.WIDTH*64 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, Map.HEIGHT*64 - Game.HEIGHT);
		
		if(hp <= 0) {
			Game.entities = new ArrayList<Entity>();
			Game.enemies = new ArrayList<Enemy>();
			Game.ui = new UI();
			Game.spritesheet = new Spritesheet("/SpriteSheet.png");
			Game.player = new Player(0, 0, 64, 64, Game.spritesheet.getSprite(0, 0, 64, 64));
			Game.entities.add(Game.player);
			Game.tp = new Aura(0, 0, 64, 64, Game.spritesheet.getSprite(0, 0, 64, 64));
			Game.entities.add(Game.tp);
			Game.map = new Map("/SmallMap.png");
			
	}
	}
	/*public void isColiddingLifeFLame() {
		if (checkItem() instanceof LifeFlame) {
			if (isColindingWith(this, checkItem())) {
				System.out.println("Colidiu");
				hp+= 10;
				if (hp > maxhp) {
					hp = maxhp;
				}
				//Game.entities.remove(checkItem());
			}
			
		}
		
	}*/
	
	public void shoot() {
		if (mouseShoot) {
			mouseShoot = false;
			double angle = 0;
			angle = Math.atan2(my - (this.getY() + 32 - Camera.y), mx - (this.getX() + 32 - Camera.x));
			
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			System.out.println(angle);
			
			FireBall fireball = new FireBall(this.getX(), this.getY(), 20, 20, null, dx, dy);
			Game.fireballs.add(fireball);
		}
		
	}
	
	public void isColiddingLifeFLame() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity en = Game.entities.get(i);
			if (en instanceof LifeFlame) {
				if (isColindingWith(this, en)) {
					//System.out.println("Colidiu");
					LifeFlame.healing = true;
					Game.entities.remove(en);
				}
			}
		}
	}
	
	public void isColiddingXPDrop() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity en = Game.entities.get(i);
			if (en instanceof XPDrop) {
				if (isColindingWith(this, en)) {
					//System.out.println("Colidiu" + power);
					power++;
					if (power > maxPower) {
						power = maxPower;
					}
					Game.entities.remove(en);
				}
			}
		}
	
		
	}
	
	public void isColiddingFirePower() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity en = Game.entities.get(i);
			if (en instanceof FirePower) {
				if (isColindingWith(this, en)) {
					//System.ouln("Colidiu" + power);
					power++;
					if (power > maxPower) {
						power = maxPower;
					}
					Game.entities.remove(en);
				}
			}
		}
	
		
	}
	
	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if (dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if (dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		super.render(g);
		//g.setColor(Color.BLUE);
		//g.fillRect(this.getX()- Camera.x, this.getY() - Camera.y, Map.TILE_SIZE, Map.TILE_SIZE);
	}
}
