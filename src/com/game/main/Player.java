package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player extends GameObject {
	
	public boolean following = true;
	private int toX, toY;
	Random r = new Random();
	Handler handler;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		// velX = r.nextInt(5) + 1;
		// velY = r.nextInt(5) ;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	public void tick() {
		x += velX;
		y += velY;
		
		if(following){
			float diffX = this.x - toX ;
			float diffY = this.y - toY ;

			float distance = (float) Math.sqrt(Math.pow((this.x - toX), 2) + Math.pow((this.y - toY), 2));

			//velX = (float) ( Math.abs((Math.pow(((-1.0 / distance) * diffX)*2 , 0.5))) * ((-1.0 / distance) * diffX));
			//velY = (float) ( Math.abs(Math.pow(((-1.0 / distance) * diffY)* 2, 0.5)) * ((-1.0 / distance) * diffY));

			velX = (float) ((-2/ distance) * diffX)*5;
			velY = (float) ((-2/ distance) * diffY)*5;
			
		} else {
			float diffX = this.x - toX ;
			float diffY = this.y - toY ;

			float distance = (float) Math.sqrt(Math.pow((this.x - toX), 2) + Math.pow((this.y - toY), 2));

			//velX = (float) ( Math.abs((Math.pow(((-1.0 / distance) * diffX)*2 , 0.5))) * ((-1.0 / distance) * diffX));
			//velY = (float) ( Math.abs(Math.pow(((-1.0 / distance) * diffY)* 2, 0.5)) * ((-1.0 / distance) * diffY));

			velX = (float) ((-0.7 / distance) * diffX)*5;
			velY = (float) ((-0.7 / distance) * diffY)*5;

		}

		
		collision();
	}
	
	public void follow(int x, int y){
		this.toX = x;
		this.toY = y;
	}
	
	public void collision(){
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i); 
			if(tempObject.getId() == ID.E_BASIC1 || tempObject.getId() == ID.E_BASICH){
				
				
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					
					HUD.HEALTH -=1;
				}
			}
			if(tempObject.getId() == ID.E_FAST3 || tempObject.getId() == ID.E_FAST1 || tempObject.getId() == ID.E_FAST2){
				
				
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					
					HUD.HEALTH -=1;
				}
			}
			if(tempObject.getId() == ID.E_HOAMING1 ){
				
				
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					
					HUD.HEALTH -=1;
				}
			}
		}
		
	}
	public void heal(){
		if(HUD.HEALTH < 200){
			HUD.HEALTH++;
		}

	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(new Color(214, 207, 202));
		g2d.draw(getBounds());
		//if (id == ID.Player) 
		g.setColor(new Color(27,142, 137));
		g.fillRect((int)x+1, (int)y+1, 32-1, 32-1);
		

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
