package syl.study.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mtime on 2016/10/29.
 */
public class MyFirstShiroDemo {



    static Logger log = LoggerFactory.getLogger(MyFirstShiroDemo.class);

    public static void main(String[] args) {

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();

        session.setAttribute("price","123");

        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
            token.setRememberMe(true);
            try {
                currentUser.login( token );
                log.debug("用户名密码正确， 登录成功");
                log.info( "User [" + currentUser.getPrincipal() + "] logged in successfully." );
                if (currentUser.hasRole("admin")){
                    log.info("User [" + currentUser.getPrincipal() + "] has role admin");
                }else{
                    log.info("User [" + currentUser.getPrincipal() + "] is not role admin");
                }

                if (currentUser.isPermitted("lightsaber:zhangsan")){
                    log.info("you have permition");
                }

                currentUser.logout();
                if (!currentUser.isAuthenticated()){
                    log.info("退出成功");
                }



            } catch ( UnknownAccountException e ) {
                log.debug("该用户不存在:{}",e);
            } catch ( IncorrectCredentialsException e ) {
                log.debug("密码不正确:{}",e);
            } catch ( LockedAccountException e ) {
                log.debug("被锁定用户不可以登录:{}",e);
            }catch ( AuthenticationException e ) {
                log.debug("验证失败 : {}" , e);
            }
        }


    }


}
