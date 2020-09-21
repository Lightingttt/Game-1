package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	
	private double speed = 1;
	
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 1 /*,i = 0, iM = 5*/;
	private BufferedImage[] rightPlayer, leftPlayer/*, standLeftPlayer, standRightPlayer*/;
	
	public boolean moving = false;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[2];
		leftPlayer = new BufferedImage[2];
		/*standLeftPlayer = new BufferedImage[4];
		standRightPlayer = new BufferedImage[4];*/
		
		for (int i = 0; i < 2; i++) {
			rightPlayer [i] = Game.spritesheet.getSprite((i*64), 64, 64, 64);
		}
		for (int i = 0; i < 2; i++) {
			leftPlayer [i] = Game.spritesheet.getSprite(128 + (i*64), 64, 64, 64);
		}
		/*for (int i = 0; i < 4; i++) {
			standRightPlayer [i] = Game.spritesheet.getSprite((i*64), 0, 64, 64);
		}
		for (int i = 0; i < 4; i++) {
			standLeftPlayer [i] = Game.spritesheet.getSprite(256 + (i*64), 0, 64, 64);
		}*/
	}
	
	public void tick() {
		moving = false;
		if (right) {
			moving = true;
			dir = right_dir;
			x+=speed;
			
		}else if (left) {
			moving = true;
			dir = left_dir;
			x-=speed;
		
		}if (up) {
			moving = true;
			y-=speed;
		
		}else if (down) {
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
		}/*else if (moving = false) {
			frames ++;
			if (frames == 5) {
				frames = 0;
				i++;
				if(i > iM) {
					i = 0;
				}
				
			}
		}*/
		
	}
	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
		}else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		}/*else if (moving == false && dir == right_dir) {
			g.drawImage(standRightPlayer[i], this.getX(), this.getY(), null);
		}*/
	}

}
