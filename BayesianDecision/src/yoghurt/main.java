package yoghurt;


public class main {
    public static void main(String[] args){
        Bayes bayes = new Bayes();
        //训练样本
        bayes.setHeightOfBoy("boy.txt");
        bayes.setHeightOfGril("girl.txt");
        bayes.setData();
        //分类测试
        bayes.setPriorP1(0.5);
        bayes.setHeightOfBoy("boynew.txt");
        bayes.setHeightOfGril("girlnew.txt");
        bayes.testing();
    }
}
