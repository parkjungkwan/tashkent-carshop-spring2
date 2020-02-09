package texas.sbv.car.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.social.Social;
import texas.sbv.car.social.Thumb;

@Component @Lazy @Entity
@Getter @Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userSeq")
public class User implements UserDetails {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USERSEQ") @NotNull private Long userSeq;
    @Column(name = "USERID", unique = true, length = 25) @NotNull private String userid;
    @Column(name = "PASSWD") @NotNull private String passwd;
    @Column(name = "NAME", length = 30) @NotNull private String name;
    @Column(name = "EMAIL", length=100) @NotNull private String email;
    @Column(name = "AUTH") @NotNull private int auth;
    @Column(name = "GENDER", length=10)  private String gender;
    @Column(name = "BIRTHMONTH", length=10)  private String birthMonth;
    @Column(name = "REGION", length=10)  private String region;




    @OneToMany(mappedBy = "userSeq", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Social> socials = new ArrayList<>();
    @OneToMany(mappedBy = "userSeq", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Thumb> thumbs = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userid;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.passwd;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}
