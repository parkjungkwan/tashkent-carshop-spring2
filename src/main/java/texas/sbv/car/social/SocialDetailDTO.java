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
public class SocialDetailDTO {
    private String carName, boardContent, boardImg, boardDate, userName, userid;
    private int thumbCount;
}
