package xunshan.features.proxy.aop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AopConfig.class)
public class AopProxyTest {
    @Autowired
    private CustomerService customerService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getNameShouldBeIntercept() {
        customerService.setName("NAME");
        customerService.getName();
    }
}