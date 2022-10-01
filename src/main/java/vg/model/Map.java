package vg.model;

import vg.model.bonus.Bonus;
import vg.model.entity.Entity;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.Player;
import vg.model.entity.staticEntity.MysteryBox;
import vg.utils.V2D;

import java.util.Set;

/**
 * Interface that defines what a map must do to become usable by {@link Stage}
 * that is containing all the data structures and getters for them, methods
 * for updating the border, computing if an {@link Entity} is captured by
 * the player when creating new borders using {@link Tail}.
 * @param <T> the type that defines positions and dimensionless entities
 * @see V2D
 */
public interface Map<T> {
    /**
     * Will compute and return the percentage of the map that is captured
     * by the player. This is used to check if the level is completed.
     * @return a percentage that indicates how much of the map is captured
     */
    double getOccupiedPercentage();
    //TODO capire come gestire i bordi dai vettori

    /**
     * Return the set of the current borders, that where the player can travel
     * without creating a tail and with the shield active. At the start of each
     * level, the borders will be a Set from (0,0) to (xMax,0) to (xMax,yMax)
     * to (0,yMax) and then will be replaced by the ones created by player's tail.
     * @return a set of positions (usually {@link V2D})
     */
    Set<T> getBorders();

    /**
     * Update the borders of the map. Must call internally {@link #isTailCompleted()}
     * or something that checks is the player tail is actually completed.
     * @param tail the player tail that will become part of the new border
     */
    void updateBorders(Set<T> tail);

    /**
     * Returns the player.
     * @return the player
     */
    Player getPlayer();

    /**
     * Returns the tail that is created by the player. The tail, when not completed,
     * is considered as a Set of {@link Entity} without a {@link vg.utils.Shape} and
     * with the lowest {@link vg.utils.MassTier}. So other entities can walk through
     * the tail without colliding with it.
     * @return the tail that is created by the player
     */
    Set<T> getTail();

    //TODO capire come linkare corretamente T nella documentazione (e se è possibile innanzitutto)
    /**
     * Checks if the tail, generated by the player, is ready to become a border, so if
     * the last {@link T} is colliding with the current border.
     * @return true if the tail is completed and will become a border, false otherwise
     * @see T
     */
    boolean isTailCompleted();

    /**
     * Checks if an entity is captured by the player when he created a new border
     * completing a tail. The Boss of the level cannot be captured (as the boss will
     * be used to compute what part of the map to capture).
     * @param toCheck the entity to check if it is captured or not
     * @return true if the entity must be captured, false otherwise
     */
    boolean toCapture(Entity toCheck);

    /**
     * Removes an entity after being captured or destroyed in some way.
     * @param toRemove the entity to remove
     */
    void removeEntity(Entity toRemove);

    /**
     * Return a list of active bonus collected by player.
     * @return the set of bonuses currently activate
     */
    Set<Bonus> getActiveBonus();

    /**
     * Returns the set of mystery boxes.
     * @return the set of mystery boxes
     * @see MysteryBox
     * @see Bonus
     */
    Set<MysteryBox<Bonus>> getAllMysteryBoxes();

    /**
     * Returns the set of dynamic entities.
     * @return the set of dynamic entities
     */
    Set<DynamicEntity> getAllDynamicEntities();


}
