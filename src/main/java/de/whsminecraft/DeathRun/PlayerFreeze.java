package de.whsminecraft.DeathRun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collection;

public class PlayerFreeze implements Listener {
    private static boolean freeze;
    private static PlayerFreeze instance;

    public static void freezePlayers(Collection<? extends Player> players) {
        if (instance == null) {
            instance = new PlayerFreeze();
            Bukkit.getServer().getPluginManager().registerEvents(instance, Main.getInstance());
        }

        freeze = true;

    }

    public static void unfreezePlayers(Collection<? extends Player> players) {
        freeze = false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!freeze)
            return;

        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.DARK_RED + "Du kannst dich gerade leider nicht bewegen");
    }
}
