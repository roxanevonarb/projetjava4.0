package showboard;

import java.io.IOException;

import showboard.Showboarddemo;

public abstract class Main {

    public static void main(final String[] args) throws IOException, InterruptedException {
        final Showboarddemo demo = new Showboarddemo();
        demo.move();
    }
}
