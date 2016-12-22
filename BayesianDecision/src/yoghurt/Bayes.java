package yoghurt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;
import java.math.*;

public class Bayes {
    private Double[] heightOfBoy = new Double[400];
    private Double[] heightOfGril = new Double[400];
    private int lenOfBoy;
    private int lenOfGril;
    private double priorP1;  //1代表男生,0代表女生
    private double ave1;
    private double ave0;
    private double var1;
    private double var0;

    public void setPriorP1(double p){
        priorP1 = p;
    }
    public void setHeightOfBoy(String path){
        heightOfBoy = loadHeight(path,1);
    }
    public void setHeightOfGril(String path){
        heightOfGril = loadHeight(path,0);
    }


    public Double[] loadHeight(String path, int flag){
        int i = 0;
        Double[] heightOfSample = new Double[400];
        try {
            Scanner reader = new Scanner(new File(path));
            while (reader.hasNextLine()) {
                String temp = reader.nextLine();
                temp.replaceAll(" ","");
                temp = temp.substring(0,3);
                heightOfSample[i] = Double.parseDouble(temp);
                //String[] dataSample = temp.split("\t");
               // heightOfSample[i] = Double.parseDouble(dataSample[0]);
                i++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }

        if(flag == 1){
            lenOfBoy = i;
        }
        else{
            lenOfGril = i;
        }
        return heightOfSample;
    }

    public double getAverage(Double[] array, int count){
        double sum = 0;
        for(int i = 0;i < count;i++){
            sum += array[i].doubleValue();
        }
        return sum / count;
    }

    public double getVariance(Double[] array,int count){
        double sum = 0;
        double ave = getAverage(array,count);
        for(int i = 0;i < count;i++){
            sum += (array[i].doubleValue() - ave) * (array[i].doubleValue() - ave);
        }
        return sum / count;
    }
    public void setData(){
         ave1 = getAverage(heightOfBoy,lenOfBoy);
         ave0 = getAverage(heightOfGril,lenOfGril);
         var1 = getVariance(heightOfBoy,lenOfBoy);
         var0 = getVariance(heightOfGril,lenOfGril);
    }
    public int Classify(double height){
        double p1 = 0.5 * ((Math.log(2*Math.PI*var1)) - (height-ave1)*(height-ave1)/var1) ;
        double p0 = 0.5 * ((Math.log(2*Math.PI*var0)) - (height-ave0)*(height-ave0)/var0) ;
        //System.out.println(p1);
        //System.out.println(p0);
        if(p1*priorP1 >= (p0*(1-priorP1))){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void testing(){
        double rightNum1 = 0;
        double rightNum0 = 0;
        for(int i=0;i<lenOfBoy;i++){
            if(Classify(heightOfBoy[i].doubleValue()) == 1){
                rightNum1++;
            }
        }
        for(int i=0;i<lenOfGril;i++){
            if(Classify(heightOfGril[i].doubleValue()) == 0){
                rightNum0++;
            }
        }
        System.out.println("男生的正确率是" + rightNum1/lenOfBoy);
        System.out.println("女生的正确率是" + rightNum0/lenOfGril);
    }
}
