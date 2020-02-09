package texas.sbv.car.record;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {
    public List<Record> findByMycarIdOrderByDate(Long id);

    @Query(value = "SELECT * FROM RECORD INNER JOIN MYCAR ON MYCAR.MYCARID = RECORD.MYCAR_ID WHERE MYCAR_ID =:id",
            nativeQuery = true)
    public List<Record> findByMycarId(long id);

    public void deleteRecordByRecordId(long id);
}
