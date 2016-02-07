package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class E_Hoaming1 extends GameObject {
	// private double s = 16.0;

	private Handler handler;
	private boolean color = false;
	private GameObject player;

	public E_Hoaming1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.PLAYER)
				player = handler.object.get(i);
		}

		// velX = 5;
		// velY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 40, 40);
	}

	public void tick() {

		x += velX;
		y += velY;

		float diffX = x - player.getX() ;
		float diffY = y - player.getY() ;

		float distance = (float) Math.sqrt(Math.pow((x - player.getX()), 2) + Math.pow((y - player.getY()), 2));

		//velX = (float) ( Math.abs((Math.pow(((-1.0 / distance) * diffX)*2 , 0.5))) * ((-1.0 / distance) * diffX));
		//velY = (float) ( Math.abs(Math.pow(((-1.0 / distance) * diffY)* 2, 0.5)) * ((-1.0 / distance) * diffY));

		velX = (float) ((-1.0 / distance) * diffX)*3;
		velY = (float) ((-1.0 / distance) * diffY)*3;
		/*
		if(velX > -2 && velX < 2 ){
			if(velX < 0) velX = -2;
			else velX = 2;
		}
		if(velY > -2 && velY < 2 ){
			if(velY < 0) velY = -2;
			else velX = 2;
		}
		*/
		//velX = Game.clamp(velX, 4, 10);
		//velY = Game.clamp(velY, 4, 10); 
		// if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		// 40 works better but who knows lets not hardcode yet
		// if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		/*
		 * if (velX > 0 || velY > 0) { s = s - 0.4; } if (velX < 0 || velY < 0)
		 * { s = s + 0.4;
		 */

		//if (color)
			//handler.object.add(new Trail(x, y, ID.Trail, new Color(97, 8, 158), 40, 40, 0.5f, handler));
		//if (!color)
			//handler.object.add(new Trail(x, y, ID.Trail, new Color(195, 13, 31), 40, 40, 0.5f, handler));
		//color = !color;

	}
	public boolean visible(){
		if ((y <= 0 || y >= Game.HEIGHT - 32) || (x <= 0 || x >= Game.WIDTH - 16)){
			return false;
		}
		return true;
	}

	public void render(Graphics g) {
		// g.setColor(new Color(195, 13, 31));
		// g.fillRect(x, y, (int) s, (int) s);
		// g.fillRect(x, y, 16, 16);
		if(visible()){
			Graphics2D g2d = (Graphics2D) g;

			g.setColor(new Color(195, 13, 31));
			g2d.draw(getBounds());
			// if (id == ID.Player)
			g.setColor(new Color(97, 8, 158));
			g.fillRect((int) x + 1, (int) y + 1, 40 - 1, 40 - 1);
			
		}


	}

}
