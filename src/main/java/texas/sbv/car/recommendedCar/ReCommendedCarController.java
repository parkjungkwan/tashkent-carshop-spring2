package texas.sbv.car.recommendedCar;

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

import texas.sbv.car.car.CarRepository;

@RestController
@RequestMapping("/recommendedCar")
@CrossOrigin(origins = "http://localhost:8081")
public class ReCommendedCarController {
    @Autowired RecommendedCarRepository recommendedCarRepository;
    @Autowired CarRepository carsRepository;
    @PostMapping("/recommendedCar")

    public void recommendedCar(@RequestBody Map<String,Object> send){
        List<String> list=(List<String>) send.get("carcodeList");
        list.forEach(el->{
            RecommendedCar recommendedCar= new RecommendedCar();
            recommendedCar.setCars(carsRepository.findByCarcd(el));
            recommendedCar.setUserid(String.valueOf(send.get("userid")));
            recommendedCar.setCenterCode(String.valueOf(send.get("centercode")));
            System.out.println(recommendedCar.toString());
            recommendedCarRepository.save(recommendedCar);
        });



    }
    @GetMapping("/getRecommendedCar/{userid}")
    public List<RecommendedCar> getRecommendedCar(@PathVariable String userid){
        List<RecommendedCar> list =recommendedCarRepository.findByUserid(userid);
        list.sort((a,b) -> b.getRecoCarSeq().compareTo(a.getRecoCarSeq()));
        return list;
    }
}
