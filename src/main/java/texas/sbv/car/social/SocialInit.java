package texas.sbv.car.social;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import texas.sbv.car.car.Car;
import texas.sbv.car.car.CarRepository;
import texas.sbv.car.user.User;
import texas.sbv.car.user.UserRepository;
import texas.sbv.car.util.SocialDummy;


@Order(value=4)
@Component
public class SocialInit implements ApplicationRunner {
    private SocialRepository socialRepository;

    public SocialInit(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carsRepository;
    @Autowired
    ThumbRepository thumbRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 18844 --- [           SocialInit ]         : SocialInit Start ");

        if (userRepository.count() != 0 && carsRepository.count() != 0) {
            //socialBoard 테이블을 지운 후 social더미만 먼저 실행

            SocialDummy socialDummy = new SocialDummy();
            Iterable<User> users = userRepository.findAll();
            List<User> user = new ArrayList<>();
            for (User u : users) {
                user.add(u);
            }
            Iterable<Car> cars = carsRepository.findAll();
            List<Car> car = new ArrayList<>();
            for (Car c : cars) {
                car.add(c);
            }
            long count = socialRepository.count();
            if (count == 0) {
                for (int i = 0; i < 10; i++) {
                    for (Social s : socialDummy.crawlingBoard(user, car)) {
                        socialRepository.save(s);
                    }
                }
            }
            Iterable<Social> socials = socialRepository.findAll();
            List<Social> socialList = new ArrayList<>();
            for (Social s : socials) {
                socialList.add(s);
            }
            socialList.stream().sorted(Comparator.comparing(Social::getBoardSeq)).collect(Collectors.toList());
            List<Social> commentedSocialList = new ArrayList<>();
            commentedSocialList.clear();
            for (int i = socialList.size() - 1; i > socialList.size() - 30; i--) {
                commentedSocialList.add(socialList.get(i));
            }


            long thumbCount = thumbRepository.count();
            if (thumbCount == 0) {
                Collections.shuffle(user);
                List<User> thumbUserList = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    thumbUserList.add(user.get(i));
                }
                for (User u : thumbUserList) {
                    Collections.shuffle(commentedSocialList);
                    for (int i = 0; i < 20; i++) {
                        Thumb thumb = new Thumb();
                        thumb.setUserSeq(u);
                        thumb.setBoardSeq(commentedSocialList.get(i));
                        thumbRepository.save(thumb);
                    }
                }
            }
        }
        System.out.println( formattedTime1 + "  INFO 18844 --- [           SocialInit ]         : SocialInit End ");
    }
}


