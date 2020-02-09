package texas.sbv.car.car;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
@Lazy
public interface CarService {
    Map<String, List<Car>> getCategoryByCarType(Iterable<Car> cars);

    Map<String, Map<String, Long>> getCategory1(Iterable<Car> cars);

    List<Car> findAllByDistinct(List<Car> carsList);

    List<Car> findAllByDistinct(Iterable<Car> cars);

    <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor);

    Map<String, Long> getCategory2(String param);

    Map<String, Long> getCategory3(String param);

    List<SearchDetailCondition> findByMakecd(List<Car> carsList);

    List<SearchDetailCondition> findByModelWithCount(List<Car> carsList, String code);

    List<SearchDetailCondition> findByModelCategory(List<Car> carsList, String code);

    List<SearchDetailCondition> findByModelNMCategory(List<Car> carsList, String name);

    List<Car> findCarWithFuleType(List<Car> carsList);

    List<Car> findCarWithCenterRegionCode(List<Car> carsList);

    List<SearchDetailCondition> findByMakecdWithCount(List<Car> carsList);

    List<Car> findAllCategory(List<Car> carsList);

    List<Car> findCarBySelectedCategory(List<Car> carsList, String category);

    List<Car> findCarBySelectedMaker(List<Car> carsList, String code);

    List<Car> findCarBySelectedModelNM(List<Car> carsList, String modelCode);

    List<Car> findCarBySelectedFuelType(List<Car> carsList, String code);

    List<Car> findCarBySelectedRegion(List<Car> carsList, String code);

    List<Car> findCarBySelectedModel(List<Car> carsList, String code);

    List<Car> findCarBySelectedMakerNM(List<Car> carsList, String name);

    Map<String,Map<String,List<Car>>> findMakerAndModelByModelText(String modelnmText);

}
