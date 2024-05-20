package net.moita.snake.ui.view;

import net.moita.snake.ui.Manager;
import net.moita.snake.ui.type.Direction;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public final class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        Manager.getInstanceById(player.getUniqueId()).ifPresent((instance) -> {
            event.setCancelled(true);

            if(item.getType() != Material.SKULL_ITEM || !(item.hasItemMeta())) return;
            switch(event.getRawSlot()) {
                case 48:
                    instance.getSnake().setDirection(Direction.LEFT);
                    break;

                case 50:
                    instance.getSnake().setDirection(Direction.RIGHT);
                    break;

                case 40:
                    instance.getSnake().setDirection(Direction.UP);
                    break;

                case 49:
                    instance.getSnake().setDirection(Direction.DOWN);
                    break;
            }
        });
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Manager.getInstanceById(event.getPlayer().getUniqueId()).ifPresent((instance) -> {
           Manager.removeInstance(instance.getPlayer().getUniqueId());
           event.getPlayer().getInventory().clear();
        });
    }

}
