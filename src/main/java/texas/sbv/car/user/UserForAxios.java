package texas.sbv.car.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserForAxios {
    private String userid, username, auth;
}
