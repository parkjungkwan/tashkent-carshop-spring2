package texas.sbv.car.recommendedCar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedCarRepository extends CrudRepository<RecommendedCar, Long> {

    List<RecommendedCar> findByUserid(String userid);

}
