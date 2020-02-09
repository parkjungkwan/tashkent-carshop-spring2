package texas.sbv.car.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import texas.sbv.car.user.UserRepository;

@RequiredArgsConstructor
@Service
public class RpmUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String userid) {
        return userRepository.findByUserid(userid);
    }
}