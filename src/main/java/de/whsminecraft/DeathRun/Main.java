package de.whsminecraft.DeathRun;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    public static Main getInstance() { return instance; }

    public Main() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("deathrun").setExecutor(new Commands());
        getLogger().info("Plugin was successfully enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin was successfully disabled.");
    }
}
