package model.characters;

import java.util.Random;

public abstract class Entity {

    private final int hp;
    private int hitRate;

    public Entity(int hp, int hitRate) {
        this.hp = hp;
        this.hitRate = hitRate;
    }

    public int getRandom() {
        return new Random().nextInt(hitRate);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getHp() {
        return hp;
    }

    public int getHitRate() {
        return hitRate;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "hp=" + hp +
                ", hitRate=" + hitRate +
                '}';
    }
}
