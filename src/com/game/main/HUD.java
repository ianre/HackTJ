package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//greenvalue not implemented because *them esthetics mang*
public class HUD {
	
	private Handler handler;
	
	public static float HEALTH = 200;
	
	private int score =  0;
	private int level = 1;
	
	public HUD (Handler handler){
		this.handler = handler;
	}
     	
	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 200);
		
		score++;
	}
	public void render(Graphics g){
		g.setColor(new Color(214, 207, 202));
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(195, 13, 31));
		g.fillRect(15, 15, (int)HEALTH , 32); 
		g.setColor(new Color(246, 250, 250));
		g.drawRect(15, 15, 200, 32);
		
		
		g.drawString("Score: " + score, 14, 69);
		g.drawString("Level: " + level, 14, 89);
		

		Font font = new Font("Courier", 1, 10);
		g.setFont(font);
		
		//int h  = 500;
		/*
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() != ID.TRAIL){
				h += 10;
				g.drawString(tempObject.toString(), 14, h);
			}
			
			
		}
		*/
		g.dispose();
		
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	

}
