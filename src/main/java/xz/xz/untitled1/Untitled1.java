package xz.xz.untitled1;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import xz.xz.untitled1.COMMANDS.OpenGuiShop;

public final class Untitled1 extends JavaPlugin {
    private static Untitled1 instance;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        Untitled1.getInstance().getLogger().info("Plugin Starting!!!");
        // Plugin startup logic
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.registerCommand(new OpenGuiShop());
        Untitled1.getInstance().getLogger().info("Plugin Working!!!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Untitled1 getInstance() {
        return instance;
    }
}
