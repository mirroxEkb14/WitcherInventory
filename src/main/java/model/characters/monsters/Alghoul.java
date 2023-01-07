package model.characters.monsters;

import model.characters.Monster;
import model.characters.kinds.creatures.Necrophage;
import model.characters.kinds.entities.MonsterKind;

public class Alghoul extends Monster {

    private static final int MIN_HP = 750;
    private static final int MIN_HIT_RATE = 395;
    private static final int FORTUNE_MARK = 7;

    public Alghoul(MonsterKind monsterKind) {
        super(monsterKind, Necrophage.ALGHOUL.toString(), MIN_HIT_RATE, MIN_HP, FORTUNE_MARK);
    }
}
