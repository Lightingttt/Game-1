package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	
	private double speed = 3;
	
	
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	private BufferedImage[] rightPlayer, leftPlayer, upPlayer, downPlayer;
	
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
	}
	
	public void tick() {
		//System.out.println(moving);
		moving = false;
		if (right && Map.isFree((int)(x+speed), y)) {
			moving = true;
			dir = right_dir;
			x+=speed;
			
		}else if (left && Map.isFree((int)(x-speed), y)) {
			moving = true;
			dir = left_dir;
			x-=speed;
		
		}if (up && Map.isFree(x, (int)(y-speed))) {
			dir = up_dir;
			moving = true;
			y-=speed;
		
		}else if (down && Map.isFree(x, (int)(y+speed))) {
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
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, Map.WIDTH*64 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, Map.HEIGHT*64 - Game.HEIGHT);
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
	}
}
