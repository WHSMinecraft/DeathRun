package de.whsminecraft.DeathRun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ("deathrun".equals(s)) {
            DeathRun run = new DeathRun();
            run.start();

            return true;
        }

        return false;
    }
}
