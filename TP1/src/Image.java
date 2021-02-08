/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

import java.io.File;
import java.lang.reflect.Array;

public class Image
{
    private File f;
    private int width;
    private int height;
    //private Array<Pixel> lstPixel;

    public Image()
    {

    }
    /**
     * permet de set la largeur de l'image
     * @param width largeur de limage, doit être un int
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * permet de set la hauteur de l'image
     * @param height hauteur de l'image, doit être un int
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Verifie la couleur la plus présente dans l'image
     * @param img l'image a vérifier, doit être une image
     * @return
     */
    public static int prepoderanceColor(Image img)
    {
        return 0;
    }
    /**
     * Modifie la valeur courante de l'image
     * @param v difference de modification, doit être un int
     */
    public static void darken_Brigthen(int v)
    {

    }

    /**
     * Écrit l'image dans un fichier
     * @param f fichier d'écriture
     */
    public void write(File f)
    {

    }

    /**
     * Lit l'image d'un fichier
     * @param f Fichier contenant l'image
     */
    public void readImage(File f)
    {

    }

    /**
     * permet de copier un image
     * @param img image à copier
     */
    public static void CopyImage(Image img)
    {

    }

    /**
     * Permet d'extraire une partie d'un image
     * @param x position x dans l'image, doit être un int
     * @param y position y dans l'image, doit être un int
     * @param nHeight hauteur de l'image, doit être un int
     * @param nWidth largeur de l'image, doit être un int
     * @return nouvelle image
     */
    public static Image Extract(int x, int y, int nHeight, int nWidth)
    {
        Image img = new Image();
        return img;
    }

    /**
     * tourne l'image a 90
     */
    public static void rotate90()
    {

    }

    /**
     * reduit la grosseur de l'image
     */
    public static void reduce()
    {

    }
}
