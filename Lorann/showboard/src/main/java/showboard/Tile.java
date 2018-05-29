package showboard;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import showboard.ISquare;

public class Tile implements ISquare {

    /** The image. */
    private Image  image;

    /** The image name. */
    private String imageName;

    public Tile(final String imageName) {
        this.setImageName(imageName);
    }


    	public final Image getImage() {
        return this.image;
    }

   
    public final void loadImage() throws IOException {
        this.setImage(ImageIO.read(new File("image/" + this.getImageName())));
    }

    private void setImage(final Image image) {
        this.image = image;
    }

    private String getImageName() {
        return this.imageName;
    }

    private void setImageName(final String imageName) {
        this.imageName = imageName;
    }

}
