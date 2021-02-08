/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

public class BWPixel extends Pixel
{
    private int color1;

    /**
     * Constructeur de BWpixel
     * @param c code de couleur, doit être un int
     */
    public BWPixel(int c)
    {
        ReadPixel(c);
    }
    /**
     * Attribue la valeur à color1
     * @param c code de couleur, doit être un int
     */
    public void ReadPixel(int c)
    {
        this.color1 = c;
    }

    /**
     *  envoie valuer color1
     * @return valeur de c1, est un int
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
