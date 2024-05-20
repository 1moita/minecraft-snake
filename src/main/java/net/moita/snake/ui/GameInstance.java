package net.moita.snake.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.moita.snake.entity.Coordinate;
import net.moita.snake.entity.Snake;
import net.moita.snake.ui.view.Item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@RequiredArgsConstructor @Getter
public final class GameInstance {

    private final Player player;
    private Inventory inventory;

    private final Snake snake = Snake.withDefaults();

    public void renderContent() {
        if(this.inventory == null)
            this.inventory = Bukkit.createInventory(this.player, 6 * 9, "Snake");
        this.inventory.clear();

        this.inventory.setItem(snake.getFood().toSlot(9), Item.createItem(Material.APPLE).toItem());
        for(Coordinate coordinate : snake.getBody()) this.inventory.setItem(coordinate.toSlot(9), Item.createItem(Material.STONE).toItem());

        this.inventory.setItem(40, Item.createItem(Material.SKULL_ITEM).setOwningPlayer("MHF_ArrowUp").setDisplayName("&eW").toItem());
        this.inventory.setItem(48, Item.createItem(Material.SKULL_ITEM).setOwningPlayer("MHF_ArrowLeft").setDisplayName("&eA").toItem());
        this.inventory.setItem(49, Item.createItem(Material.SKULL_ITEM).setOwningPlayer("MHF_ArrowDown").setDisplayName("&eS").toItem());
        this.inventory.setItem(50, Item.createItem(Material.SKULL_ITEM).setOwningPlayer("MHF_ArrowRight").setDisplayName("&eD").toItem());
    }

}
