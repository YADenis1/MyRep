package xz.xz.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;

public class TeleportBowListener implements Listener {
    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        if (!(event.getEntityType() == EntityType.ARROW)) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player player)) {
            return;
        }
        if (player.getInventory()
                .getItemInMainHand()
                .getItemMeta()
                .getPersistentDataContainer()
                .get(NamespacedKey.fromString("is_telepot_bow"),
                        PersistentDataType.STRING) == null) {
            return;
        }
        Location location = event.getEntity().getLocation();

        player.teleport(location);

        event.getEntity().remove();

        player.sendMessage(Component.text("teleport working"));
    }
}
