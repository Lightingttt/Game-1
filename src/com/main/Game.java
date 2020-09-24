package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.entities.Entity;
import com.entities.Player;
import com.graphics.Spritesheet;
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
	public static Spritesheet spritesheet;	
	
	public static Map map;
	public static Player player;
	
	
	public Game () {
		addKeyListener(this);
		//player = sheet.getSprite(0, 0, 32, 32);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT)); //tamanho da janela
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/SpriteSheet.png");
		player = new Player(0, 0, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
		map = new Map("/Map.png");
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
		g.dispose();		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		bs.show();
		
	}

	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
