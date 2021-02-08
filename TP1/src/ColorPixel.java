/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

public class ColorPixel extends Pixel
{


    private int color1;
    private int color2;
    private int color3;

    /**
     * Constructeur de BWpixel
     * @param c1 code de couleur, doit être un int
     * @param c2 code de couleur, doit être un int
     * @param c3 code de couleur, doit être un int
     */
    public ColorPixel(int c1, int c2, int c3)
    {
        ReadPixel(c1, c2, c3);
    }
    /**
     * Attribue la valeur à color1
     * @param c1 code de couleur, doit être un int
     * @param c2 code de couleur, doit être un int
     * @param c3 code de couleur, doit être un int
     */
    public void ReadPixel(int c1, int c2, int c3)
    {
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
    }

    /**
     * TODO: Regler confusion value
     */
    public int getCodeValue()
    {
        return this.color1;
    }

    /**
     * Modifie la valeur courante de c
     * @param v difference de modification, doit être un int
     */
    public static void darken_Brigthen(int v)
    {

    }

    /**
     * compare deux pixel
     * @param p pixel a comparer
     * @return boolean de comparaison
     */
    public static boolean pixelsTheSame(Pixel p)
    {
        return true;
    }


}
