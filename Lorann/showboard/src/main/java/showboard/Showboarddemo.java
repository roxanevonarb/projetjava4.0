package showboard;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.swing.SwingUtilities;

import showboard.BoardFrame;
//import showboard.Monster;
import showboard.Tile;


	public class Showboarddemo extends Observable implements Runnable {

////////////////////////////////taille de la fenêtre/////////////////////////////////
		
		
    public static final int        width               = 6;

    public static final int        height              = 6;

    private static final int       timeLoop            = 300;

    private static final int       sizeFrame           = 400;

    private static final int       widthBetweenFrame   = 20;

///////////////////////////////une seul fenêtre//////////////////////////////////////  
  
    private static final Rectangle closeView           = new Rectangle(5, 5, width - 5, height - 5);
    
/////////////////////////////////////////////////////////////////////////////////
    
    private static final int       monstersNumber      = 20;
   
    private static final int       imageMonstersNumber = 4;

    /** The white tile. */
    private final Tile             whiteTile           = new Tile("WhiteTile.jpg");

    /** The black tile. */
    private final Tile             blackTile           = new Tile("BlackTile.jpg");

    /** The light gray tile. */
    private final Tile             lightGrayTile       = new Tile("LightGrayTile.jpg");

    /** The monsters. */
    private final List<Monster>    monsters;

   
    public Showboarddemo() throws IOException {
        super();
        final Random random = new Random();
        this.whiteTile.loadImage();
        this.blackTile.loadImage();
        this.lightGrayTile.loadImage();
        
        this.monsters = new ArrayList<Monster>();
        for (int i = 1; i <= monstersNumber; i++) {
            final Monster monster = new Monster("Monster" + ((i % imageMonstersNumber) + 1) + ".png");
            monster.loadImage();
            monster.setPosition(random.nextInt(width), random.nextInt(height));
            this.monsters.add(monster);
        }

        SwingUtilities.invokeLater(this);
    }
/////////////////////////////////////////fermer la fenêtre /////////////////////////////////////////////
 
    @Override
    public final void run() {
        final BoardFrame frameCloseView = new BoardFrame("Close view");
        frameCloseView.setDimension(new Dimension(width, height));
        frameCloseView.setDisplayFrame(closeView);
        frameCloseView.setSize(sizeFrame, sizeFrame);

        
        this.frameConfigure(frameCloseView);
        
    }
/////////////////////////////////////////faire bouger les monstres/////////////////////////////////////

    public final void move() throws InterruptedException {
        for (;;) {
            for (final Monster monster : this.monsters) {
                monster.move();
            }
            this.setChanged();
            this.notifyObservers();

            Thread.sleep(timeLoop);
        }
    }
//////////////////////////////////////////////////////////////////////////////////*/
    
    
    
    
    public final void frameConfigure(final BoardFrame frame) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((x == 0) || (x == (width - 1)) || (y == 0) || (y == (height - 1))) {
                    frame.addSquare(this.lightGrayTile, x, y);
                } else if (((x + y) % 2) == 0) {
                    frame.addSquare(this.whiteTile, x, y);
                } else {
                    frame.addSquare(this.blackTile, x, y);
                }
            }
        }

        for (final Monster monster : this.monsters) {
            frame.addPawn(monster);
        }

        this.addObserver(frame.getObserver());

        frame.setVisible(true);
    }
}
