package service;

import model.characters.Hero;
import model.characters.Monster;

/**
 * Generic/Parameterized class
 * Contains methods that can be applied to either Hero or Monster
 */
public class Printer <T> {
    T entity;

    public Printer(T entity) {
        this.entity = entity;
    }

    public void print() {
        if (this.entity instanceof Hero) {
            System.out.println("Hero{" +
                    "heroKind=" + ((Hero) entity).getHeroKind() +
                    ", level=" + ((Hero) entity).getLevel() +
                    ", hp=" + ((Hero) entity).getHp() +
                    ", hitRate=" + ((Hero) entity).getHitRate() +
                    '}'
            );
        } else if (this.entity instanceof Monster) {
            System.out.println("Monster{" +
                    "monsterKind=" + ((Monster) entity).getMonsterKind() +
                    ", name=" + ((Monster) entity).getName() +
                    ", fortuneMark=" + ((Monster) entity).getFortuneMark() +
                    ", hp=" + ((Monster) entity).getHp() +
                    ", hitRate=" + ((Monster) entity).getHitRate() +
                    '}'
            );
        }
    }
}
