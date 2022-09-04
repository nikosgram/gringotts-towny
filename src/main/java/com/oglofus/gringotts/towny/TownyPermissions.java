package com.oglofus.gringotts.towny;

import org.bukkit.entity.Player;

/**
 * The Permissions.
 */
public enum TownyPermissions {
    /**
     * Create vault town permissions.
     */
    CREATE_VAULT_TOWN("gringotts.createvault.town"),
    /**
     * Create vault nation permissions.
     */
    CREATE_VAULT_NATION("gringotts.createvault.nation");

    /**
     * The Node.
     */
    public final String node;

    TownyPermissions(String node) {
        this.node = node;
    }

    /**
     * Check if a player has this permission.
     *
     * @param player player to check
     * @return whether given player has this permission
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isAllowed(Player player) {
        return player.hasPermission(this.node);
    }
}
