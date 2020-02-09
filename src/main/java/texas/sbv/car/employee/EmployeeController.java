package texas.sbv.car.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {
    @Autowired EmployeeRepository employeeRepository;

    @GetMapping("/employeeList/{centerCode}")
    public Map<String, List<Employee>> employeeList(@PathVariable String centerCode){
        Map<String,List<Employee>> map =new HashMap<>();
        map.put("result",employeeRepository.findByCenterCode(centerCode));
        return map;
    }
}
