package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class KeyInput extends KeyAdapter {
	
	
	private boolean controlSent = false;
	private Handler handler;
	private boolean circleToggle = false;
	private boolean[] keyDown = new boolean[4];
	
	
	public KeyInput( Handler handler){
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_C) controlSent = !controlSent;
		
		for(int i = 0; i < handler.object.size(); i ++){
			try {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.PLAYER){
					tempObject = (Player)tempObject;
					Player p = (Player) tempObject;
					//key events
					if(key == KeyEvent.VK_W) {p.setVelY(-4); keyDown[0] = true;}
					if(key == KeyEvent.VK_S) {p.setVelY(4);  keyDown[1] = true;}
					if(key == KeyEvent.VK_D) {p.setVelX(4);  keyDown[2] = true;}
					if(key == KeyEvent.VK_A) {p.setVelX(-4); keyDown[3] = true;}
					p.following = !controlSent;
					if(p.following == false){
						p.setVelX(0);
						p.setVelY(0);
					}
					
					
					
				}
				if(tempObject.getId() == ID.SENTINEL){
					Sentinel s = (Sentinel)tempObject;
					
					//key events
					if(key == KeyEvent.VK_UP)   {s.setVelY(-4); }
					if(key == KeyEvent.VK_DOWN) {s.setVelY(4);  }
					if(key == KeyEvent.VK_RIGHT){s.setVelX(4);  }
					if(key == KeyEvent.VK_LEFT) {s.setVelX(-4); }
					if(key == KeyEvent.VK_SPACE){s.increasing = !s.increasing;}
					s.following = controlSent;
					if(s.following == false){
						s.setVelX(0);
						s.setVelY(0);
					}
				}
			} catch (NullPointerException ex) {
				System.err.println("Error int key input class");
			}	

		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PLAYER){
				//key events
				if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3] = false;//tempObject.setVelX(0);
				
				
				//verticalMOV
				//if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontalMOV
				//if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			
			
			}
			
		}
		
	}

	
}
