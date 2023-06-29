package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class RoadManager {

    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;

    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    private RoadObject  createRoadObject(RoadObjectType type, int x, int y) {
        RoadObject manager = null;
        if (type == RoadObjectType.THORN) {
            manager = new Thorn(x, y);
        } else if (type == RoadObjectType.DRUNK_CAR) {
            manager = new MovingCar(x, y);
        } else {
            manager = new Car(type, x, y);
        }
        return manager;
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }

    }

    public void draw(Game game) {
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject item : items) {
            item.move(boost + item.speed, items);
        }
        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject item : items) {
            if (item.type == RoadObjectType.THORN) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int probability = game.getRandomNumber(100);
        if (probability < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        ArrayList<RoadObject> copy = new ArrayList<>(items);
        for (RoadObject item : copy) {
            if (item.y >= RacerGame.HEIGHT) {
                items.remove(item);
                if (item.type != RoadObjectType.THORN) {
                    passedCarsCount++;
                }
            }
        }
    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (RoadObject item : items) {
            if (item.isCollision(playerCar)) {
                return true;
            }
        }
        return false;
    }

    private void generateRegularCar(Game game) {
        int probability = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (probability < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMovingCarExists() {
        for (RoadObject item : items) {
            if (item instanceof MovingCar) {
                return true;
            }
        }
        return false;
    }

    private void generateMovingCar(Game game) {
        int probability = game.getRandomNumber(100);
        if (probability < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    public int getPassedCarsCount() {
        return passedCarsCount;
    }


}
