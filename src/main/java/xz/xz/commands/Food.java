package xz.xz.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xz.xz.commands.Commands;
import net.kyori.adventure.text.Component;


@CommandAlias("Food")
public class Food extends BaseCommand{
    private final Commands plugin;
    public Food(Commands plugin) {
        this.plugin = plugin;
    }
    @Default
    public void onFood(CommandSender sender, String[] arguments){
        if (sender instanceof Player player){
            if(arguments.length == 0) {
                final TextComponent textComponent = Component.text("favorite food ", NamedTextColor.RED)
                        .append(Component.text(this.plugin.getConfig().getString("food"), NamedTextColor.BLUE));
                sender.sendMessage(textComponent);
            }else{
                this.plugin.getConfig().set("food", String.join(" ", arguments));
                this.plugin.saveConfig();
                player.sendMessage(Component.text("Favorite food changed ", NamedTextColor.RED));
            }
        }
    }
}