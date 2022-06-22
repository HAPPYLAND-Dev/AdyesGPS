package me.xiaozhangup.adyesgps;

import com.live.bemmamin.gps.api.GPSAPI;
import ink.ptms.adyeshach.api.AdyeshachAPI;
import me.xiaozhangup.adyesgps.tools.IString;
import me.xiaozhangup.adyesgps.tools.Skull;
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

    private GPSAPI gpsapi = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        gpsapi = new GPSAPI(this);
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
            for (int i = 36; i < 45; i++) {
                inventory.setItem(i, board);
            }

            for (int i = 9; i < 36; i++) {
                if (getConfig().getString(i + ".loc") != null) {
                    inventory.setItem(i , Skull.getSkull(getConfig().getString(i + ".skull"), IString.addColor(getConfig().getString(i + ".name")) , " " , IString.addColor("&e点击前往")));
                }
            }

            p.openInventory(inventory);

            return true;
        });
        Bukkit.getPluginCommand("adyesgps").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (!p.isOp()) return false;
            getConfig().set(inside[0] + "loc" , p.getLocation());
            getConfig().set(inside[0] + "skull" , "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY4ZDM0NTQ3YTZiYjZiNjhjYjcyN2FmMzcxNDk5ZTdjMDQzNjdmOWUwNDE5NzFkMWI1YWUyMWEyZGQwMTIwOSJ9fX0=");
            getConfig().set(inside[0] + "name" , "Path: " + inside[0]);
            saveConfig();
            reloadConfig();
            p.sendMessage("Done");
            return true;
        });
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player player) {
            if (e.getInventory().getHolder() instanceof Holder) {
                e.setCancelled(true);
                int i = e.getRawSlot() - 8;
                if (getConfig().getLocation(i + ".loc") != null) {
                    gpsapi.startCompass(player , getConfig().getLocation(i + ".loc"));
                }
            }
        }
    }
}
