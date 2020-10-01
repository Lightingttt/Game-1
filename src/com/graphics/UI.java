package com.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.main.Game;

public class UI {
	
	private int hpBarX = 20;
	private int hpBarY = 20;
	private int hpBarW = 200;
	private int hpBarH = 20;
	        
	private int mpBarX = 20;
	private int mpBarY = 43;
	private int mpBarW = 200;
	private int mpBarH = 15;
	        
	private int spBarX = 20;
	private int spBarY = 63;
	private int spBarW = 200;
	private int spBarH = 15;
	        
	public void hpBar(Graphics g) {
			
			g.setColor(Color.RED);
			g.fill3DRect(hpBarX, hpBarY, hpBarW, hpBarH, true);
			g.setColor(Color.GREEN);
			g.fill3DRect(hpBarX, hpBarY, (int)((Game.player.hp/Game.player.maxhp)*200), hpBarH, true);
		
		}
	
	public void mpBar(Graphics g) {
		
			g.setColor(Color.CYAN);
			g.fill3DRect(mpBarX, mpBarY, mpBarW, mpBarH, true);
			g.setColor(Color.BLUE);
			g.fill3DRect(mpBarX, mpBarY, mpBarW, mpBarH, true);
		
	}
	public void spBar(Graphics g) {
		
		g.setColor(Color.PINK);
		g.fill3DRect(spBarX, spBarY, spBarW, spBarH, true);
		g.setColor(Color.MAGENTA);
		g.fill3DRect(spBarX, spBarY, spBarW, spBarH, true);
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fill3DRect(hpBarX-3, hpBarY-3, hpBarW+6, hpBarH+45, true);
		this.hpBar(g);
		this.mpBar(g);
		this.spBar(g);
}
	}
