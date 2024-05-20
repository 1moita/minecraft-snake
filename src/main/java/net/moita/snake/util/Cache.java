package net.moita.snake.util;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class Cache<T> extends ArrayList<T> {

    public T find(Predicate<T> it) {
        for(T value : this)
            if(it.test(value)) return value;
        return null;
    }

    public Optional<T> findOptional(Predicate<T> it) {
        return Optional.ofNullable(this.find(it));
    }

}
