package texas.sbv.car.employee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class EmployeeInit implements ApplicationRunner {
    private EmployeeRepository employeeRepository;

    public EmployeeInit(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 18844 --- [           EmployeeInit ]         : EmployeeInit Start ");


        long count = employeeRepository.count();
        List<String> code= employeeRepository.findCenterCode();
        code.forEach(el->{


        if (count == 0) {
            String url = "https://www.kcar.com/directcenter/directcenter_detail.do?i_sCenterCode="+el;
            Document doc = null;

            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements element = doc.select("span.employee_name");
            Elements element2 = doc.select("span.employee_txt");
            Elements element3 = doc.select("span.img>img");
            Elements element4 = doc.select("h3.center_name");


            Iterator<Element> ie1 = element.iterator();
            Iterator<Element> ie2 = element2.iterator();
            Iterator<Element> ie3 = element3.iterator();


            for (int j=0;j<element.size()/2;j++) {
                Employee employee = new Employee();
                if(j>9) {
                    employee.setEmCode(el + String.valueOf(j));
                }else{
                    employee.setEmCode(el +'0'+ String.valueOf(j));
                }
                employee.setCenterCode(el);
                employee.setCenterName(element4.text());
                employee.setEmName(ie1.next().text());
                employee.setEmPosition(ie2.next().text());
                employee.setEmImg(ie3.next().attr("src"));
                employeeRepository.save(employee);


            }
        }
        });
        System.out.println( formattedTime1 + "  INFO 18844 --- [           EmployeeInit ]         : EmployeeInit End ");
    }
}