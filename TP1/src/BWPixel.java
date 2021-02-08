import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        readPixel(c);
    }
    /**
     * Attribue la valeur à color1
     * @param c code de couleur, doit être un int
     */
    public void readPixel(int c)
    {
        this.color1 = c;
    }
    public void writePixel(File f)
    {
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(f);
            writer.write(this.getCodeValue());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     *  envoie valuer color1
     * @return valeur de c1, est un int
     */
    private int getCodeValue()
    {
        return this.color1;
    }

    /**
     * Modifie la valeur courante de c
     * @param v difference de modification, doit être un int
     */
    public void darken_Brigthen(int v)
    {
        readPixel(getCodeValue() - v);
    }

    /**
     * compare deux pixel
     * @param p pixel a comparer
     * @return boolean de comparaison
     */
    public boolean pixelsTheSame(Pixel p)
    {
        if(p instanceof BWPixel)
            return ((BWPixel) p).getCodeValue() == this.getCodeValue();

        return false;
    }
}
