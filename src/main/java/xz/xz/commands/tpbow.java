package xz.xz.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xz.xz.commands.Commands;
import net.kyori.adventure.text.Component;


@CommandAlias("bow")
public class tpbow extends BaseCommand{
    @Default
    public void onComma(CommandSender sender, String[] arguments){
        if (sender instanceof Player player){
            if (arguments.length == 0) {
                ItemStack tpbow = bowutils.createTeleportBow();
                player.getInventory().addItem(tpbow);
                player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                player.sendMessage(ChatColor.GREEN + "You got a TPBOW!!!");
            } else {
                Player target = Bukkit.getPlayerExact(arguments[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Target player not exists");
                }
                ItemStack tpbow = bowutils.createTeleportBow();
                target.getInventory().addItem(tpbow);
                target.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                target.sendMessage(ChatColor.GREEN + "You got a TPBOW!!!");
            }
        }
    }
}