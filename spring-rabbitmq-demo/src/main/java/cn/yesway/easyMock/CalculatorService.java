package cn.yesway.easyMock;

/**
 * @author Administrator
 * @date 2017/10/18 14:57
 * @desc
 */
public interface CalculatorService {
     double add(double input1, double input2);
     double subtract(double input1, double input2);
     double multiply(double input1, double input2);
     double divide(double input1, double input2);
     public void serviceUsed();
}
