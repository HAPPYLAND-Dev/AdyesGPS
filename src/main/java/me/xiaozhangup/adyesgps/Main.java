package me.xiaozhangup.adyesgps;

import ink.ptms.adyeshach.api.AdyeshachAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Main() , this);
        Bukkit.getPluginCommand("adyesgps").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            Inventory inventory = Bukkit.createInventory(new Holder() , 45 , "NPC导航菜单");

            ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta boardMeta = board.getItemMeta();
            boardMeta.setDisplayName(" ");
            board.setItemMeta(boardMeta);

            for (int i = 0; i < 9; i++) {
                inventory.setItem(i, board);
            }
            for (int i = 9; i < 45; i++) {
                inventory.setItem(i, board);
            }

            getConfig().set();
            return true;
        });
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player player) {
            if (e.getInventory().getHolder() instanceof Holder) {
                e.setCancelled(true);
                if (e.getRawSlot() = 1) {
                    //TODO
                }
            }
        }
    }
}
