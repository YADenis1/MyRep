package xz.xz.untitled1.SHOP;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.components.ScrollType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.ScrollingGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xz.xz.untitled1.Untitled1;

import java.util.*;

public class ShopRender{
    private Material CoinMaterial = Material.valueOf(Untitled1.getInstance().getConfig().getString("CoinsMaterial"));
    private int skip =0;
    public void ConfirmBye(Player player, String material, int num, int cost){
        ItemStack itemget = new ItemStack(Material.valueOf(material));

        ItemMeta Metaget = itemget.getItemMeta();
        Metaget.displayName(Component.text(material + " + ")
                .append(Component.text(num))
                .color(NamedTextColor.GREEN)
                .append(Component.text(Untitled1.getInstance().getConfig().getString("CoinsMaterial") + " " + cost).color(NamedTextColor.RED))
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
            ItemStack coins = new ItemStack(CoinMaterial, cost);
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
        GIU.open(player);
    }
    public void onOpen(CommandSender sender) {
        ScrollingGui gui = Gui.scrolling()
                .title(Component.text("SHOP!"))
                .rows(6)
                .pageSize(45)
                .scrollType(ScrollType.valueOf(Untitled1.getInstance().getConfig().getString("ScrollType")))
                .create();
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Команда может быть выполнена только оператором, не консолью");
            return;
        }

        String info = Untitled1.getInstance().getConfig().getString("items");
        List<String> myList = new ArrayList<String>(Arrays.asList(info.split(",")));
        var g =-1;
        String material = null;
        int cost = 0;
        int num = 0;
        int count = 0;
        int localskip = skip;
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName("Previous").asGuiItem(event -> {
            event.setCancelled(true);
            if(skip >2){
                skip = skip-3;
                onOpen(player);
            }
        }));
// Next item
        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName("Next").asGuiItem(event ->
                {gui.next();
                    event.setCancelled(true);
                    skip = skip+3;
                    onOpen(player);}
        ));

        int PredmetsToOut = Untitled1.getInstance().getConfig().getInt("ItemsToOut");

        for(String element: myList){
            if(localskip != 0){
                localskip-=1;
                continue;
            }
            count++;
            if(count>PredmetsToOut*3) {
                break;
            }
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
                        event.setCancelled(true);
                        if(player.getInventory().contains(CoinMaterial, finalCost)){
                            ConfirmBye(player, finalMaterial2, finalNum1, finalCost1);
                        }
                        else {
                            player.sendMessage("Not Enough " + Untitled1.getInstance().getConfig().getString("CoinsMaterial"));
                        }
                });

                gui.addItem(guiItem);
                g =-1;
                material = null;
                cost = 0;
                num = 0;
            }

        }

            GuiItem guiItem = ItemBuilder.from(CoinMaterial).asGuiItem(event -> {
                event.setCancelled(true);
                ItemStack predmet = new ItemStack(CoinMaterial, 64);
                player.getInventory().addItem(predmet);
            });
            gui.addItem(guiItem);
            gui.open(player);
    }
}
