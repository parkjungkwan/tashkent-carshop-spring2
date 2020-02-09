package texas.sbv.car.car;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
public class SearchCondition {

    private List<SearchDetailCondition> categoryList;
    private List<SearchDetailCondition> modelList;
    private List<SearchDetailCondition> fuelTypeList;
    private List<SearchDetailCondition> regionList;
    private String carcd;
    private String findKey;
    private String maker;
    private int pageLimit;
    private String orderBySub;
    private String modelText;
    private SearchDetailCondition minPrice;
    private SearchDetailCondition maxPrice;
    private SearchDetailCondition minMilage;
    private SearchDetailCondition maxMilage;
}
