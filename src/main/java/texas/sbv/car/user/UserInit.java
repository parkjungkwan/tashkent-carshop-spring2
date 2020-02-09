package texas.sbv.car.user;


import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import texas.sbv.car.company.CompanyRepository;
import texas.sbv.car.social.SocialInit;
import texas.sbv.car.social.SocialRepository;
import texas.sbv.car.util.UserDummy;

@Order(value=2)
@Component
public class UserInit implements ApplicationRunner {
    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    @Autowired
    SocialRepository socialRepository;
    @Autowired SocialInit socialInit;
    public UserInit(UserRepository userRepository,CompanyRepository companyRepository){
        this.userRepository= userRepository;
        this.companyRepository= companyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss").format (System.currentTimeMillis()) + "  INFO 18844 --- [           UserInit ]         : UserInit Start ");

        UserDummy userDummy = new UserDummy();
        if(userRepository.count() == 0){
            List<String> code = companyRepository.findCenterCode();
            for(int i =0;  i<500; i++){
                userRepository.save(userDummy.makeUser());
            }
            code.forEach(el->{
                User user =new User();
                user.setUserid(el);
                user.setName(companyRepository.findCenterNameByCenterCode(el));
                user.setPasswd(BCrypt.hashpw(el, BCrypt.gensalt()));
                user.setEmail(el+"@gmail.com");
                user.setAuth(1);
                user.setRegion(companyRepository.findRigionByCenterCode(el));
                user.setRoles(Collections.singletonList("ADMIN"));
                userRepository.save(user);
            });
        }

        System.out.println( new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss").format (System.currentTimeMillis()) + "  INFO 18844 --- [           UserInit ]         : UserInit END ");
    }
}
