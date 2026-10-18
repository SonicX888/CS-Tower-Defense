package com.mycompany.cs_tower_defense;

/**
 * Interface of everything that can take damage and die
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public interface Damageable {

    /**
     * Decreases the hp by a specific amount.
     *
     * @param amount number of hp to remove
     */
    void takeDamage(int amount);

    /**
     * Checks if there is no hp left.
     *
     * @return true if there is no hp left, false otherwise
     */
    boolean isDead();

}
