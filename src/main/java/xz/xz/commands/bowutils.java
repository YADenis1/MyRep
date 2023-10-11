package xz.xz.commands;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class bowutils {
    public static ItemStack createTeleportBow(){
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.setDisplayName("TPBOW");

        List<String> lore = new ArrayList<>();
        lore.add("tp you to landing pos");
        bowMeta.setLore(lore);

        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);

        bowMeta.getPersistentDataContainer().set(NamespacedKey.fromString("is_telepot_bow"), PersistentDataType.STRING, "0");

        bow.setItemMeta(bowMeta);

        return bow;
    }
}