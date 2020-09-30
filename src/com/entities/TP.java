package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class TP extends Entity{
	
	public boolean right, left, up, down;
	
	public static boolean transform = false;

	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	private BufferedImage[] aura;
	
	public boolean moving = false;

	public TP(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		aura = new BufferedImage[4];
		
		
	
		for (int i = 0; i < 4; i++) {                                                       
			aura [i] = Game.spritesheet.getSprite((i*64), 256, 64, 64);               
		}                                                                                   
		
	}
	
	public void tick() {
		double speed = Game.player.speed;
		moving = false;
		if (Game.player.right && Map.isFree((int)(x+speed), y)) {
			moving = true;
			x+=speed;
			
		}else if (Game.player.left && Map.isFree((int)(x-speed), y)) {
			moving = true;
			x-=speed;
		
		}if (Game.player.up && Map.isFree(x, (int)(y-speed))) {
			moving = true;
			y-=speed;
		
		}else if (Game.player.down && Map.isFree(x, (int)(y+speed))) {
			moving = true;
			y+=speed;
		}

		if (transform) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		
		//Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, Map.WIDTH*64 - Game.WIDTH);
		//Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, Map.HEIGHT*64 - Game.HEIGHT);
	}
	public void render(Graphics g) {
		if (transform) {
			g.drawImage(aura[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
}
