package com.oglofus.gringotts.towny.nation;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import org.gestern.gringotts.accountholder.AccountHolder;

/**
 * The type Nation account holder.
 */
public class NationAccountHolder implements AccountHolder {
    public static final String ACCOUNT_TYPE = "nation";
    private final       Nation nation;

    /**
     * Instantiates a new Nation account holder.
     *
     * @param nation the nation
     */
    NationAccountHolder(Nation nation) {
        this.nation = nation;
    }

    /**
     * Return name of the account holder.
     *
     * @return name of the account holder
     */
    @Override
    public String getName() {
        return this.nation.getName();
    }

    /**
     * Send message to the account holder.
     *
     * @param message to send
     */
    @Override
    public void sendMessage(String message) {
        TownyAPI.getInstance().getOnlinePlayers(this.nation).forEach(player -> player.sendMessage(message));
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
        return this.nation.getUUID().toString();
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    /**
     * The nation onwing this account
     * @return nation object
     */
    public Nation getNation() {
        return nation;
    }
}
