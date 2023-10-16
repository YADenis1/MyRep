package xz.xz.untitled1;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

@CommandAlias("Shop")
public class OpenMenu extends BaseCommand {
    @Default
    public void onOpen(CommandSender sender) {
        Gui gui = Gui.gui()
                .title(Component.text("GUI Title!"))
                .rows(6)
                .create();


        if (sender instanceof Player player) {
            var freeSlot = 0;

            String info = Untitled1.getInstance().getConfig().getString("items");
            List<String> myList = new ArrayList<String>(Arrays.asList(info.split(",")));
            var g =-1;
            String material = null;
            int cost = 0;
            int num = 0;
            for(String element: myList){
                g++;
                String reElement = element.replace("[", "").replace("{", "").replace("}", "").replace("]", "");
                List<String> number = new ArrayList<String>(Arrays.asList(reElement.split("=")));
                if(g==0){
                    material = number.get(1).toString();
                }
                else if(g==1){
                    cost = Integer.valueOf(number.get(1));
                }
                else if(g==2){
                    num = Integer.valueOf(number.get(1));
                    var finalCost = cost;
                    g=0;
                    String finalMaterial = material;
                    var finalNum = num;
                    String finalMaterial1 = material;
                    GuiItem guiItem = ItemBuilder.from(Material.valueOf(material)).asGuiItem(event -> {
                        System.out.println("get Material succesful");
                        event.setCancelled(true);
                        if(player.getInventory().contains(Material.DIAMOND, finalCost)){
                            ItemStack coins = new ItemStack(Material.DIAMOND, finalCost);
                            player.getInventory().removeItem(coins);
                            ItemStack giveItem = new ItemStack(Material.valueOf(finalMaterial1));
                            player.getInventory().addItem(giveItem);
                        }
                        else {
                            player.sendMessage("Not Enough Diamonds");
                        }
                    });
                    gui.setItem(freeSlot, guiItem);
                    freeSlot++;
                    g =-1;
                    material = null;
                    cost = 0;
                    num = 0;
                    finalNum = 0;
                    finalMaterial = null;
                }

            }

            GuiItem guiItem = ItemBuilder.from(Material.DIAMOND).asGuiItem(event -> {
                event.setCancelled(true);
                ItemStack predmet = new ItemStack(Material.DIAMOND, 64);
                player.getInventory().addItem(predmet);
            });
            gui.setItem(freeSlot, guiItem);
            player.sendMessage("Привет, " + player.getName() + "!");
            gui.open(player);
        } else {
            sender.sendMessage("Команда может быть выполнена только оператором, не консолью");
        }
    }
}
