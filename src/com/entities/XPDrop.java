package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class XPDrop extends Entity{
	
	public Rectangle enemyRect;
	
	private double speed = 1, spdmult = 0.02;


	public XPDrop(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		enemyRect = new Rectangle(this.getX(), this.getY(), Map.WIDTH, Map.HEIGHT);
		
		this.setMask(this.getX(), this.getY(), 10, 10);
		
	}
	
	public void tick() {
		//System.out.println(speed);
		
		DmgText dmg = new DmgText(0, 0, width, height, null);
		if (this.isColidingWithPlayer() == false) {
		/*if (x < Game.player.getX()){
			
			x += speed * (spdmult += 0.02);
			
		}else if (x > Game.player.getX()){
			
			x -= speed * (spdmult += 0.02);
			
		}if (y < Game.player.getY()){
			
			y += speed * (spdmult += 0.02);
			
		}else if (y > Game.player.getY()){
			
			y -= speed * (spdmult += 0.02);
			
		
	}*/
			double angle = 0;
			angle = Math.atan2(Game.player.getY() + 32 - (this.getY()), Game.player.getX() + 32 - (this.getX()));
			
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			x += dx*speed * (spdmult += 0.02);
			y += dy*speed * (spdmult += 0.02);
			
		}else {
			
			Game.entities.remove(this);
			
		}
	}
		
		public boolean isColidingWithPlayer () {
			
			Game.player.playerRect = new Rectangle(Game.player.getX() + maskX, Game.player.getY() + maskY, maskW, maskH);
			enemyRect = new Rectangle(this.getX() + maskX, this.getY() + maskY, maskW, maskH);
			
			return Game.player.playerRect.intersects(enemyRect);
			
		}
	
	public void render(Graphics g) {
		
		//g.setColor (Color.red);
		//g.fillRect (this.getX()- Camera.x, this.getY()- Camera.y, width, height);
		g.setColor (Color.blue);
		g.fillOval (this.getX() - Camera.x, this.getY() - Camera.y, 10, 10);
		
	}
	

}
