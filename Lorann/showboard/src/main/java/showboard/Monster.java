package showboard;

import java.awt.Point;
import java.util.Random;
import showboard.IPawn;

public class Monster extends Tile implements IPawn {

   
    private Point        position;

    private final Random random = new Random();

    public Monster(final String imageName) {
        super(imageName);
    }

    public final int getX() {
        return this.getPosition().x;
    }

    public final int getY() {
        return this.getPosition().y;
    }

    public final Point getPosition() {
        return this.position;
    }

    public final void setPosition(final Point position) {
        this.position = position;
    }

    public final void setPosition(final int x, final int y) {
        this.position = new Point(x, y);
    }

    public final void move() {
        this.getPosition().x = this.getPosition().x + (this.random.nextInt() % 2);
        if (this.getPosition().x < 0) {
            this.getPosition().x = 0;
        }
        if (this.getPosition().x >= Showboarddemo.width) {
            this.getPosition().x = Showboarddemo.width - 1;
        }
        this.getPosition().y = this.getPosition().y + (this.random.nextInt() % 2);
        if (this.getPosition().y < 0) {
            this.getPosition().y = 0;
        }
        if (this.getPosition().y >= Showboarddemo.height) {
            this.getPosition().y = Showboarddemo.height - 1;
        }
    }
}
