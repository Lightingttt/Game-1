package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.entities.Entity;
import com.entities.Player;
import com.entities.Aura;
import com.entities.DmgText;
import com.entities.Enemy;
import com.graphics.Spritesheet;
import com.graphics.UI;
import com.world.Map;


public class Game extends Canvas implements Runnable, KeyListener {
	

	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	
	public static final int WIDTH = 768;
	public static final int HEIGHT = 640;
	
	private boolean isRunning = true;
	private Thread thread;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<DmgText> dmgTexts;
	public static Spritesheet spritesheet;	
	
	public static Map map;
	public static Player player;
	public static Aura tp;
	public static UI ui;
	
	public static Random rnd = new Random();
	
	private String gameStatus = "NORMAL";
	
	public Game () {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT)); //tamanho da janela
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		ui = new UI();
		spritesheet = new Spritesheet("/SpriteSheet.png");
		player = new Player(0, 0, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
		tp = new Aura(0, 0, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(tp);
		dmgTexts = new ArrayList<DmgText>();
		map = new Map("/SmallMap.png");
	}
	
	private void initFrame() {
		frame = new JFrame("Game 1");
		frame.add(this);
		frame.pack();
		frame.setResizable(false); // Desabilita o redimensionamento1 do tamanho da janela
		frame.setLocationRelativeTo(null); //Adiciona a posição da janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Coloca para encerrar o software como padrão
		frame.setVisible(true);
		
	}

	public synchronized void start () {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
	}
		
	public synchronized void stop() {
	
	}
	
	public static void main (String[] args) {
		Game game = new Game();
		game.start();
		
	}
	
	
	public void tick() {
			for (int i = 0; i<entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			for (int i = 0; i<dmgTexts.size(); i++) {
				Entity e = dmgTexts.get(i);
				e.tick();
			}
	}
	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		map.render(g);
		for (int i = 0; i<entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		for (int i = 0; i<dmgTexts.size(); i++) {
			Entity e = dmgTexts.get(i);
			e.render(g);
		}
		ui.render(g);
		g.dispose();		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("SP", 20, 63+13);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("MP", 20, 43+13);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("HP", 20, 23+13);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 14));
		g.drawString("Fire Power: " + Player.power, 20, 83+13);
		
		bs.show();
		
	}

	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames ++;
				delta --;
				
			} 
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS:" + frames);
				frames = 0;
				timer += 1000;
				//System.out.println("Esta checando: " + Entity.checkItem());
				//System.out.println(Game.player.hp);
			}
			
		}

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W){
			player.up = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			if(!Aura.transform && Game.player.sp > 0) {
			Aura.transform = true;
			} else {
				Aura.transform = false;
			}
			System.out.println(Aura.transform);
		}
		if(e.getKeyCode() == KeyEvent.VK_X) {
			if(!Aura.transform && Game.player.sp > 0) {
			Aura.transform = true;
			} else {
				Aura.transform = false;
			}
			System.out.println(Aura.transform);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W){
			player.up = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
}
