package texas.sbv.car.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Lazy
public class CarServiceImpl implements CarService {
    @Autowired private CarRepository carsRepository;

    @Override
    public Map<String, List<Car>> getCategoryByCarType(Iterable<Car> cars) {
        return StreamSupport.stream(cars.spliterator(), false)
                .filter(distinctByKey(Car::getMakenm))
                .collect(Collectors.groupingBy(Car::getCarType));
    }

    @Override
    public Map<String, Map<String, Long>> getCategory1(Iterable<Car> cars) {
        return StreamSupport.stream(cars.spliterator(), false)
                .collect(Collectors.groupingBy(Car::getCarType,
                        Collectors.groupingBy(Car::getMakenm,Collectors.counting())));
    }
    @Override
    public Map<String, Long> getCategory2(String param) {
        return StreamSupport.stream(carsRepository.findByMakenm(param).spliterator(), false)
                .collect(Collectors.groupingBy(Car::getModelGrpNm, Collectors.counting()));
    }
    @Override
    public Map<String, Long> getCategory3(String param) {
        return StreamSupport.stream(carsRepository.findByModelGrpNm(param).spliterator(), false)
                .collect(Collectors.groupingBy(Car::getModelnmText, Collectors.counting()));
    }

    @Override
    public List<Car>  findAllByDistinct(List<Car> carsList) {
        return carsList.stream()
                .filter(distinctByKey(Car::getCarcd))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findAllByDistinct(Iterable<Car> cars) {
        return null;
    }

    @Override
    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Override
    public List<SearchDetailCondition> findByMakecdWithCount(List<Car> carsList) {
        List<SearchDetailCondition> tmpMakecd = findByMakecd(carsList);
        for (SearchDetailCondition detailCondition : tmpMakecd) {
            int cnt = 0;
            for (Car cars : carsList) {
                if (detailCondition.getCode().equals(cars.getMakecd())) cnt++;
            }
            detailCondition.setCount(cnt);
        }
        return tmpMakecd;
    }

    @Override
    public List<SearchDetailCondition> findByMakecd(List<Car> carsList) {
        List<SearchDetailCondition> tmpMakecd = new ArrayList<>();
        for (Car cars : carsList.stream()
                .filter(distinctByKey(Car::getMakecd))
                .collect(Collectors.toList())) {
            SearchDetailCondition tmpCondition = new SearchDetailCondition();
            tmpCondition.setCode(cars.getMakecd());
            tmpCondition.setName(cars.getMakenm());
            tmpMakecd.add(tmpCondition);
        }
        return tmpMakecd;
    }

    @Override
    public List<SearchDetailCondition> findByModelWithCount(List<Car> carsList , String code) {
        List<SearchDetailCondition> tmpModelcd = findByModelCategory(carsList , code);
        for (SearchDetailCondition detailCondition : tmpModelcd) {
            int cnt = 0;
            for (Car cars : findCarBySelectedMaker( carsList , code ).stream()
                    .collect(Collectors.toList())) {
                if (detailCondition.getCode().equals(cars.getModelGrpCd())) cnt++;
            }
            detailCondition.setCount(cnt);
        }
        return tmpModelcd;
    }

    @Override
    public List<SearchDetailCondition> findByModelCategory(List<Car> carsList, String code) {
        List<SearchDetailCondition> tmpModelGrpNm = new ArrayList<>();
        for (Car cars : findCarBySelectedMaker(carsList,code).stream()
                .filter(distinctByKey(Car::getModelGrpNm))
                .collect(Collectors.toList())) {
            SearchDetailCondition tmpCondition = new SearchDetailCondition();
            tmpCondition.setCode(cars.getModelGrpCd());
            tmpCondition.setName(cars.getModelGrpNm());
            tmpCondition.setBigCategory(cars.getMakecd());
            tmpModelGrpNm.add(tmpCondition);
        }
        return tmpModelGrpNm;
    }

    @Override
    public List<SearchDetailCondition> findByModelNMCategory(List<Car> carsList, String name) {
        List<SearchDetailCondition> tmpModelGrpNm = new ArrayList<>();
        for (Car cars : findCarBySelectedMakerNM( carsList , name ).stream()
                .filter(distinctByKey(Car::getModelGrpCd))
                .collect(Collectors.toList())) {
            SearchDetailCondition tmpCondition = new SearchDetailCondition();
            tmpCondition.setCode(cars.getModelGrpCd());
            tmpCondition.setName(cars.getModelGrpNm());
            tmpCondition.setBigCategory(cars.getMakecd());
            tmpModelGrpNm.add(tmpCondition);
        }
        return tmpModelGrpNm;
    }

    @Override
    public List<Car> findCarWithFuleType(List<Car> carsList) {
        return carsList.stream()
                .filter(distinctByKey(Car::getFuelTyped))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarWithCenterRegionCode(List<Car> carsList) {
        return carsList.stream()
                .filter(distinctByKey(Car::getCenterRegionCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findAllCategory(List<Car> carsList) {
        return carsList.stream()
                .filter(distinctByKey(Car::getCategorycd))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarBySelectedCategory(List<Car> carsList, String categorycode) {
        return carsList.stream()
                .filter(cars -> categorycode.equals(cars.getCategorycd()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarBySelectedModel(List<Car> carsList, String modelCode) {
        return carsList.stream()
                .filter(cars -> modelCode.equals(cars.getModelGrpCd()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Car> findCarBySelectedModelNM(List<Car> carsList, String modelName) {
        return carsList.stream()
                .filter(cars -> modelName.equals(cars.getModelGrpNm()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarBySelectedFuelType(List<Car> carsList, String fuelTypecode) {
        return carsList.stream()
                .filter(cars -> fuelTypecode.equals(cars.getFuelTyped()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarBySelectedRegion(List<Car> carsList, String regioncode) {
        return carsList.stream()
                .filter(cars -> regioncode.equals(cars.getCenterRegionCode()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarBySelectedMaker(List<Car> carsList, String code) {
        return carsList.stream()
                .filter(cars -> code.equals(cars.getMakecd()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Car> findCarBySelectedMakerNM(List<Car> carsList, String name) {
        return carsList.stream()
                .filter(cars -> name.equals(cars.getMakenm()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String,Map<String,List<Car>>> findMakerAndModelByModelText(String modelnmText) {
        return StreamSupport.stream(carsRepository.findByModelnmText(modelnmText).spliterator(), false)
                .collect(Collectors.groupingBy(Car::getMakenm, Collectors.groupingBy(Car::getModelGrpNm)));
    }


}
