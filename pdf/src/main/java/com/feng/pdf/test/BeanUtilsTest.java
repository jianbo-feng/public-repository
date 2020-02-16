package com.feng.pdf.test;

import com.feng.pdf.bean.Test;
import com.feng.pdf.vo.TestVo;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {

    public static void main(String... args) throws Exception {

        Test t = new Test();
        t.setUserId("121212");
        t.setUserName("张三");
        t.setPassword("1111111");
        System.err.println("t.toStrirg() ==> " + t.toString());

        TestVo vo = new TestVo();
        BeanUtils.copyProperties(t, vo);
        System.err.println(vo.toString());

    }
}
