package com.game.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.game.main.Game.STATE;

public class Handler {
	private Game game;
	private int crossX, crossY;
	private boolean killing;
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public Handler(Game game) {
		this.game = game;
		this.killing = false;
	}

	public void tick() {
		if (!killing) {
			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				tempObject.tick();
			}
		}

	}

	private void drawCrosshair(Graphics g) { 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(new Color(195, 13, 31));
		if (game.gameState == STATE.GAME) {
			fillCenteredCircle(g2d, crossX, crossY, 20);
			int diff = 50;
			g2d.drawRect(crossX - 30, crossY - 30, 60, 60);
			g2d.drawLine(crossX - diff, crossY, crossX + diff, crossY);
			g2d.drawLine(crossX, crossY - diff, crossX, crossY + diff);
			for (int j = 2; j < 4; j++) {
				drawArcC(g2d, crossX, crossY, 70 * j, 0, 100);
				drawArcC(g2d, crossX, crossY, 70 * j, 120, 100);
				drawArcC(g2d, crossX, crossY, 70 * j, 240, 100);
			

			}
		}
		//System.out.println(crossX + " " + crossY);
		g2d.setStroke(new BasicStroke(1));

	}

	public void setCrossH(int x, int y) {
		this.crossX = x;
		this.crossY = y;

	} 

	public void drawArcC(Graphics2D g, int x, int y, int r, int th1, int th2) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.drawArc(x, y, r, r, th1, th2);
	}

	public void fillCenteredCircle(Graphics2D g, int x, int y, int r) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.fillOval(x, y, r, r);
	}

	public void render(Graphics g) {
		drawCrosshair(g);

		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void setWall() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.id == ID.PLAYER || tempObject.id == ID.SENTINEL) {
				tempObject.setVelX(0);
				tempObject.setVelY(0);
				tempObject.setX(Game.WIDTH / 2);
				tempObject.setVelY(Game.HEIGHT / 2);
			}
		}

	}

	public void killEnemies() {
		killing = true;
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.id == ID.PLAYER) {
				object.clear();

				addObject(new Sentinel((int) tempObject.getX(), (int) tempObject.getY(), ID.SENTINEL, this));
				addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.PLAYER, this));
			}
		}
		killing = false;
	}
}
