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
public class SocialWriteDTO {
    private String carCode, carName, boardContent, boardImgName, userid;
}
