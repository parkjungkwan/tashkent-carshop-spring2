package texas.sbv.car.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.car.car.Car;
import texas.sbv.car.car.CarRepository;
import texas.sbv.car.recommend.Recommend;
import texas.sbv.car.recommend.RecommendRepository;


@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:8081")
public class CompanyController {
    @Autowired
    CarRepository carsRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RecommendRepository recommendRepository;
    @Autowired
    CompanyServiceImpl companyServiceImpl;

    @GetMapping("/carList/{centerCode}")
    public Map<String, List<Car>> carList(@PathVariable String centerCode){
        Map<String,List<Car>> map = new HashMap();
        map.put("result",carsRepository.findByCenterCode(centerCode));
        return map;
    }
    @GetMapping("/customerList")
    public Map<String, String> customerList(){
        Map<String,String> map = new HashMap();
        map.put("result","aa");
        return map;

    }
    @PostMapping("/bestCarList")
    public List<Car> bestCarList(@RequestBody Recommend recommend){
        System.out.println(recommend.toString());

        return companyServiceImpl.getBestCarList(recommend);
    }


}
