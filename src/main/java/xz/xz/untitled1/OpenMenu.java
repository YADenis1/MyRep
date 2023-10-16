package xz.xz.untitled1;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@CommandAlias("Shop")
public class OpenMenu extends BaseCommand {
    public void ConfirmBye(Player player, String material, int num, int cost){
        ItemStack itemget = new ItemStack(Material.valueOf(material));

        ItemMeta Metaget = itemget.getItemMeta();
        Metaget.displayName(Component.text(material + " + ")
                .append(Component.text(num))
                .color(NamedTextColor.GREEN)
                .append(Component.text(" Diamonds - " + cost).color(NamedTextColor.RED))
        );


        itemget.setItemMeta(Metaget);

        ItemStack itemdecline = new ItemStack(Material.RED_WOOL);

        ItemMeta Metadecline = itemget.getItemMeta();
        Metadecline.displayName(Component.text("DECLINE")
                .color(NamedTextColor.RED)
        );
        itemdecline.setItemMeta(Metadecline);

        ItemStack itemconfirm = new ItemStack(Material.GREEN_WOOL);

        ItemMeta Metaconfirm = itemget.getItemMeta();
        Metaconfirm.displayName(Component.text("CONFIRM")
                .color(NamedTextColor.GREEN)
        );
        itemconfirm.setItemMeta(Metaconfirm);


        Gui GIU = Gui.gui()
                .title(Component.text("APPLY?!"))
                .type(GuiType.HOPPER)
                .create();

        GuiItem guiItem4 = ItemBuilder.from(itemconfirm).asGuiItem(event -> {
            event.setCancelled(true);
            ItemStack coins = new ItemStack(Material.DIAMOND, cost);
            player.getInventory().removeItem(coins);
            ItemStack giveItem = new ItemStack(Material.valueOf(material), num);
            player.getInventory().addItem(giveItem);
            GIU.close(player);

        });

        GuiItem guiItem1 = ItemBuilder.from(itemdecline).asGuiItem(event -> {
            event.setCancelled(true);
            GIU.close(player);
        });

        GuiItem guiItem3 = ItemBuilder.from(itemget).asGuiItem(event -> {
            event.setCancelled(true);
        });
        GIU.setItem(0, guiItem1);
        GIU.setItem(4, guiItem4);
        GIU.setItem(2, guiItem3);
        System.out.println("trying to open confirm menu");
        GIU.open(player);
    }
    @Default
    public void onOpen(CommandSender sender) {
        Gui gui = Gui.gui()
                .title(Component.text("SHOP!"))
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

                    ItemStack item = new ItemStack(Material.valueOf(material));
                    ItemMeta Meta = item.getItemMeta();
                    Meta.displayName(Component.text(material + " >> ").color(NamedTextColor.GREEN)
                            .append(Component.text(" AMOUNT >> " + num).color(NamedTextColor.AQUA))
                            .append(Component.text(" COST >> " + cost).color(NamedTextColor.RED))
                            );
                    item.setItemMeta(Meta);

                    String finalMaterial2 = material;
                    int finalNum1 = num;
                    int finalCost1 = cost;
                    GuiItem guiItem = ItemBuilder.from(item).asGuiItem(event -> {

                        System.out.println("get Material succesful");
                        event.setCancelled(true);
                        if(player.getInventory().contains(Material.DIAMOND, finalCost)){
                            ConfirmBye(player, finalMaterial2, finalNum1, finalCost1);
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
