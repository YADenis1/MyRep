package xz.xz.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
@CommandAlias("Hi")
public class Hello extends BaseCommand{
    @Default
    public void onHello(CommandSender sender){
        if (sender instanceof Player player){
            player.sendMessage("Hello, " + player.getName());
        }
    }
}