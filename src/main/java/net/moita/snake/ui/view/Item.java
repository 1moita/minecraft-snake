package net.moita.snake.ui.view;

import lombok.Getter;
import lombok.NonNull;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Item {

    private ItemStack item;

    @NonNull
    private Material type;

    @NonNull
    private int amount;

    private String name;
    private final List<String> description = new ArrayList<>();

    private String player;

    public Item(Material type) {
        this.type = type;
        this.amount = 1;
        this.name = type.toString();
    }

    public Item(Material type, int amount) {
        this.type = type;
        this.amount = amount;
        this.name = type.toString();
    }

    public Item setType(Material type) {
        this.type = type;
        return this;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Item setDisplayName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public Item addDescriptionLine(String line) {
        this.description.add(ChatColor.translateAlternateColorCodes('&', line));
        return this;
    }

    public Item addDescriptionLines(String... lines) {
        for(String line : lines) this.addDescriptionLine(line);
        return this;
    }

    public Item setOwningPlayer(OfflinePlayer player) {
        this.player = player.getName();
        return this;
    }

    public Item setOwningPlayer(String player) {
        this.player = player;
        return this;
    }

    public ItemStack toItem() {
        this.item = new ItemStack(this.type, this.amount);

        ItemMeta meta = this.item.getItemMeta();
        if(meta == null) return this.item;

        if(!(this.name.isEmpty())) meta.setDisplayName(this.name);
        meta.setLore(this.description);

        if(this.player != null && this.type == Material.SKULL_ITEM) {
            this.item.setDurability((short) 3);
            ((SkullMeta) meta).setOwner(this.player);
        }

        this.item.setItemMeta(meta);
        return this.item;
    }

    public static Item createItem(Material type) {
        return new Item(type);
    }

}
