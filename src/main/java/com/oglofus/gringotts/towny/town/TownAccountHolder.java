package com.oglofus.gringotts.towny.town;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import org.gestern.gringotts.accountholder.AccountHolder;

/**
 * The type Town account holder.
 */
public class TownAccountHolder implements AccountHolder {
    public static final String ACCOUNT_TYPE = "town";
    private final       Town   town;

    /**
     * Instantiates a new Town account holder.
     *
     * @param town the town
     */
    TownAccountHolder(Town town) {
        this.town = town;
    }

    /**
     * Return name of the account holder.
     *
     * @return name of the account holder
     */
    @Override
    public String getName() {
        return this.town.getName();
    }

    /**
     * Send message to the account holder.
     *
     * @param message to send
     */
    @Override
    public void sendMessage(String message) {
        TownyAPI.getInstance().getOnlinePlayers(this.town).forEach(player -> player.sendMessage(message));
    }

    /**
     * Type of the account holder. For instance "faction" or "player".
     *
     * @return account holder type
     */
    @Override
    public String getType() {
        return ACCOUNT_TYPE;
    }

    /**
     * A unique identifier for the account holder.
     * For players, this is simply the name. For factions, it is their id.
     *
     * @return unique account holder id
     */
    @Override
    public String getId() {
        return this.town.getUUID().toString();
    }

    /**
     * The town onwing this account
     * @return town object
     */
    public Town getTown() {
        return this.town;
    }
}
