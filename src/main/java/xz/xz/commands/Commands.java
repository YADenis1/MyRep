package xz.xz.commands;

import org.bukkit.plugin.java.JavaPlugin;
import xz.xz.commands.Hello;
import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Commands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Plugin Started");
        BukkitCommandManager mgr = new BukkitCommandManager(this);
        mgr.registerCommand(new Hello());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
