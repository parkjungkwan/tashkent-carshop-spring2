package texas.sbv.car.social;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import texas.sbv.car.user.User;

@Repository
public interface ThumbRepository extends CrudRepository<Thumb, Long> {
    Thumb findByBoardSeqAndUserSeq(Social social, User user);
}
