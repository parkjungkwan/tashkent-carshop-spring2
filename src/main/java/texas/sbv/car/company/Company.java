package texas.sbv.car.company;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.employee.Employee;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Lazy
@Table(name = "companys")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "company_id") private Long companyId;
    @NotNull@Column(name = "center_code", length = 3) private String centerCode;
    @NotNull@Column(name = "center_name", length = 20) private String centerName;
    @NotNull@Column(name = "center_region", length = 10) private String centerRegion;
    @Column(name = "month_revenue") private int monthRevenue;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private List<Employee> employees;
}
