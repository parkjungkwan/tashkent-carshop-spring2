package texas.sbv.car.magazine;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import texas.sbv.car.util.MakeCarDummyList;

@Component
public class MagazineInit implements ApplicationRunner {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ExtractedWordRepository extractedWordRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 18844 --- [           MagazineInit ]         : MagazineInit Start ");

       MakeCarDummyList http = new MakeCarDummyList();
        Map<String, Map<String, Object>> map = new HashMap<>();
        ArticleCrawler crawler = new ArticleCrawler( articleRepository , extractedWordRepository );
        if (articleRepository.count() == 0 ) crawler.motorgraphComtroller();
        if (extractedWordRepository.count() == 0 ) crawler.extractor();

        System.out.println( formattedTime1 + "  INFO 18844 --- [           MagazineInit ]         : MagazineInit End ");

    }


}
