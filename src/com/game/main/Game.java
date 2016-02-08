

package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -473349850293143017L;
	private static final double GOLDEN = 1.61803398875;
	private Thread thread;
	private boolean running = false;
	//public static Color[] colors = new Color[5];
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static Font fontL;
	public static Font fontS;
	public static Font fontX;


	
	public static final int WIDTH = 1500, HEIGHT = WIDTH / 16 * 9;

	public enum STATE {
		MENU, HELP, GAME
	};

	public STATE gameState = STATE.MENU;

	public Game() {
		System.out.println("Game Has Begun");
		handler = new Handler(this);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		
		this.addMouseListener(menu);
		
		

		new Window(WIDTH, HEIGHT, "tjGame", this);

		hud = new HUD(handler);
		spawner = new Spawn(handler, hud);

		r = new Random();
		/*
		 * for (int i = 0; i < 80; i++) { handler.addObject(new Player(0,
		 * 0,ID.Player));}
		 */

		for (int i = 0; i < 0; i++) {
			handler.addObject(new E_Basic1(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.E_BASIC1, handler));

		}

		if (gameState == STATE.GAME) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.PLAYER, handler));
			handler.addObject(new E_Basic1(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.E_BASIC1, handler));

		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
		}

	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
		stop();

	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.GAME) {
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.MENU) {
			menu.tick();
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(0, 10, 28));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.GAME) {
			hud.render(g);

		} else if (gameState == STATE.MENU || gameState == STATE.HELP) {
			menu.render(g);
		}

		g.dispose();
		bs.show();

	}


	public static void main(String[] args) throws Exception {
		// giveColor();
		//String[] arg = {};
		//LoadFont lf = new LoadFont();
		//LoadFont.main(arg);
		URL fontUrl = new URL("http://www.webpagepublicity.com/" +
                "free-fonts/a/Astra.ttf");
        fontL = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        fontL = fontL.deriveFont(Font.PLAIN,55);
        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fontL);
        fontS = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        fontS = fontL.deriveFont(Font.PLAIN,40);
        ge.registerFont(fontS);
        fontX = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        fontX = fontL.deriveFont(Font.PLAIN,100);
        ge.registerFont(fontX);
		
		new Game();
		// nice method m8

	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}

	public static double clamp(double var, double min, double max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}

	/*
	 * public static void giveColor(){ colors[0] = new Color(18, 48, 178);
	 * colors[1] = new Color(109, 204, 20); colors[2] = new Color(90, 178, 9);
	 * colors[3] = new Color(0, 48, 255); colors[4] = new Color(255, 80, 25); }
	 */

	public int colorT = 0;

	public boolean gBack = false;

	public Color getSPCColor() {
		if (!gBack) {
			colorT++;
			if (colorT > 254)
				gBack = true;
		} else {
			colorT--;
			if (colorT < 2)
				gBack = false;
		}

		int min = colorT - 30;
		if (min < 1) {
			min = 1;
		}

		return new Color(min + (int) (Math.random() * ((colorT - min) + 1)),
				(255 - min) + (int) (Math.random() * (((255 - colorT) - (255 - min)) + 1)),
				((int) min / 2) + (int) (Math.random() * ((((int) colorT / 2) - ((int) min / 2)) + 1)));
	}

}
