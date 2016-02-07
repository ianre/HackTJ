package com.game.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {

		this.handler = handler;
		this.hud = hud;

	}
	public int spawnX(){
		return ( 10 + r.nextInt(Game.WIDTH - 21));
	}
	public int spawnY(){
		return  ( 10 + r.nextInt(Game.HEIGHT - 21));
	}

	public void tick() {
		
		scoreKeep++;

		if (scoreKeep >= 300) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if (hud.getLevel() == 2) {
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				

			}
			if (hud.getLevel() == 3) {
				
				handler.killEnemies();
				handler.addObject(new E_Fast3(spawnX(),spawnY(), ID.E_FAST3, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));

			}
			
			if (hud.getLevel() == 5) {

				handler.killEnemies();
				handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
				handler.addObject(new E_Fast2(spawnX(),spawnY(), ID.E_FAST2, handler));
				handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
				handler.addObject(new E_Fast2(spawnX(),spawnY(), ID.E_FAST2, handler));
				handler.addObject(new E_Hoaming1(1, Game.HEIGHT / 2, ID.E_HOAMING1, handler));
				handler.addObject(new E_Hoaming1(Game.WIDTH -50,Game.HEIGHT/ 2, ID.E_HOAMING1, handler));
			}
			
			if (hud.getLevel() == 7) {
				handler.killEnemies();
				
				handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
				
				handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
				
				
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler));
				
				int temp = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 15; j++) {
					for (int i = 0; i < 15; i++) {
						handler.addObject(
								new E_Hoaming1(temp + temp * j - 1400, temp + temp * i - 1400, ID.E_HOAMING1, handler));
					}
				}
			}
			if(hud.getLevel() == 8){
				int temp = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 15; j++) {
					for (int i = 0; i < 15; i++) {
						handler.addObject(
								new E_Hoaming1(temp + temp * j + 1400, temp + temp * i - 1400, ID.E_HOAMING1, handler));
					}
				}
				
			}
			if(hud.getLevel() == 9){
				int temp = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 15; j++) {
					for (int i = 0; i < 15; i++) {
						handler.addObject(
								new E_Hoaming1(temp + temp * j + 1400, temp + temp * i + 1400, ID.E_HOAMING1, handler));
					}
				}
			}
			if(hud.getLevel() == 12){
				handler.killEnemies();
				for(int i = 0; i < 20; i ++){
					handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
				}
				handler.addObject(new E_Hoaming1(spawnX(),spawnY(), ID.E_HOAMING1, handler));
				
				
			}
			if(hud.getLevel() == 16){
				handler.setWall();
				handler.killEnemies();
				
				int temp = (int) (Game.HEIGHT / 15);
				for (int j = 0; j < 40; j++) {
					for (int i = 0; i < 15; i++) {
						handler.addObject(
								new E_BasicHorde(temp * j - 700, temp * i , ID.E_BASICH, handler));
					}
				}
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 19; j++) {
					for (int i = 0; i < 21; i++) {
						handler.addObject(
								new E_Hoaming1(temp2 + temp2 * j + 1400, temp2 + temp2 * i + 1400, ID.E_HOAMING1, handler));
					}
				}
				
			}
			if(hud.getLevel() == 20){
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 19; j++) {
					for (int i = 0; i < 21; i++) {
						handler.addObject(
								new E_Hoaming1(temp2 + temp2 * j - 1400, temp2 + temp2 * i, ID.E_HOAMING1, handler));
					}
				}
			}
			if(hud.getLevel() == 22){
				handler.killEnemies();
				
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 10; j++) {
					for (int i = 0; i < 11; i++) {
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j - 1000, temp2 + temp2 * i , ID.E_HOAMING1, handler));
					}
				}
				
			} 
			if(hud.getLevel() == 25){
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 10; j++) {
					for (int i = 0; i < 11; i++) {
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j + 900 , temp2 + temp2 * i , ID.E_HOAMING1, handler));
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j , temp2 + temp2 * i + 1000, ID.E_HOAMING1, handler));
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j  , temp2 + temp2 * i - 1000, ID.E_HOAMING1, handler));
					}
				}
			}
			if(hud.getLevel() == 27){
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 10; j++) {
					for (int i = 0; i < 11; i++) {
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j , temp2 + temp2 * i + 1000, ID.E_HOAMING1, handler));
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j  , temp2 + temp2 * i - 1000, ID.E_HOAMING1, handler));
					}
				}
			}
			if(hud.getLevel() == 29){
				int temp2 = (int) (Game.HEIGHT / 13);
				for (int j = 0; j < 10; j++) {
					for (int i = 0; i < 11; i++) {
						handler.addObject(new E_Hoaming1(temp2 + temp2 * j  , temp2 + temp2 * i - 1000, ID.E_HOAMING1, handler));
					}
				}
			}
			/*
			if(hud.getLevel() > 22 && hud.getLevel() % 2 == 0){
				handler.killEnemies();
				handler.addObject(new E_S_Boss(Game.WIDTH / 2-50, -190, ID.E_S_BOSS, handler));
			}
			*/
			if(hud.getLevel() == 32){
				handler.killEnemies();
				handler.addObject(new E_S_Boss(Game.WIDTH / 2-150, -190, ID.E_S_BOSS, handler));
				handler.addObject(new E_S_Boss(Game.WIDTH / 2+150, -190, ID.E_S_BOSS, handler));
			}
			if(hud.getLevel() == 36){
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.id == ID.SENTINEL) {
						Sentinel s = (Sentinel) tempObject;
						s.end = true;
						//handler.removeObject(s);
					}
				}
			}
			
		}
		
	}

	public void spawnRoom(int diff){
		handler.killEnemies();
		for (int i = 0; i < 5; i ++){
			if(randomFromPercentage(50)) handler.addObject(new E_Basic1(spawnX(),spawnY(), ID.E_BASIC1, handler)); 
		}
		for (int i = 0; i < 5; i ++){
			if(randomFromPercentage(50)) handler.addObject(new E_Fast1(spawnX(),spawnY(), ID.E_FAST1, handler));
			else handler.addObject(new E_Fast2(spawnX(),spawnY(), ID.E_FAST2, handler));
		}
		if(randomFromPercentage(50)) handler.addObject(new E_Fast3(spawnX(),spawnY(), ID.E_FAST3, handler));
		else {
			int temp = (int) (Game.HEIGHT / 13);
			for (int j = 0; j < 19; j++) {
				for (int i = 0; i < 21; i++) {
					handler.addObject(
							new E_Hoaming1(temp + temp * j - 1400, temp + temp * i - 1400, ID.E_HOAMING1, handler));
				}
			}
		}
		if(randomFromPercentage(1)) handler.addObject(new E_S_Boss(Game.WIDTH / 2-50, -190, ID.E_S_BOSS, handler));
	}
	public boolean randomFromPercentage(int x){
		if(r.nextInt(100) < x) return true;
		else return false;
	}

}
