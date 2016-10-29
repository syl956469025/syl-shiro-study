package syl.study.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by Think on 2016/10/28.
 */
public class ShiroTest {


    @Test
    public void testShrio(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:userss.ini");
        org.apache.shiro.mgt.
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);

        System.out.println(subject.isAuthenticated());
    }


}
