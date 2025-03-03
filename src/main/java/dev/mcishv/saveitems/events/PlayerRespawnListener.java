package dev.mcishv.saveitems.events;

import dev.mcishv.saveitems.SaveItems;
import dev.mcishv.saveitems.utils.InventoryCache;
import dev.mcishv.saveitems.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerRespawnListener implements Listener {
    private final SaveItems plugin;

    public PlayerRespawnListener(SaveItems plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        ItemStack[] armor = InventoryCache.getSavedArmor(player);
        if (armor != null) {
            player.getInventory().setArmorContents(armor);
        }

        List<ItemStack> savedItems = InventoryCache.getSavedItems(player);
        if (savedItems != null) {
            for (ItemStack item : savedItems) {
                if (!ItemUtils.isArmorPiece(item)) {
                    player.getInventory().addItem(item);
                }
            }
        }

        InventoryCache.clearSavedData(player);
    }
}
