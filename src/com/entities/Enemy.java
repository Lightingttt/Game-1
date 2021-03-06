package com.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class Enemy extends Entity{
	
	private double speed = 1;
	
	public static int maskX = 20, maskY = 20, maskW = 30, maskH = 30;
	
	public Rectangle enemyRect;
	
	private Graphics f;
	
	public int hp = 100;
	public static double dmg = 10;
	
	int frames = 0, maxFrames = 70;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		enemyRect = new Rectangle(this.getX(), this.getY(), Map.WIDTH, Map.HEIGHT);
		
		
		
	}
	
	public void tick() {
		this.setMask(22, 22, 30, 25);
		//System.out.println(speed);
		DmgText dmg = new DmgText(0, 0, width, height, null);
		if (this.isColidingWithPlayer() == false) {
		if (x < Game.player.getX() && Map.isFree((int)(x+speed), this.getY()) && !isColinding((int)(x+speed), this.getY())){
			
			x += speed;
			
		}else if (x > Game.player.getX() && Map.isFree((int)(x-speed), this.getY()) && !isColinding((int)(x-speed), this.getY())){
			
			x -= speed;
			
		}if (y < Game.player.getY() && Map.isFree(this.getX(), (int) (y+speed)) && !isColinding(this.getX(), (int) (y+speed))){
			
			y += speed;
			
		}else if (y > Game.player.getY() && Map.isFree(this.getX(), (int) (y-speed)) && !isColinding(this.getX(), (int) (y-speed))){
			
			y -= speed;
			
		}
		}else {
			
			damage();
		}
		//autoDestroy();

	}
	
	public boolean isColidingWithPlayer () {
		
		Game.player.playerRect = new Rectangle(Game.player.getX() + maskX, Game.player.getY() + maskY, maskW, maskH);
		enemyRect = new Rectangle(this.getX() + maskX, this.getY() + maskY, maskW, maskH);
		
		return Game.player.playerRect.intersects(enemyRect);
	}
	
	public boolean isColinding(int xNext, int yNext) {
		Rectangle colisionRect = new Rectangle(xNext + maskX, yNext + maskY, maskW, maskH);
		
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e == this)
				continue;
			Rectangle targetRect = new Rectangle(e.getX() + maskX, e.getY() + maskY, maskW, maskH);
			if (colisionRect.intersects(targetRect)) {
				return true;
				
			}
		}
		return false;
	}
	
	public void damage() {
		
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				if (Game.rnd.nextInt(100) <90){
					//System.out.println("ATACOU");
					Game.player.hp-= dmg;
					
					DmgText dmg = new DmgText(Game.player.getX(), Game.player.getY(), width, height, null);
					Game.dmgTexts.add(dmg);
					
					if (Game.player.hp < 0)
						Game.player.hp = 0;
					
				
			}
		}
	
		
		
	}
	
	public void autoDestroy() {
		int a = Game.rnd.nextInt(100);
		
		
		if(a < 20) {
			hp --;
		}
		if(hp <0) {
			Game.enemies.remove(this);
			Game.entities.remove(this);
			
			XPDrop xp = new XPDrop(this.getX(), this.getY(), width, height, null);
			Game.entities.add(xp);
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.BLUE);
		//g.fillRect(this.getX() + maskX - Camera.x, this.getY() + maskY - Camera.y, maskW, maskH);

	}
}
