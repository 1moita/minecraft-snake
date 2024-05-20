package net.moita.snake.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@AllArgsConstructor
@Getter @Setter
public final class Coordinate {

    private int x;
    private int y;

    public int toSlot(int width) {
        return getY() * width + getX();
    }

    public boolean equalsCoordinate(Coordinate other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    public static Coordinate getRandom(int width, int height) {
        Random random = new Random();
        return new Coordinate(random.nextInt(width), random.nextInt(height));
    }

}
