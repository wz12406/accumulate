package cn.yesway.easymocktest;

import cn.yesway.easyMock.CalculatorService;
import cn.yesway.easyMock.MathApplication;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class MathApplicationTester {
    //@TestSubject annotation is used to identify class which is going to use the
    //mock object
    @TestSubject
    MathApplication mathApplication = new MathApplication();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    @Test
    public void testAdd(){
        //add the behavior of calc service to add two numbers
        EasyMock.expect(calcService.add(10.0,20.0)).andStubReturn(30.00);
        //limit the method call to 1, no less and no more calls are allowed
        calcService.serviceUsed();
        EasyMock.expectLastCall().times(3);
        //activate the mock
        EasyMock.replay(calcService);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        EasyMock.verify(calcService);
    }

}
