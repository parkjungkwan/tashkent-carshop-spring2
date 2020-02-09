package texas.sbv.car.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {
    @Query(value = "SELECT distinct center_code FROM car", nativeQuery = true)
    public List<String> findCenterCode();
    @Query(value = "SELECT distinct center_name FROM car WHERE center_code like ?", nativeQuery = true)
    public String findCenterNameByCenterCode(String centerCode);
    @Query(value = "SELECT distinct center_name FROM car WHERE center_code like ? " , nativeQuery = true)
    public String findByCenterCode(String code);
    @Query(value = "SELECT distinct center_region FROM car WHERE center_code like ? " , nativeQuery = true)
    public String findCenterRigionByCenterCode(String code);
    @Query(value = "SELECT distinct center_code FROM car WHERE center_name like ? " , nativeQuery = true)
    public String findByCenterName(String name);
    @Query(value = "SELECT distinct center_name FROM company WHERE center_region like ? " , nativeQuery = true)
    public List<String> findByCenterRegion(String region);
    @Query(value = "SELECT distinct center_region FROM company  " , nativeQuery = true)
    public List<String> findAllRigion();
    @Query(value = "SELECT distinct center_region FROM company WHERE center_code like ? " , nativeQuery = true)
    public String findRigionByCenterCode(String centerCode);

}
