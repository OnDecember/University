package com.javarush.games.minesweeper;

public class GameObject {

    public boolean isMine;
    public boolean isOpen;
    public boolean isFlag;
    public int x;
    public int y;

    public int countMineNeighbors;

    GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }
}