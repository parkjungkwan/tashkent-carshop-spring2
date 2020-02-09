package texas.sbv.car.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.car.car.CarRepository;
import texas.sbv.car.company.CompanyRepository;

@RestController
@RequestMapping("/recommend")
@CrossOrigin(origins = "http://localhost:8081")
public class RecommendController {
    @Autowired
    CarRepository carsRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RecommendRepository recommendRepository;

    @GetMapping("/centerName/{region}")
    public List<String> centerRigion(@PathVariable String region){
        if(region.equals("경기")){
            region="경기/인천";
        }
        List<String> centerName =  companyRepository.findByCenterRegion(region);
        return centerName;
    }

    @GetMapping("/centerRigion")
    public List<String> centerName(){
        List<String> region =  companyRepository.findAllRigion();
        return region;

    }
    @PostMapping("/inputRecommend")
    public void inputRecommend(@RequestBody Recommend recommend){
        recommend.setCenterCode(companyRepository.findByCenterName(recommend.getCenterName()));
        recommendRepository.save(recommend);


    }
    @GetMapping("/customerList/{userid}")
    public List<Recommend> customerList(@PathVariable String userid){
        List<Recommend> list=recommendRepository.findByCenterCode(userid);
        list.sort((a,b) -> b.getRecoSeq().compareTo(a.getRecoSeq()));
        return  list;

    }
    @PostMapping("/recommendRemove")
    public void recommendRemove(@RequestBody List<Recommend> recommends){
        recommends.forEach(el->{
            recommendRepository.deleteById(el.getRecoSeq());
        });
    }
}