package utils;

import model.characters.Entity;

/**
 * Contains methods for all the entities who can fight
 */
public interface Fighter {

    // takes out a bit of entity's hp
    boolean hit(Entity entity);
}
