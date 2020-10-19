package com.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.entities.Aura;
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
		g.fill3DRect(hpBarX, hpBarY, hpBarW, hpBarH, false);
		
			
		if (Aura.transform) {
			
			g.setColor(Color.ORANGE);
			g.fill3DRect(hpBarX, hpBarY, (int)((Game.player.hp/Game.player.maxhp)*200), hpBarH, true);;
			
		}else{
			
			g.setColor(Color.GREEN);
			g.fill3DRect(hpBarX, hpBarY, (int)((Game.player.hp/Game.player.maxhp)*200), hpBarH, true);
		}
		
	
	}
	
	public void mpBar(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fill3DRect(mpBarX, mpBarY, mpBarW, mpBarH, false);
		g.setColor(Color.CYAN);
		g.fill3DRect(mpBarX, mpBarY, (int)((Game.player.mp/Game.player.maxmp)*200), mpBarH, true);
		
	}
	public void spBar(Graphics g) {
		
		g.setColor(Color.PINK);
		g.fill3DRect(spBarX, spBarY, spBarW, spBarH, false);
		g.setColor(Color.MAGENTA);
		g.fill3DRect(spBarX, spBarY, (int)((Game.player.sp/Game.player.maxsp)*200), spBarH, true);
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fill3DRect(17, 17, 206, 65, true);
		this.hpBar(g);
		this.mpBar(g);
		this.spBar(g);
}
	}
