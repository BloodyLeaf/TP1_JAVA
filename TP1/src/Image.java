/**
 * @author Pier-Alexandre Carron, William Goupil
 * @version 1.0
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Image
{

    String imgType;
    private int width;
    private int height;
    private int maxColorValue;
    private Pixel lstPixel[][];

    public Image(String FileName)
    {
        this.readImage(FileName);
    }

    public Image(int w, int h, String imageType){
        width = w;
        height = h;
        imgType = imageType;

        if(imgType.equals("P2")){                     //PGM

            lstPixel = new BWPixel[width][height];
        }
        else if (imgType.equals("P3")){              //PPM
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
     * @return couleur plus présente dans l'image
     */
    public Pixel prepoderanceColor()
    {
        List<Pixel> tempList = new ArrayList<Pixel>();
        Pixel res = new Pixel();
        if(imgType.equals("P2"))
        {
            for(int i = 0 ; i < width ; i++){
                for(int j = 0 ; j < height ; j++){
                    tempList.add((BWPixel)getPixel(i,j));
                }
            }
            int taille = tempList.size();
            BWPixel tmp;
            BWPixel testPixel;

            for(int i=0; i < taille; i++)
            {
                for(int j=1; j < (taille-i); j++)
                {

                    {
                        tmp = (BWPixel)tempList.get(j-1);
                        tempList.set(j-1,tempList.get(j));
                        tempList.set(j, tmp);
                    }
                }
            }
            int max_count = 1;
            int curr_count = 1;
            res = (BWPixel)tempList.get(0);

            for(int i = 0 ; i < tempList.size() ; i++){
                testPixel = (BWPixel)tempList.get(i-1);
                if(testPixel.pixelsTheSame(tempList.get(i)))
                    curr_count++;
                else {
                    if (curr_count > max_count) {
                        max_count = curr_count;
                        res = (BWPixel)tempList.get(i - 1);
                    }
                    curr_count = 1;
                }
            }
        }
        else if(imgType.equals("P3"))
        {
            for(int i = 0 ; i < width ; i++){
                for(int j = 0 ; j < height ; j++){
                    tempList.add((ColorPixel)getPixel(i,j));
                }
            }
            int taille = tempList.size();
            ColorPixel tmp;
            ColorPixel testPixel;

            for(int i=0; i < taille; i++)
            {
                for(int j=1; j < (taille-i); j++)
                {

                    {
                        tmp = (ColorPixel)tempList.get(j-1);
                        tempList.set(j-1,tempList.get(j));
                        tempList.set(j, tmp);
                    }
                }
            }
            int max_count = 1;
            int curr_count = 1;
            res = (ColorPixel)tempList.get(0);

            for(int i = 0 ; i < tempList.size() ; i++){
                testPixel = (ColorPixel)tempList.get(i-1);
                if(testPixel.pixelsTheSame(tempList.get(i)))
                    curr_count++;
                else {
                    if (curr_count > max_count) {
                        max_count = curr_count;
                        res = (ColorPixel)tempList.get(i - 1);
                    }
                    curr_count = 1;
                }
            }
        }
        return res;
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
        for(int j = 0 ; j < height ; j++){
            for(int i = 0 ; i < width ; i++){

                if(getPixel(i,j) instanceof BWPixel){
                    System.out.println(((BWPixel)getPixel(i,j)).getCodeValue());
                    ((BWPixel)getPixel(i,j)).writePixel(f);
                }
                if(getPixel(i,j) instanceof ColorPixel ){
                    ((ColorPixel)getPixel(i,j)).writePixel(f);
                }
            }
        }
    }

    /**
     * Lit l'image d'un fichier
     * @param f Fichier contenant l'image
     */
    public void readImage(String f)
    {
        try {
            BufferedInputStream in  = new BufferedInputStream(new DataInputStream(new FileInputStream(f)));
            Scanner fileReader = new Scanner(in);
            imgType = fileReader.next();
            width =  fileReader.nextInt();
            height = fileReader.nextInt();
            maxColorValue = fileReader.nextInt();



            if(imgType.equals("P2")){
                
                lstPixel = new BWPixel[width][height];
                for(int j = 0 ; j < height ; j++){
                    for(int i = 0 ; i < width;i++) {
                        int pixelValue = fileReader.nextInt();
                        setPixel(i,j,pixelValue);
                    }
                }
            }
            else if (imgType.equals("P3")){
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

    /**
     * permet de copier un image
     * @param img image à copier
     */
    public static void copyImage(Image img)
    {
        Image img2 = new Image(img.width,img.height, img.imgType);

        if(img2.imgType.equals("P2")) {
            img2.lstPixel = new BWPixel[img2.width][img2.height];
            for (int j = 0; j < img2.height; j++) {
                for (int i = 0; i < img2.width; i++) {
                    BWPixel tempPixel = (BWPixel) img.getPixel(i, j);
                    img2.setPixel(i, j, tempPixel.getCodeValue());
                }
            }
        }
        else if (img2.imgType.equals("P3")){
            img2.lstPixel = new ColorPixel[img2.width][img2.height];

            for(int j = 0 ; j < img2.height ; j++){
                for(int i = 0 ; i < img2.width;i++) {
                    ColorPixel tempPixel = (ColorPixel)img.getPixel(i,j);
                    img2.setPixel(i,j,tempPixel.getColor1(),tempPixel.getColor2(),tempPixel.getColor3());
                }
            }
        }
    }

    /**
     * Permet d'extraire une partie d'un image
     * @param x position x dans l'image, doit être un int
     * @param y position y dans l'image, doit être un int
     * @param nHeight hauteur de l'image, doit être un int
     * @param nWidth largeur de l'image, doit être un int
     * @return nouvelle image
     */
    public  Image extract(int x, int y, int nHeight, int nWidth)
    {
        Image newImg;
        newImg = new Image(nWidth,nHeight, this.imgType);

        if(newImg.imgType.equals("P2")){
            newImg.lstPixel = new BWPixel[newImg.width][newImg.height];
            for(int j = 0 ; j < newImg.height ; j++){
                for(int i = 0 ; i < newImg.width;i++) {
                    BWPixel tempPixel = (BWPixel)this.getPixel(i + x, j + y);
                    newImg.setPixel(i, j, tempPixel.getCodeValue());
                }
            }
        }
        else if (newImg.imgType.equals("P3")){
            newImg.lstPixel = new ColorPixel[newImg.width][newImg.height];

            for(int j = 0 ; j < newImg.height ; j++){
                for(int i = 0 ; i < newImg.width;i++) {
                    ColorPixel tempPixel = (ColorPixel)this.getPixel(i + x, j + y);
                    newImg.setPixel(i,j,tempPixel.getColor1(),tempPixel.getColor2(),tempPixel.getColor3());
                }
            }
        }
        return newImg;
    }

    /**
     * tourne l'image a 90
     */
    public void rotate90()
    {
        int newW = height;
        int newH = width;
        Pixel[][] newLstPixel = new BWPixel[newW][newH];

        if(imgType.equals("P2")){                     //PGM


            for(int i = 0; i < width ; i++ ){
                for(int j = 0 ; j < height ; j++){
                    System.out.println("i : " + i + " j : " + j);

                    newLstPixel[j][width-1-i] = getPixel(i,j);

                }
            }
        }
        else if (imgType.equals("P3")){              //PPM
            newLstPixel = new ColorPixel[width][height];

            for(int j = 0; j < height ; j ++ ){
                for(int i = 0 ; i < width ; i++){
                    newLstPixel[newH-1-j][i] = getPixel(i,j);

                }
            }
        }

        lstPixel = newLstPixel;

    }

    /**
     * reduit la grosseur de l'image
     */
    public Image reduce()
    {
        Image reducedImage = new Image(width,height,imgType);
        if(imgType.equals("P2")){                    //PGM


            for(int j = 0 ; j < height-1 ; j+=2) {
                for(int i = 0 ; i < width-1 ; i+=2){
                    int moy = ((BWPixel)lstPixel[i][j]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i+1][j]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i][j+1]).getCodeValue();
                    moy += ((BWPixel)lstPixel[i+1][j+1]).getCodeValue();
                    reducedImage.setPixel(i/2,j/2,moy/4);

                }
            }


        } else if(imgType.equals("P3")){                          //PPM
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

    private Pixel getPixel(int x, int y){
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
