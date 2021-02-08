/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ColorPixel extends Pixel {


    private int color1;
    private int color2;
    private int color3;

    /**
     * Constructeur de BWpixel
     *
     * @param c1 code de couleur, doit être un int
     * @param c2 code de couleur, doit être un int
     * @param c3 code de couleur, doit être un int
     */
    public ColorPixel(int c1, int c2, int c3) {
        readPixel(c1, c2, c3);
    }

    /**
     * Attribue la valeur à color1
     *
     * @param c1 code de couleur, doit être un int
     * @param c2 code de couleur, doit être un int
     * @param c3 code de couleur, doit être un int
     */
    public void readPixel(int c1, int c2, int c3) {
        if (c1 < 0) {
            c1 = 0;
        }
        if (c2 < 0) {
            c2 = 0;
        }
        if (c3 < 0) {
            c3 = 0;
        }
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
    }

    public void writePixel(File f) {

        try {
            FileWriter myWriter = new FileWriter(f);
            myWriter.write(color1);
            myWriter.write(color2);
            myWriter.write(color3);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return un int, la valeur de la couleur 1
     */
    private int getColor1() {
        return this.color1;
    }

    /**
     * @return un int, la valeur de la couleur 2
     */
    private int getColor2() {
        return this.color2;
    }

    /**
     * @return un int, la valeur de la couleur 3
     */
    private int getColor3() {
        return this.color3;
    }


    /**
     * Modifie la valeur courante de c
     *
     * @param v difference de modification, doit être un int
     */
    public void darken_Brigthen(int v) {
        readPixel(getColor1() - v, getColor2() - v, getColor3() - v);
    }

    /**
     * compare deux pixel
     *
     * @param p pixel a comparer
     * @return boolean de comparaison
     */
    public boolean pixelsTheSame(Pixel p) {
        if (p instanceof ColorPixel) {
            if (((ColorPixel) p).getColor1() == getColor1() &&
                    ((ColorPixel) p).getColor2() == getColor2() &&
                    ((ColorPixel) p).getColor3() == getColor3()) {

                return true;
            }
        }
        return false;
    }


}
