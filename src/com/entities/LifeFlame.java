package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class LifeFlame extends Entity {

	private static double heal = 2.03265247, maxHeal = 36, curHeal = 0;
	
	private static double teste = 2.03265247;
	
	public static boolean healing = false;
	
	private static int frames = 0, maxFrames = 60;
	
	public LifeFlame(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		
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
				System.out.println(teste);
				
				System.out.println("Curou " + Game.player.hp);
				if(curHeal > maxHeal) {
					healing = false;
					curHeal = 0;
			}
		}
	}
}
		

	


