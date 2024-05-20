package net.moita.snake.ui;

import lombok.Getter;

import net.moita.snake.util.Cache;

import java.util.Optional;
import java.util.UUID;

public final class Manager {

    @Getter
    private static Cache<GameInstance> instances = new Cache<>();

    public static Optional<GameInstance> getInstanceById(UUID id) {
        return getInstances().findOptional((it) -> it.getPlayer().getUniqueId().equals(id));
    }

    public static void createInstance(GameInstance instance) {
        getInstances().add(instance);
    }

    public static void removeInstance(UUID id) {
        getInstanceById(id).ifPresent((it) -> getInstances().remove(it));
    }

}
