package com.world;

import java.awt.image.BufferedImage;

import com.entities.Player;
import com.main.Game;

public class SandTile extends Tile {

	public SandTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	public static void slow (int xNext, int yNext) {
		
		if (Map.tiles [((xNext/62) + ((yNext/62) * Map.WIDTH))] instanceof SandTile)
		Player.speed = 2;
		else {
		Player.speed = Player.normalSpeed;	
		}
		
	}
}
