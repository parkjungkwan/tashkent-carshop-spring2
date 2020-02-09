package texas.sbv.car.social;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@Lazy
public interface SocialService {
    List<SocialListDTO> allList();
    String[] thumbed(String userid);
    SocialDetailDTO loadBoard(String boardSeq);
    void writeContent(SocialWriteDTO param);
    File makeDir(String t, String u);
    File makeFile(File t, String u);
    void updateContent(String boardSeq, SocialWriteDTO socialWriteDto);
    void deleteContent(String boardSeq);
    void thumbUp(String boardSeq, String userid);
    void thumbDown(String boardSeq, String userid);
}
