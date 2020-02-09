package texas.sbv.car.magazine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.car.car.Car;
import texas.sbv.car.car.CarRepository;
import texas.sbv.car.car.CarService;
import texas.sbv.car.proxy.Trunk;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/magazine")
public class ArticleController {
    @Autowired Trunk<Object> trunk;
    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;
    @Autowired ExtractedWordRepository extractedWordRepository;
    @Autowired List<ExtractedWord> extractedWordList;
    @Autowired List<Car> cars;
    @Autowired CarRepository carsRepository;
    @Autowired CarService carsService;
    @Autowired List<Article> articleList;

    @GetMapping("/init")
    public Map<String, Object> init () {
        trunk.clear();
        extractedWordList = (List<ExtractedWord>) extractedWordRepository.findAll();
        articleList = (List<Article>) articleRepository.findAll();
        trunk.put(Arrays.asList("articles","extractedWordList"), Arrays.asList(articleList , extractedWordList));
        return trunk.get();
    }

    @GetMapping("/AIEditer/{keyword}")
    public Map<String, Object> aiEditer (@PathVariable String keyword) {
        trunk.clear();
        List<Car> tempCars = new ArrayList<>();
        List<Article> tmpArticle = new ArrayList<>();
        cars = carsService.findAllByDistinct((List<Car>) carsRepository.findAll());
        cars.forEach(item ->{
            if(item.getTruckName().contains(keyword.trim()))
                tempCars.add(item);
        });

        trunk.put(Arrays.asList("carsList"), Arrays.asList(tempCars.stream().limit(16)));
        articleList = (List<Article>) articleRepository.findAll();
        articleList.forEach(item -> {
            if(item.getSubject().contains(keyword))
            tmpArticle.add(item);
        });

        trunk.put(Arrays.asList("articles"), Arrays.asList(tmpArticle));

        return trunk.get();
    }

}
