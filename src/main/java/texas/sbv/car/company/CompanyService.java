package texas.sbv.car.company;

import java.util.List;

import org.springframework.stereotype.Component;

import texas.sbv.car.car.Car;
import texas.sbv.car.recommend.Recommend;

@Component
public interface CompanyService {
    public List<Car> getBestCarList(Recommend recommend);
}
