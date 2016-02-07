package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter implements MouseMotionListener {

	private Game game;
	private Handler handler;

	private int innerSquares = 1;
	private boolean gBack = false;
	private Random r = new Random();

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	public void mouseMoved(MouseEvent e){
		//System.out.println("Mouse Moved");
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.HELP) {
			if (mouseOver(mx, my, 1000, 335, 120, 45)) {
				game.gameState = STATE.MENU;
				return;
			}
		}

		if (game.gameState == STATE.MENU) {
			// play button
			if (mouseOver(mx, my, 400, 335, 120, 45)) {
				game.gameState = STATE.GAME;
				game.addMouseMotionListener(new MouseInput(handler, game));
				
				handler.addObject(new Sentinel(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.SENTINEL, handler));
				handler.addObject(new E_Basic1(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.E_BASIC1, handler));
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.PLAYER, handler));

			}
			//help button
			if (mouseOver(mx, my, 1000, 355, 120, 45)) {
				game.gameState = STATE.HELP;

			}
			//help 2
			if (mouseOver(mx, my, 1000, 355, 120, 45)) {
				game.gameState = STATE.HELP;
			}

			// quit button
			if (mouseOver(mx, my, 1000, 415, 120, 45)) {
				System.exit(0);
			}
			//g.drawString("Play", 400, 380);
			//g.drawString("Help", 1000, 380);
			//g.drawString("Quit", 1000, 460);

		}
		// help button
		// back button
		

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void tick() {
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.MENU) {
			Font font = new Font("Courier", 1, 50);
			g.setFont(Game.fontL);
			g.setColor(new Color(240, 255, 230));
			fillRectP(0, 51, 49, 49, g);
			
			drawRectP(10, 10, 39, 39, g);
			drawRectP(51, 10, 39, 39, g);
			drawRectP(10, 51, 39, 39, g);
			drawRectP(51, 51, 39, 39, g);
			drawRectP(9, 9, 82, 82, g);
			drawRectP(8, 8, 80, 80, g);
			drawRectP(8, 12, 80, 80, g);
			drawRectP(12, 8, 80, 80, g);
			drawRectP(12, 12, 80, 80, g);
			for (int i = 5; i < 13; i++) {
				g.setColor(new Color(0, 12, 21));
				//drawRectP(49 - i, 51, i, i, g);
				g.setColor(new Color(240, 255, 230));
				drawRectP(49 - i, 49 - i, i, i, g);
				
				drawRectP(51, 49 - i, i, i, g);
				
				drawRectP(51, 51, i, i, g);
			}
			for (int i = 1;i < 6; i++) {
				g.setColor(new Color(0, 12, 21));
				drawRectP(49 - i, 51, i, i, g);
				g.setColor(new Color(240, 255, 230));
				
			}
			for (int i = 12; i < 100; i++) {
				g.setColor(new Color(0, 12, 21));
				drawRectP(49 - i, 51, i, i, g);
				g.setColor(new Color(240, 255, 230));
				
			}
			for (int i = 43; i < 55; i++) {
				drawRectP(49 - i, 51, i, i, g);
				drawRectP(49 - i, 49 - i, i, i, g);
				drawRectP(51, 49 - i, i, i, g);
				drawRectP(51, 51, i, i, g);
			}
			for (int i = 28; i < 30; i++) {
				drawRectP(49 - i, 51, i, i, g);
				drawRectP(49 - i, 49 - i, i, i, g);
				drawRectP(51, 49 - i, i, i, g);
				drawRectP(51, 51, i, i, g);
			}

			if (!gBack) {
				innerSquares++;
				if (innerSquares > 50)
					gBack = true;
			} else {
				innerSquares--;
				if (innerSquares < 0)
					gBack = false;
			}

			// int varTop = Game.HEIGHT -50 -10;
			// int varLeft = Game.WIDTH -25 - 10;
			// g.drawRect(10 , 10, varLeft + 10, varTop + 10);
			// g.drawRect(10 , 10, (int)(varLeft/2), (int)(varTop/2));
			// g.drawRect(10 + (int)(varLeft/2), 10 + (int)(varTop/2),
			// (int)(varLeft/2) + 11, (int)(varTop/2)+ 11);
			// g.drawRect(10 + (int)(varLeft/4.0), 10+ (int)(varTop/4.0),
			// (int)(varLeft/4.0) * 2, (int)(varTop/4.0) * 2);
			// g.drawRect((int) (Game.WIDTH /2.0), (int)(Game.HEIGHT / 2.0),
			// (int)
			// (Game.WIDTH /2.0), (int)(Game.HEIGHT / 2.0));
			// g.drawString("Menu", 150, 150);
			g.drawString("Play", 400, 380);
			g.drawString("Help", 1000, 380);
			g.drawString("Quit", 1000, 460);
			
			// g.setColor(Color.RED);
			// g.drawRect(0, 0, (int)(varX*100),(int)(varY * 100));

		} else if (game.gameState == STATE.HELP) {
			Font font = new Font("Courier", 1, 50);
			g.setFont(Game.fontL);
			g.setColor(new Color(240, 255, 230));

			drawRectP(10, 10, 39, 39, g);
			drawRectP(51, 10, 39, 39, g);
			drawRectP(10, 51, 39, 39, g);
			drawRectP(51, 51, 39, 39, g);
			drawRectP(9, 9, 82, 82, g);
			drawRectP(8, 8, 80, 80, g);
			drawRectP(8, 12, 80, 80, g);
			drawRectP(12, 8, 80, 80, g);
			drawRectP(12, 12, 80, 80, g);

			// SW
			for (int i = 1; i < 2; i++) {
				drawRectP(49 - i, 51, i, i, g);
			}
			// NW
			for (int i = 1; i < 28; i++) {
				//drawRectP(49 - i, 49 - i, i, i, g);
			}
			// NE
			for (int i = 1; i < 13; i++) {
				drawRectP(51, 49 - i, i, i, g);
			}
			// SE
			for (int i = 1; i < 28; i++) {
				drawRectP(51, 51, i, i, g);
			}
			for (int i = 43; i < 55; i++) {
				drawRectP(49 - i, 51, i, i, g);
				drawRectP(49 - i, 49 - i, i, i, g);
				drawRectP(51, 49 - i, i, i, g);
				drawRectP(51, 51, i, i, g);
			}
			for (int i = 28; i < 30; i++) {
				drawRectP(49 - i, 51, i, i, g);
				drawRectP(49 - i, 49 - i, i, i, g);
				drawRectP(51, 49 - i, i, i, g);
				drawRectP(51, 51, i, i, g);
			}
			g.setColor(new Color(240, 255, 230));
			fillRectP(0, 51, 49, 49, g);
			
			g.drawString("Back", 1000, 380);
			//Font font2 = new Font("Courier", 1, 20);
			g.setFont(Game.fontL);
			//g.setColor(new Color(240, 255, 230));

			g.drawString("Game ", 320, 210);
			g.drawString("optimized for", 320, 250);
			g.drawString("Myo armband", 320, 290);
			g.setFont(Game.fontS);
			g.setColor(new Color(0, 12, 31));
			//g.drawString("Game optimized for Myo", 320, 460);
			int d = 420;
			g.drawString("Make a Fist", 5, d+=50);
			g.drawString("    to switch character", 5,  d+=30);
			g.drawString("Wave Right", 5,  d+=50);
			g.drawString("    to heal", 5,  d+=30);
			g.drawString("Double Tap", 5,  d+=50);
			g.drawString("    to toggle control", 5,  d+=30);
			g.drawString("Time increments level", 5,  d+=30);
			g.drawString("Level increments difficulty", 5,  d+=40);
			g.drawString("Player   Survives", 5,  d+=30);
			g.drawString("Sentinel Protects", 5,  d+=30);
			
			
		}

	}

	public void drawRectP(int x, int y, int width, int height, Graphics g) {
		double varX = ((Game.WIDTH - 7) * 0.01);
		double varY = ((Game.HEIGHT - 30) * 0.01);
		g.drawRect((int) (x * varX), (int) (y * varY), (int) (width * varX), (int) (height * varY));

	}
	public void fillRectP(int x, int y, int width, int height, Graphics g) {
		double varX = ((Game.WIDTH - 7) * 0.01);
		double varY = ((Game.HEIGHT - 30) * 0.01);
		g.fillRect((int) (x * varX), (int) (y * varY), (int) (width * varX), (int) (height * varY));

	}

}
