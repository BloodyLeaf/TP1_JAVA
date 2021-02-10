/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.Locale;
import java.util.Scanner;

public class Image
{

    String imgType;
    private int width;
    private int height;
    private int maxColorValue;
    private Pixel lstPixel[][];


    //Manque sa a finir : get le type
    public Image(String FileName)
    {
        try {
            BufferedInputStream in  = new BufferedInputStream(new DataInputStream(new FileInputStream(FileName)));
            Scanner fileReader = new Scanner(in).useLocale(Locale.CANADA);
            imgType = fileReader.next();
            width =  fileReader.nextInt();
            height = fileReader.nextInt();
            maxColorValue = fileReader.nextInt();

            if(imgType == "PGM"){
                lstPixel = new BWPixel[width][height];
                for(int j = 0 ; j < height ; j++){
                    for(int i = 0 ; i < width;i++) {
                        int pixelValue = fileReader.nextInt();
                        setPixel(i,j,pixelValue);
                    }
                }
            }
            else if (imgType == "PPM"){
                lstPixel = new ColorPixel[width][height];

                for(int j = 0 ; j < height ; j++){
                    for(int i = 0 ; i < width;i++) {
                        int pixelValuec1 = fileReader.nextInt();
                        int pixelValuec2 = fileReader.nextInt();
                        int pixelValuec3 = fileReader.nextInt();
                        setPixel(i,j,pixelValuec1,pixelValuec2,pixelValuec3);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Image(int w, int h, String imageType){
        width = w;
        height = h;
        imgType = imageType;

        if(imgType =="P2"){                     //PGM
            lstPixel = new BWPixel[width][height];
        }
        else if (imgType == "P3"){              //PPM
            lstPixel = new ColorPixel[width][height];
        }
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
     * @return
     */
    public static int prepoderanceColor()
    {
        return 0;
    }


    /**
     * Modifie la valeur courante de l'image
     * @param v difference de modification, doit être un int
     */
    public void darken_Brigthen(int v)
    {
        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < height ; j++){
                if(getPixel(i,j) instanceof BWPixel ) ((BWPixel)getPixel(i,j)).darken_Brigthen(v);
                if(getPixel(i,j) instanceof ColorPixel ) ((ColorPixel)getPixel(i,j)).darken_Brigthen(v);
            }
        }
    }

    /**
     * Écrit l'image dans un fichier
     * @param f fichier d'écriture
     */
    public void write(File f)
    {
        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < height ; j++){
                if(getPixel(i,j) instanceof BWPixel ) ((BWPixel)getPixel(i,j)).writePixel(f);
                if(getPixel(i,j) instanceof ColorPixel ) ((ColorPixel)getPixel(i,j)).writePixel(f);
            }
        }
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
    public static void copyImage(Image img)
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
    public static Image extract(int x, int y, int nHeight, int nWidth)
    {
        Image img = null;
        return img;
    }

    /**
     * tourne l'image a 90
     */
    public void rotate90()
    {
        int newW = height;
        int newH = width;
        Pixel newLstPixel[][] = null;

        if(imgType =="P2"){                     //PGM
            newLstPixel = new BWPixel[width][height];

            for(int j = 0; j < height ; j ++ ){
                for(int i = 0 ; i < width ; i++){
                    newLstPixel[j][newW-i-newH] = getPixel(i,j);

                }
            }
        }
        else if (imgType == "P3"){              //PPM
            newLstPixel = new ColorPixel[width][height];

            for(int j = 0; j < height ; j ++ ){
                for(int i = 0 ; i < width ; i++){
                    newLstPixel[newH-1-j][i] = getPixel(i,j);

                }
            }
        }



    }

    /**
     * reduit la grosseur de l'image
     */
    public Image reduce()
    {
        Image reducedImage = new Image(width,height,imgType);
        if(imgType == "P2"){                    //PGM


            for(int j = 0 ; j < height-1 ; j+=2) {
                for(int i = 0 ; i < width-1 ; i+=2){
                    int moy = ((BWPixel)lstPixel[i][j]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i+1][j]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i][j+1]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i+1][j+1]).getCodeValue();
                    reducedImage.setPixel(i/2,j/2,moy/4);

                }
            }


        } else if(imgType =="P3"){                          //PPM
            for(int j = 0 ; j < height-1 ; j+=2) {
                for(int i = 0 ; i < width-1 ; i+=2){
                    int moy1 = ((ColorPixel)lstPixel[i][j]).getColor1();
                    moy1 += ((ColorPixel)lstPixel[i+1][j]).getColor1();
                    moy1 += ((ColorPixel)lstPixel[i][j+1]).getColor1();
                    moy1 += ((ColorPixel)lstPixel[i+1][j+1]).getColor1();

                    int moy2 = ((ColorPixel)lstPixel[i][j]).getColor2();
                    moy2 += ((ColorPixel)lstPixel[i+1][j]).getColor2();
                    moy2 += ((ColorPixel)lstPixel[i][j+1]).getColor2();
                    moy2 += ((ColorPixel)lstPixel[i+1][j+1]).getColor2();

                    int moy3 = ((ColorPixel)lstPixel[i][j]).getColor2();
                    moy3 += ((ColorPixel)lstPixel[i+1][j]).getColor2();
                    moy3 += ((ColorPixel)lstPixel[i][j+1]).getColor2();
                    moy3 += ((ColorPixel)lstPixel[i+1][j+1]).getColor2();

                    reducedImage.setPixel(i/2,j/2,moy1/4,moy2/4,moy3/4);

                }
            }
        }

        return reducedImage;
    }

    private Pixel getPixel(int x, int y ){

        if(lstPixel[x][y] instanceof BWPixel){
            return lstPixel[x][y];
        }
        else if (lstPixel[x][y] instanceof ColorPixel){
            return lstPixel[x][y];
        }
        return lstPixel[x][y];
    }
    private void setPixel(int x,int y , int value){
        if(lstPixel[x][y] instanceof BWPixel){
            ((BWPixel) lstPixel[x][y]).setValue(value);
        }

    }

    private void setPixel(int x,int y , int value1, int value2, int value3){
        if(lstPixel[x][y] instanceof BWPixel){
            ((ColorPixel) lstPixel[x][y]).setValue(value1,value2,value3);
        }

    }

}
