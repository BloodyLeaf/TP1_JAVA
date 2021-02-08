/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

import java.io.File;

public class ImageAnalyser
{
    /**
     * lit une image d'un fichier
     * @param f fichier contenant l'image
     */
    public static void readImage(File f)
    {

    }

    /**
     *  Écrit une image dans un fichier
     * @param f fichier d'écriture
     * @param i image à écrire
     */
    public static void write(File f, Image i)
    {

    }
    /**
     * copie image2 dans image 1
     * @param img1 image affecter
     * @param img2 image copier
     */
    public static void CopyImage(Image img1, Image img2)
    {

    }
    /**
     * vérifie la couleur la plus présente dans l'image
     * @param img image a vérifier
     * @return valeur de la couleur la plus présente, est un int
     */
    public static int preponderanceColor(Image img)
    {

        return 0;
    }

    /**
     * applique un filtre sur une image
     * @param img image à traiter
     * @param v difference de modification, doit être un int
     */
    public static void darken_brighten(Image img, int v)
    {

    }

    /**
     * extrait une partie d'un image
     * @param img image ou on va extraire
     * @param x position x dans l'image, doit être un int
     * @param y position y dans l'image, doit être un int
     * @param nHeight hauteur de l'image, doit être un int
     * @param nWidth largeur de l'image, doit être un int
     * @return nouvelle image
     */
    public static Image extract(Image img, int x,int y, int nHeight, int nWidth)
    {
        return img;
    }

    /**
     * réduit une image
     * @param img image a réduire
     * @return nouvelle image
     */
    public static Image reduce(Image img)
    {
        return img;
    }

    /**
     * Pivote une image à 90 degré
     * @param img image à pivoter
     * @return image pivoter
     */
    public static Image rotate90(Image img)
    {
        return img;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {

    }

}
