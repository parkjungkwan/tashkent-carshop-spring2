package texas.sbv.car.revenue;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import texas.sbv.car.employee.EmployeeRepository;

@Component
public class RevenueInit implements ApplicationRunner {
    @Autowired
    private RevenueRepository revenueRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    private String month;
    private String day;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 18844 --- [           RevenueInit ]         : RevenueInit Start ");

       long count = revenueRepository.count();

        if (count == 0) {

            employeeRepository.findEmCode().forEach(el->{
                for(int year=2018;year<=2020;year++) {

                    if(year==2020){
                        for(int i =1;i<3;i++){
                            Revenue revenue = new Revenue();
                            revenue.setMonth(String.valueOf(year) + "0"+String.valueOf(i));
                            revenue.setEmCode(el);
                            revenue.setEmName(employeeRepository.findEmNameByEmCode(el));
                            revenue.setCenterCode(employeeRepository.findByEmCode(el));
                            revenue.setEmRevenue((int) (Math.random() * 100 + 1) * 1000);
                            revenueRepository.save(revenue);
                        }

                    }else {
                        for (int i = 1; i < 13; i++) {
                            Revenue revenue = new Revenue();
                            month = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                            revenue.setMonth(String.valueOf(year) + month);
                            revenue.setEmCode(el);
                            revenue.setEmName(employeeRepository.findEmNameByEmCode(el));
                            revenue.setCenterCode(employeeRepository.findByEmCode(el));
                            revenue.setEmRevenue((int) (Math.random() * 100 + 1) * 1000);
                            revenueRepository.save(revenue);
                        }
                    }
                }

            });

            System.out.println( formattedTime1 + "  INFO 18844 --- [           RevenueInit ]         : RevenueInit End ");

        }
    }
}