package texas.sbv.car.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.car.proxy.Trunk;
import texas.sbv.car.security.JwtTokenProvider;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired User user;
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired JwtTokenProvider jwtTokenProvider;
    @Autowired UserForAxios userForAxios;
    @Autowired Trunk<Object> trunk;


    @GetMapping("/idCheck/{userid}")
    public Boolean idCheck(@PathVariable String userid){
        return userRepository.findByUserid(userid)==null;
    }

    @PostMapping("/join")
    public Map<String, Object> join(@RequestBody User param){
        trunk.clear();
        userRepository.save(User.builder()
                .userid(param.getUserid())
                .passwd(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()))
                .name(param.getName())
                .birthMonth(param.getBirthMonth())
                .email(param.getEmail())
                .gender(param.getGender())
                .region(param.getRegion())
                .auth(0)
                .roles(Collections.singletonList("USER"))
                .build());
        if (userRepository.findByUserid(param.getUserid())!=null) {
            trunk.put(Arrays.asList("result"), Arrays.asList(true));
        } else {
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User param) {
        trunk.clear();
        user = userRepository.findByUserid(param.getUserid());
        if (BCrypt.checkpw(param.getPasswd(), user.getPasswd())) {
            trunk.put(Arrays.asList("user","token"),
                    Arrays.asList(makeUserAxios(user),jwtTokenProvider.createToken(user.getUserid(), user.getRoles())));
            trunk.put(Arrays.asList("result"), Arrays.asList(true));
        } else {
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
    }

    @PostMapping("/getUserInfo")
    public HashMap<String, Object> getUserInfo(@RequestBody String token){
        trunk.clear();
        if(token != null &&jwtTokenProvider.validateToken(token)){
            user = userRepository.findByUserid(jwtTokenProvider.getUserPk(token));
            trunk.put(Arrays.asList("token","result", "user"), Arrays.asList(token, true, makeUserAxios(user)));
        }else{
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, String> param){
        trunk.clear();
        if(jwtTokenProvider.validateToken(param.get("token"))){
            userService.update(User.builder().userid(param.get("userid"))
                    .passwd(BCrypt.hashpw(param.get("passwd"), BCrypt.gensalt()))
                    .name(param.get("username")).email(param.get("email")).gender(param.get("gender"))
                    .birthMonth(param.get("birthMonth")).region(param.get("region")).build());
            trunk.put(Arrays.asList("result"), Arrays.asList(true));
        }else{
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
    }
    @PostMapping("/withDrawl")
    public Map<String, Object> withDrawl(@RequestBody String token){
        trunk.clear();
        if(token != null &&jwtTokenProvider.validateToken(token)){
            userService.withDrawl(jwtTokenProvider.getUserPk(token));
            trunk.put(Arrays.asList("result"), Arrays.asList(true));
        }else{
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
}
    @PostMapping("/getAuth")
    public Map<String, Object> getAuth(@RequestBody String token){
        trunk.clear();
        System.out.println(token);
        if(token != null && jwtTokenProvider.validateToken(token)){
            trunk.put(Arrays.asList("result", "auth"), Arrays.asList(true, userRepository.findRolesByuserUserseq(user.getUserSeq())));
        }else{
            trunk.put(Arrays.asList("result"), Arrays.asList(false));
        }
        return trunk.get();
    }
    private UserForAxios makeUserAxios(User user) {
        userForAxios.setUserid(user.getUserid());
        userForAxios.setUsername(user.getName());
        userForAxios.setAuth(userRepository.findRolesByuserUserseq(user.getUserSeq()));
        return userForAxios;
    }
}
