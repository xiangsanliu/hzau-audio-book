package com.hzau.feidian.hzauaudiobook;

import org.junit.Test;

import java.io.File;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HZAUAudioBookApplicationTests {

    @Test
    public void contextLoads() {
        String s = "C:\\Users\\xiang\\Documents\\三体\\epubBuilder破解版.zip";
        System.out.println(s.lastIndexOf(File.separator));
        System.out.println(s.substring(s.lastIndexOf(File.separator)+1));
    }

}
