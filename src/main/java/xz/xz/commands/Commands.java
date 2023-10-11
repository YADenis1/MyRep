package xz.xz.commands;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xz.xz.commands.Hello;
import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xz.xz.commands.TeleportBowListener;

public final class Commands extends JavaPlugin {
    private static Commands instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        System.out.println("Plugin Started");
        Bukkit.getPluginManager().registerEvents(new TeleportBowListener(), this);
        BukkitCommandManager mgr = new BukkitCommandManager(this);
        mgr.registerCommand(new Hello());
        mgr.registerCommand(new Food(this));
        mgr.registerCommand(new tpbow());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Plugin getInstance(){
        return instance;
    }
}
