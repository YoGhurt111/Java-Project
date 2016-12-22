package yoghurt;


public class main {
    public static void main(String[] args){
        Bayes bayes = new Bayes();
        //训练样本
        bayes.setHeightOfBoy("d:/BayesianDecision/boy.txt");
        bayes.setHeightOfGril("d:/BayesianDecision/girl.txt");
        bayes.setData();
        //分类测试
        bayes.setPriorP1(0.75);
        bayes.setHeightOfBoy("d:/BayesianDecision/boynew.txt");
        bayes.setHeightOfGril("d:/BayesianDecision/girlnew.txt");
        bayes.testing();
    }
}
