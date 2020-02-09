package texas.sbv.car.carbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import texas.sbv.car.record.Record;
import texas.sbv.car.record.RecordRepository;
import texas.sbv.car.user.User;
import texas.sbv.car.user.UserRepository;

@Order(value=3)
@Component
public class CarBookInit implements ApplicationRunner {
    @Autowired RecordRepository recordRepository;
    @Autowired UserRepository userRepository;
    private CarBookRepository carbookRepository;

    public CarBookInit(CarBookRepository carbookRepository) {
        this.carbookRepository = carbookRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 00001 --- [           CarbookInit ]         : CarbookInit Start ");

        mycarInit();
        recordInit();

        System.out.println( formattedTime1 + "  INFO 18844 --- [           CarbookInit ]         : CarbookInit End ");

    }
    public void mycarInit(){
        String[] brand ={ "현대" ,"기아" , "gm" , "르노" , "쌍용"};
        String[] hyundai ={"그랜저", "아반떼", "소나타", "산타페",
                "스타렉스","에쿠스", "투싼", "팰리세이드" };
        String[] kia = {"k3","k5","k7","k9","모닝" ,"모하비", "스포티지",
                "소렌토", "소울", "카니발", "프라이드"};
        String[] gm ={ "말리부", "스파크","올란도", "윈스톰", "임팔라",
                "카마로", "크루즈" ,"트랙스"};
        String[] rn ={"sm3","sm5","sm6","sm7","qm3","qm5","qm6"};
        String[] ss = {"렉스턴", "무쏘","체어맨","코란도","티볼리"};

        long count = carbookRepository.count();
        CarBook carbook = null;

        List<User> list = (List<User>) userRepository.findAll();

        if(count ==0 && userRepository.count()!=0){

            for(int i = 0 ; i < 500; i++){
                carbook = new CarBook();

                int ranBrand = (int)(Math.random()*5);
                carbook.setBrand(brand[ranBrand]);
                switch(carbook.getBrand()){
                    case "현대":
                        int ranHyundai = (int)(Math.random()*hyundai.length);
                        carbook.setModel(hyundai[ranHyundai]);
                        break;
                    case "기아":
                        int rankia = (int)(Math.random()*kia.length);
                        carbook.setModel(kia[rankia]);

                        break;
                    case "gm":
                        int ranGm = (int)(Math.random()*gm.length);
                        carbook.setModel(gm[ranGm]);
                        break;
                    case "르노":
                        int ranRN = (int)(Math.random()*rn.length);
                        carbook.setModel(rn[ranRN]);
                        break;
                    case "쌍용":
                        int ranSS = (int)(Math.random()*ss.length);
                        carbook.setModel(ss[ranSS]);
                        break;

                }
                carbook.setYear( String.valueOf(
                        (int)Math.floor(Math.random() * (19 - 10 + 1) + 10)
                ) );
                carbook.setMonth( String.valueOf(
                        (int)(Math.random()*12)+1
                ) );
                carbook.setDistance( String.valueOf(
                        (int)(Math.random()*99999)+1000
                ) );
                carbook.setUserSeq(list.get(i));


                carbookRepository.save(carbook);
            }
        }
    }
    public void recordInit(){
        Record record = null;
        long counter = recordRepository.count();
        Iterable<CarBook> carbooks = carbookRepository.findAll();
        List<CarBook> listCarbook =new ArrayList<>();
        for(CarBook c: carbooks){
            listCarbook.add(c);

        }


        if(counter == 0 && carbookRepository.count()!=0){
            for(int i = 0 ; i < 3000; i++){
                record = new Record();
                record.setRecordDate(randDate());
                record.setServiceCode(
                        (((int)(Math.random()*2))== 0 ?
                                "주유" : "정비")
                );
                record.setPrice(String.valueOf(
                        (int)((Math.random()*10)+1)*10000
                ));
                record.setMycarId(
                        listCarbook.get(
                                (int)(Math.random()*500))
                );
                recordRepository.save(record);

            }




        }

    }
    public String randDate(){

        String result = null;
        String year = String.valueOf(
                (int)Math.floor(Math.random() * (20 - 19 + 1) + 19)
        );
        int month =
                (int)(Math.random()*12)+1;
        int day =
                (int)(Math.random()*30) +1;

        result = year+((month<10 && month%10!=0)?"0"+month : month ) +
                ((day<10 && day%10!=0)? "0"+day:day);

        return result;

    }


}
