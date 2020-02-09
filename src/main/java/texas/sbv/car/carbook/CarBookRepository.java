package texas.sbv.car.carbook;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBookRepository extends CrudRepository<CarBook, Long> {

    @Query(value = "SELECT * FROM MYCAR INNER JOIN USER ON MYCAR.USER_SEQ = USER.USERSEQ WHERE USER_SEQ =:userSeq",
    nativeQuery = true)
    public CarBook findBySeq(long userSeq);

}
