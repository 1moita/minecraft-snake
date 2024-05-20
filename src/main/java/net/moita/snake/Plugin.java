package net.moita.snake;

import net.moita.snake.ui.GameInstance;
import net.moita.snake.ui.Manager;
import net.moita.snake.ui.task.RenderingTask;
import net.moita.snake.ui.view.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Listener(), this);

        getCommand("snake").setExecutor((sender, command, args, ignored) -> {
            if(!(sender instanceof Player)) return true;
            Player player = (Player) sender;

            GameInstance instance = new GameInstance(player);
            instance.renderContent();
            player.openInventory(instance.getInventory());

            Manager.createInstance(instance);
            return true;
        });

        Bukkit.getScheduler().runTaskTimerAsynchronously(getInstance(), new RenderingTask(), 20L, 20L);
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }

}