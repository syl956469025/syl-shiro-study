package syl.study.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mtime on 2016/10/29.
 */
public class MyRealm1 implements Realm {
    static Logger log = LoggerFactory.getLogger(MyRealm1.class);

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof UsernamePasswordToken; //仅支持usernamePasswordToken
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal(); //获取用户名
        String password = new String((char[])token.getCredentials());
        log.debug("username:{} , password:{}",username, password);
        if (!"zhang".equals(username)){
            throw new UnknownAccountException("用户必须是zhang");
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException("密码必须是123");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
