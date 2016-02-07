package com.game.main;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import com.game.main.Game.STATE;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
	
	private Handler handler;
	private Game game;
	private int prevX , prevY, velX, velY;
	private int temp =0;
	private boolean gBack = false;
	private Random r = new Random();
	

	public MouseInput(Handler handler, Game game ) {
		this.handler = handler;
		this.game = game;
		//this.prevX = MouseInfo.getPointerInfo().getLocation().x; 
		//this.prevY = MouseInfo.getPointerInfo().getLocation().y;
	}
	
	public void calcVel(int curX, int curY) {
		
		if( curX < prevX){
			velX = -1;
		} else if (curX > prevX){
			velX = 1;
		} else {
			velX = 0;
		} 
		
		if( curY < prevY){
			velY = - 1;
		} else if (curY > prevY){
			velY = 1;
		} else {
			velY = 0;
		}
		prevX = curX;
		prevY = curY;
	}


	
	public void mouseMoved(MouseEvent e){
		//System.out.println("X: "+ e.getX() + " Y: " + e.getY() + " VX: "+ velX + " VY: " + velY);
		//System.out.println("mouseee");
		
		//calcVel( e.getX(), e.getY());
		
		
		if(game.gameState == STATE.GAME){
			handler.setCrossH(e.getX(), e.getY());
			for(int i = 0; i < handler.object.size(); i ++){
				try {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.PLAYER){
						
						Player player = (Player)tempObject;
						//key events
						player.follow(e.getX(), e.getY());
						//System.out.println(player.following);
						
						
					}
					if(tempObject.getId() == ID.SENTINEL){
						Sentinel s = (Sentinel)tempObject;
						
						s.follow(e.getX(), e.getY());
						//key events
						//if(velY < 0)   {s.setVelY(-4); }
						//if(velY > 0) {s.setVelY(4);  }
						//if(velX > 0){s.setVelX(4);  }
						//if(velX < 0) {s.setVelX(-4); }
					}
				} catch (NullPointerException ex) {
					System.err.println("Error int key input class");
				}	

			}
		}
	}


     public void mouseDragged(MouseEvent e) {
    	 //System.out.println("Hai");
     }

	public void tick() {
	}
	public void render(Graphics g) {
	}
	public void drawRectP(int x, int y, int width, int height, Graphics g) {
		//double varX = ((Game.WIDTH - 7) * 0.01);
		//double varY = ((Game.HEIGHT - 30) * 0.01);
		//g.drawRect((int) (x * varX), (int) (y * varY), (int) (width * varX), (int) (height * varY));

	}
	
}

