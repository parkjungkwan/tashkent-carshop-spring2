package texas.sbv.car.user;

import org.springframework.stereotype.Component;

@Component
public interface UserService{
    public void withDrawl(String userid);
    public User update(User user);
}
