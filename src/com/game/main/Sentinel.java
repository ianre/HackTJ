package com.game.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Sentinel extends GameObject {
	private boolean healing;
	public boolean following = true;
	private int toX, toY;
	private double circleR = 2;
	public boolean increasing = false;
	private double s = 60;
	public boolean end = false;
	Random r = new Random();
	Handler handler;

	public Sentinel(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		// velX = r.nextInt(5) + 1;
		// velY = r.nextInt(5) ;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) s, (int) s);
	}

	public void tick() {
		if (healing) {

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.PLAYER) {
					Player p = (Player) tempObject;
					p.heal();

				}

			}
		}
		x += velX;
		y += velY;

		if (following) {
			float diffX = this.x - toX;
			float diffY = this.y - toY;

			float distance = (float) Math.sqrt(Math.pow((this.x - toX), 2) + Math.pow((this.y - toY), 2));

			// velX = (float) ( Math.abs((Math.pow(((-1.0 / distance) * diffX)*2
			// , 0.5))) * ((-1.0 / distance) * diffX));
			// velY = (float) ( Math.abs(Math.pow(((-1.0 / distance) * diffY)*
			// 2, 0.5)) * ((-1.0 / distance) * diffY));

			velX = (float) ((-2/ distance) * diffX) * 5;
			velY = (float) ((-2 / distance) * diffY) * 5;

		} else {
			float diffX = this.x - toX;
			float diffY = this.y - toY;

			float distance = (float) Math.sqrt(Math.pow((this.x - toX), 2) + Math.pow((this.y - toY), 2));

			// velX = (float) ( Math.abs((Math.pow(((-1.0 / distance) * diffX)*2
			// , 0.5))) * ((-1.0 / distance) * diffX));
			// velY = (float) ( Math.abs(Math.pow(((-1.0 / distance) * diffY)*
			// 2, 0.5)) * ((-1.0 / distance) * diffY));

			velX = (float) ((-0.7 / distance) * diffX) * 5;
			velY = (float) ((-0.7 / distance) * diffY) * 5;

		}

		collision();
		if (increasing) {
			if (circleR < Game.WIDTH * 1.5) {
				circleR *= 1.1;
			}
			handler.object.add(new Trail(x + (int) (s / 4), y + (int) (s / 4), ID.TRAIL, new Color(12, 12, 12),
					(int) s / 2, (int) s / 2, 0.09f, handler));
		} else {
			if (circleR > 2) {
				circleR /= 1.1;
			}
			handler.object.add(new Trail(x + (int) (s / 4), y + (int) (s / 4), ID.TRAIL, new Color(245, 245, 245),
					(int) s / 2, (int) s / 2, 0.09f, handler));
		}

	}

	public void follow(int x, int y) {
		this.toX = x;
		this.toY = y;
	}

	public void collision() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.E_BASIC1 || tempObject.getId() == ID.E_FAST3
					|| tempObject.getId() == ID.E_FAST1 || tempObject.getId() == ID.E_FAST2
					|| tempObject.getId() == ID.E_HOAMING1 || tempObject.getId() == ID.E_S_BOSS_BULLET
					|| tempObject.getId() == ID.E_BASICH) {

				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
				}
			}

		}

	}

	private int circleIter = 0;
	private int circleRad = 1;
	private int lineW = 1;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// g.setColor(new Color(245, 252, 239));
		// g2d.setStroke(new BasicStroke(1));

		// drawCenteredCircle(g2d, (int) x, (int) y, (int) circleR * 2);
		g2d.setStroke(new BasicStroke(1));

		// g.fillOval((int) (x-circleR), (int) (y-circleR), (int)circleR*2,
		// (int)circleR*2);

		if (increasing) {
			if (lineW < Game.WIDTH * 1.5) {
				healing = true;
				if (circleIter == 0) {
					g2d.setColor(Color.white);
					lineW += 10;
					g2d.setStroke(new BasicStroke(lineW));
					drawCenteredCircle(g2d, (int) x, (int) y, (int) circleRad);
					circleRad += 60;
				} else {
					g2d.setColor(Color.white);
					g2d.setStroke(new BasicStroke(lineW));
					drawCenteredCircle(g2d, (int) x, (int) y, (int) circleRad);
					circleRad += 60;

				}
				if (circleRad >= (Game.WIDTH * 2 + 10)) {
					if (circleIter == 1) {
						circleIter = 0;
					} else {
						circleIter = 1;
					}
					circleRad = 1;
				}
			} else {
				healing = false;
			}

			g2d.setStroke(new BasicStroke(1));
			g.setColor(new Color(12, 43, 32));
			g2d.draw(getBounds());
			// if (id == ID.Player)
			g.setColor(new Color(12, 12, 12));
			g.fillRect((int) x + 1, (int) y + 1, (int) s - 1, (int) s - 1);

		} else {
			g.setColor(new Color(214, 207, 202));
			g2d.draw(getBounds());
			// if (id == ID.Player)
			g.setColor(new Color(245, 252, 239));
			g.fillRect((int) x + 1, (int) y + 1, (int) s - 1, (int) s - 1);

		}
		if(end){
			g.setColor(new Color(195, 13, 31));
			g.setFont(Game.fontX);
			
			g.drawString("Protector Eliminated", Game.WIDTH/2-500, Game.HEIGHT/2-100);
			g.drawString("You Won", ((Game.HEIGHT /2) -200), ((Game.HEIGHT/2)+ 200));
			//Font font2 = new Font("Courier", 1, 20);
			s = 0;
			g.setFont(new Font("Serif", 1, 12));
			
			
		} 

	}

	public void fillCenteredCircle(Graphics2D g, int x, int y, int r) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.fillOval(x, y, r, r);
	}

	public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.drawOval(x, y, r, r);
	}

	public void activateAura() {

		increasing = true;
	}

	public void deactivateAura() {
		increasing = false;
	}

	public Color randomColor() {
		Random random = new Random(); // Probably really put this somewhere
										// where it gets executed only once
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		return new Color(red, green, blue);
	}

}
