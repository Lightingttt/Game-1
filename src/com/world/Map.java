package com.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	public Map(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			int[] pixel = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixel, 0, map.getWidth());
			
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getWidth(); yy++) {
					int pixelatual = pixel[xx + (yy * map.getWidth())];
				switch(pixelatual) {
				case 0xFFFF0000:
					System.out.println("V");
				case 0xFF00FFFF:
					
				}
			}
		} 
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}

