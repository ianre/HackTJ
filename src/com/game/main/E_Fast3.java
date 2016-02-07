package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class E_Fast3 extends GameObject {
	private double s = 120.0;

	private Handler handler;
	private boolean altTrail;
	

	public E_Fast3(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 30;
		velY = 30;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)s, (int)s);
	}

	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		// 40 works better but who knows lets not hardcode yet
		if (x <= 0 || x >= Game.WIDTH - 60) velX *= -1;
		/*
		 * if (velX > 0 || velY > 0) { s = s - 0.4; } if (velX < 0 || velY < 0)
		 * { s = s + 0.4;
		 */
		
		if (altTrail){
			
			for(int i = 0; i < 7; i++){
				handler.object.add(new Trail(x+(i*5), y+(i*5), ID.TRAIL, randomColor(), (int)s-(i*10), (int)s-(i*10), 0.02f, handler));
			}
		
		} else {
			//handler.object.add(new Trail(x, y, ID.TRAIL, Color.red, 64, 64, 0.03f, handler));
		} 
		altTrail = !altTrail;
	
	}

	public void render(Graphics g) {
		//g.setColor(new Color(195, 13, 31));
		// g.fillRect(x, y, (int) s, (int) s);
		//g.fillRect(x, y, 16, 16);

		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(randomColor());
		g2d.draw(getBounds());
		//if (id == ID.Player) 
		g.setColor(randomColor());
		g.fillRect((int)x+1, (int)y+1, (int)s-1, (int)s-1);

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
