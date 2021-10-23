package de.whsminecraft.DeathRun;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class DeathRun implements Listener {
    private Collection<? extends Player> players;

    public void start() {
        // new world
        Bukkit.getServer().broadcastMessage("Creating new World...");
        long seed = new Random().nextLong();
        String worldName = "deathrun_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        Main.getInstance().getLogger().info("New world seed is: " + seed);
        World w = Bukkit.getServer().createWorld(new WorldCreator(worldName).seed(seed));

        w.setGameRule(GameRule.NATURAL_REGENERATION, false);

        players = Bukkit.getServer().getOnlinePlayers();

        final Location center = w.getSpawnLocation();

        for (Player p : players) {
            p.teleport(center);
        }


        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable()
        {
            int time = 1 * 60; // or any other number you want to start countdown from

            @Override
            public void run()
            {
                if (time < 0) {
                    Bukkit.getScheduler().cancelTasks(Main.getInstance());
                    return;
                } else if (time == 0)
                {
                    double maxD = 0;
                    Player maxP = null;
                    for (final Player player : players)
                    {
                        double d = center.distance(player.getLocation());
                        if (d > maxD) {
                            maxD = d;
                            maxP = player;
                        }
                    }

                    if (maxP == null) {
                        Bukkit.getServer().broadcastMessage("Niemand hat gewonnen. Seltsam...");
                        return;
                    }
                    Player winner = maxP;
                    Bukkit.getServer().broadcastMessage(String.format(ChatColor.GREEN + "Sieger ist `%s´! Distanz erreicht: %.2fm", winner.getDisplayName(), maxD));

                    for (final Player player : players)
                    {
                        player.remove();
                    }
                    w.save();
                } else if (time > 0) {
                    if (time % 60 == 0) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Noch " + time / 60 + " Minuten!");
                    } else if (time <= 30) {
                        Bukkit.broadcastMessage((time <= 10 ? ChatColor.RED : ChatColor.GOLD) + "Noch " + time + " Sekunden");
                    }
                }

                time--;
            }
        }, 0L, 20L);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

    }
}
