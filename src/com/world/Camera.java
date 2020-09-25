package com.world;

public class Camera {

	public static int x;
	public static int y;
	
	public static int clamp(int cAtual, int cMin, int cMax) {
		if (cAtual < cMin) {
			cAtual = cMin;
		}
		if (cAtual > cMax) {
			cAtual = cMax;
		}
		return cAtual;
		
	}
	
}
