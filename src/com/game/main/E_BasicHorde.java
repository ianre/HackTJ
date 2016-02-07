package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class E_BasicHorde extends GameObject {
	private double s = 30.0;
	private Handler handler;

	public E_BasicHorde(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 1;
		velY = 0;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)s, (int)s);
	}
       
	public void tick() {
		x += velX;
		y += velY;
		
		if ( y >= Game.HEIGHT - 32) handler.removeObject(this);;
		// 40 works better but who knows lets not hardcode yet
		if ( x >= Game.WIDTH - 16) handler.removeObject(this);;
		/*
		 * if (velX > 0 || velY > 0) { s = s - 0.4; } if (velX < 0 || velY < 0)
		 * { s = s + 0.4;
		 */
		//handler.object.add(new Trail(x, y, ID.TRAIL, new Color(195, 13, 31), (int)s, (int)s, 0.5f, handler));
	}

	public void render(Graphics g) {
		//g.setColor(new Color(195, 13, 31));
		// g.fillRect(x, y, (int) s, (int) s);
		//g.fillRect(x, y, 16, 16);

		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(new Color(255, 13, 31));
		g2d.draw(getBounds());
		//if (id == ID.Player) 
		g.setColor(new Color(255,142, 137));
		g.fillRect((int)x+1, (int)y+1, (int)(s-1),(int)(s-1));

	}

}
