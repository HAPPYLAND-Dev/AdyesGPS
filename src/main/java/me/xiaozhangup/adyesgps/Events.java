package me.xiaozhangup.adyesgps;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.xiaozhangup.adyesgps.Main.gpsapi;

public class Events implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player player) {
            if (e.getInventory().getHolder() instanceof Holder) {
                e.setCancelled(true);
                int i = e.getRawSlot() - 8;
                if (Main.plugin.getConfig().getLocation(i + ".loc") != null) {
                    gpsapi.startCompass(player , Main.plugin.getConfig().getLocation(i + ".loc"));
                    player.closeInventory();
                }
            }
        }
    }
}
