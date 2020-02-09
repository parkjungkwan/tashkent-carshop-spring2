package texas.sbv.car.employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "SELECT distinct center_code FROM car", nativeQuery = true)
    public List<String> findCenterCode();
    @Query(value = "SELECT distinct center_code FROM employee WHERE em_code like ?", nativeQuery = true)
    public String findByEmCode(String emCode);
    @Query(value = "SELECT distinct em_code FROM employee WHERE em_position like '판매'", nativeQuery = true)
    public List<String> findEmCode();
    @Query(value = "SELECT distinct em_name FROM employee WHERE em_code like ?", nativeQuery = true)

    public String findEmNameByEmCode(String code);

    public List<Employee> findByCenterName(String centerName);
    public List<Employee> findByCenterCode(String centerCode);
}
