package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class E_Basic1 extends GameObject {
	// private double s = 16.0;

	private Handler handler;

	public E_Basic1(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 5;
		velY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		// 40 works better but who knows lets not hardcode yet
		if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		/*
		 * if (velX > 0 || velY > 0) { s = s - 0.4; } if (velX < 0 || velY < 0)
		 * { s = s + 0.4;
		 */
		handler.object.add(new Trail(x, y, ID.TRAIL, new Color(195, 13, 31), 16, 16, 0.03f, handler));
	}

	public void render(Graphics g) {
		//g.setColor(new Color(195, 13, 31));
		// g.fillRect(x, y, (int) s, (int) s);
		//g.fillRect(x, y, 16, 16);

		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(new Color(195, 13, 31));
		g2d.draw(getBounds());
		//if (id == ID.Player) 
		g.setColor(new Color(27,142, 137));
		g.fillRect((int)x+1, (int)y+1, 16-1, 16-1);

	}

}
