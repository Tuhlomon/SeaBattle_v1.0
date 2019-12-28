package org.suai.model;

import java.util.Random;

public class Master {
    private int condition = 0; //0-player's move, 1-enemy
    private Random rand = new Random(System.currentTimeMillis());
    private Field myField = new Field();
    private Field enemyField = new Field();
    private int[] enemymtx = new int[100];
    int hor = 0;
    int prevX = 0;
    int prevY = 0;
    int resp = 2;
    int mind = 0;

    public Master(){
        while(!myField.isReady()){
            myField.setShip(rand.nextInt(10), rand.nextInt(10), 1+rand.nextInt(4), rand.nextInt(2));
        }
    }

    public void getRecoil(int r){
        if (r == 4) enemyField.setMarkers(prevX, prevY);
        else enemyField.setMarker(prevX, prevY, r);
    }

    public int doShot(){
        prevX = rand.nextInt(10);
        prevY = rand.nextInt(10);
        while (!enemyField.isFreeCell(prevX, prevY)){
            prevX = rand.nextInt(10);
            prevY = rand.nextInt(10);
        }
        return prevX*10+prevY;
    }

    public int getShot(int s){
        return myField.getShot(s);
    }

}
