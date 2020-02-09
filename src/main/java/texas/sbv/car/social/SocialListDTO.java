package texas.sbv.car.social;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocialListDTO {
    private Long boardSeq;
    private String boardDate, carCode, carName, boardContent, boardImg, userName;
    private int thumbCount;
}
