package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class LifeFlame extends Entity {

	public static double heal = 8, maxHeal = 36, curHeal = 0;
	
	public static boolean healing = false;
	
	private static int frames = 0, maxFrames = 60;
	
	public LifeFlame(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		
		
				}
	
	public void tick() {
		this.setMask(15, 20, 23, 25);
	}
	
	public static void heal() { 
		if(healing)
			frames++;
			//System.out.println("Ativo");
			//System.out.println("Frames "+ frames);
			if (frames >=maxFrames && healing) {
				Game.player.hp += heal;
				curHeal += heal;
				frames = 0;				
				System.out.println("Curou " + Game.player.hp);
				if(curHeal > maxHeal || Game.player.hp >= Game.player.maxhp) {
					healing = false;
					curHeal = 0;
			}
		}
	}
}
		

	


