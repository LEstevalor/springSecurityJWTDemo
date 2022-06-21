package com.it;

import com.it.domain.User;
import com.it.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@SpringBootTest
class SpringSecurityJwtDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordTest() {
        //BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        PasswordEncoder enc = passwordEncoder;
        String encode = enc.encode("123456");
        System.out.println(encode);  //$2a$10$/M27WCopR66fxIr5l8Ozs.IkOzuzMskwprV59Kk0NU/qy7Q.OG.Ym
        System.out.println(enc.matches("123456", "$2a$10$/M27WCopR66fxIr5l8Ozs.IkOzuzMskwprV59Kk0NU/qy7Q.OG.Ym"));
                        //存储到数据库的一般都是加密的，BCryptPasswordEncoder加密是随机的，但是都能与明文匹配上
        // （数据库里明文和明文是匹配不上的），比如1234和1234
    }

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
