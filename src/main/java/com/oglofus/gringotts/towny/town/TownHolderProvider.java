package com.oglofus.gringotts.towny.town;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyObject;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.gestern.gringotts.AccountChest;
import org.gestern.gringotts.Gringotts;
import org.gestern.gringotts.GringottsAccount;
import org.gestern.gringotts.accountholder.AccountHolder;
import org.gestern.gringotts.accountholder.AccountHolderProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Town holder provider.
 */
public class TownHolderProvider implements AccountHolderProvider, Listener {
    /**
     * Get the AccountHolder object mapped to the given id for this provider.
     *
     * @param id id of account holder
     * @return account holder for id
     */
    @Override
    public @Nullable AccountHolder getAccountHolder(@NotNull String id) {
        try {
            UUID targetUuid = UUID.fromString(id);

            return getAccountHolder(targetUuid);
        } catch (IllegalArgumentException ignored) {}

        String vaultPrefix = TownAccountHolder.ACCOUNT_TYPE + "-";

        Town town;

        if (id.startsWith(vaultPrefix)) {
            town = TownyUniverse.getInstance().getTown(id.substring(vaultPrefix.length()));
        } else {
            town = TownyUniverse.getInstance().getTown(id);
        }

        return getAccountHolder(town);
    }

    /**
     * Get the AccountHolder object mapped to the given id for this provider.
     *
     * @param uuid id of account holder
     * @return account holder for id
     */
    @Override
    public @Nullable AccountHolder getAccountHolder(@NotNull UUID uuid) {
        Town town = TownyUniverse.getInstance().getTown(uuid);

        return getAccountHolder(town);
    }

    /**
     * Get a TownyAccountHolder for the town of which player is a resident, if any.
     *
     * @param player player to get town for
     * @return TownyAccountHolder for the town of which player is a resident, if
     * any. null otherwise.
     */
    @Override
    public @Nullable AccountHolder getAccountHolder(@NotNull OfflinePlayer player) {
        try {
            Resident resident = TownyUniverse.getInstance().getResident(player.getUniqueId());

            if (resident == null) {
                return null;
            }

            Town town = resident.getTown();

            return getAccountHolder(town);
        } catch (NotRegisteredException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    @Override
    public @NotNull String getType() {
        return TownAccountHolder.ACCOUNT_TYPE;
    }

    /**
     * Gets account names.
     *
     * @return the account names
     */
    @Override
    public @NotNull Set<String> getAccountNames() {
        return TownyUniverse.getInstance().getTowns().stream().map(TownyObject::getName).collect(Collectors.toSet());
    }

    /**
     * Gets account holder.
     *
     * @param town the town
     * @return the account holder
     */
    public @Nullable AccountHolder getAccountHolder(@Nullable Town town) {
        if (town == null) {
            return null;
        }

        return new TownAccountHolder(town);
    }

    /**
     * Rename town.
     *
     * @param event the event
     */
    @EventHandler
    public void renameTown(RenameTownEvent event) {
        Town town = event.getTown();

        AccountHolder holder = getAccountHolder(town);

        if (holder == null) {
            return;
        }

        GringottsAccount account = Gringotts.instance.getAccounting().getAccount(holder);

        if (account == null) {
            return;
        }

        Gringotts.instance.getDao().retrieveChests(account).forEach(AccountChest::updateSign);
    }
}
