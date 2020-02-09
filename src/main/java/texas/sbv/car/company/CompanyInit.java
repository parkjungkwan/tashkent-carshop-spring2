package texas.sbv.car.company;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import texas.sbv.car.employee.EmployeeRepository;

@Order(value=1)
@Component
public class CompanyInit implements ApplicationRunner {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        SimpleDateFormat SystemTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String formattedTime1 = SystemTime.format (System.currentTimeMillis());

        System.out.println( formattedTime1 + "  INFO 18844 --- [           CompanyInit ]         : CompanyInit Start ");



        long count = companyRepository.count();
        if (count == 0) {
            List<String> code = companyRepository.findCenterCode();
            code.forEach(el -> {

                Company company = new Company();
                company.setCenterCode(el);
                company.setEmployees(employeeRepository.findByCenterCode(el));
                company.setCenterName(companyRepository.findByCenterCode(el));
                company.setCenterRegion(companyRepository.findCenterRigionByCenterCode(el));
                companyRepository.save(company);

            });
        }


        System.out.println( formattedTime1 + "  INFO 18844 --- [           CompanyInit ]         : CompanyInit End ");

    }

}