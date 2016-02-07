package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class E_S_Boss_Bullet extends GameObject {
	// private double s = 16.0;

	private Handler handler;
	private Random r = new Random();

	public E_S_Boss_Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = r.nextInt((5- - 5) + -5);
		velY = 7;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		//if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		// 40 works better but who knows lets not hardcode yet
		//if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		/*
		 * if (velX > 0 || velY > 0) { s = s - 0.4; } if (velX < 0 || velY < 0)
		 * { s = s + 0.4;
		 */
		if(y >= Game.HEIGHT) handler.removeObject(this);
		//handler.object.add(new Trail(x, y, ID.TRAIL, new Color(214, 207, 202), 16, 16, 0.2f, handler));
	}
	
	public void render(Graphics g) {
		//g.setColor(new Color(195, 13, 31));
		// g.fillRect(x, y, (int) s, (int) s);
		//g.fillRect(x, y, 16, 16);

		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(new Color(214, 207, 202));
		g2d.draw(getBounds());
		//if (id == ID.Player) 
		g.setColor(new Color(214, 207, 202));
		g.fillRect((int)x+1, (int)y+1, 16-1, 16-1);

	}

}
