package texas.sbv.car.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByUserid(String userid);
    @Query(value = "SELECT roles FROM user_roles WHERE user_userseq LIKE ? " , nativeQuery = true)
    public String findRolesByuserUserseq(long user_userseq);
}
