package xz.xz.untitled1.COMMANDS;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xz.xz.untitled1.SHOP.ShopRender;

@CommandAlias("Shop")
public class OpenGuiShop extends BaseCommand {
    @Default
    public void OnOpen(CommandSender sender){
        if (sender instanceof Player player) {
            ShopRender render = new ShopRender();
            render.onOpen(player);
        }
    }
}
