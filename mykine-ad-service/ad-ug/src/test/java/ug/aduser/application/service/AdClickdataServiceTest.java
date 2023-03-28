package ug.aduser.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ug.aduser.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdClickdataServiceTest {

    @Autowired
    AdClickdataService adClickdataService;

    @Test
    public void testSendData1(){
        for (int i = 0; i < 1000000000; i++) {
            System.out.println("i1="+i);
        }
    }

    @Test
    public void testSendData2(){
        for (int i = 0; i < 1000000000; i++) {
            System.out.println("i2="+i);
        }
    }


}
