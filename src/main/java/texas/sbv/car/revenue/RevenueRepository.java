package texas.sbv.car.revenue;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RevenueRepository extends CrudRepository<Revenue,Long> {
    public List<Revenue> findByCenterCode(String code);
}
