package net.moita.snake.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.moita.snake.ui.type.Direction;

@AllArgsConstructor
@Getter @Setter
public final class Snake {

    private List<Coordinate> body;
    private Coordinate food;

    private Direction direction;
    private int points;

    public boolean moveTo() {
        Coordinate head = new Coordinate(getHead().getX(), getHead().getY());

        switch(this.direction) {
            case LEFT:
                head.setX(head.getX() - 1);
                break;

            case RIGHT:
                head.setX(head.getX() + 1);
                break;

            case UP:
                head.setY(head.getY() - 1);
                break;

            case DOWN:
                head.setY(head.getY() + 1);
                break;
        }

        if(head.getX() < 0
                || head.getX() > 8
                || head.getY() < 0
                || head.getY() > 3) {
            return true;
        } else if(this.body.contains(head)) {
            return true;
        } else {
            this.body.add(0, head);
            if(head.equalsCoordinate(this.food)) {
                this.food = this.generateFood();
            } else {
                this.body.remove(this.body.size() - 1);
            }
        }

        return false;
    }

    private Coordinate generateFood() {
        Coordinate food;
        do {
            food = Coordinate.getRandom(9, 4);
        } while(this.body.contains(food));
        return food;
    }

    private Coordinate getHead() {
        return this.body.get(0);
    }

    public static Snake withDefaults() {
        return new Snake(
                new ArrayList<>(Arrays.asList(new Coordinate(1, 0))),
                Coordinate.getRandom(9, 4),
                Direction.RIGHT,
                0);
    }

}
