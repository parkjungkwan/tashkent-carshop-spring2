package texas.sbv.car.recommend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends CrudRepository<Recommend, Long> {
    @Query(value = "SELECT distinct model_grp_nm  FROM car WHERE makenm like ? " , nativeQuery = true)
    public List<String> findByMakeNm(String makeNm);
    @Query(value = "SELECT distinct modelnm  FROM car WHERE model_grp_nm like ? " , nativeQuery = true)
    public List<String> findByModelGrpNm(String model_grp_nm);
    @Query(value = "SELECT userid FROM user " , nativeQuery = true)
    public List<String> findUserId();
    @Query(value = "SELECT name FROM user WHERE userid like ? " , nativeQuery = true)

    public String findNameByUserId(String userid);
    public List<Recommend> findByUserId(String userid);
    public List<Recommend> findByCenterCode(String centerCode);
}
