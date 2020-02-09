package texas.sbv.car.car;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Lazy
@Table(name = "CAR")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "CID") private Long cid;
    @NotNull @Column(name = "CARCD", length = 12) private String carcd;
    @Column(name = "OPTIONCD", length = 500) private String optioncd;
    @Column(name = "CATEGORYCD", length = 3) private String categorycd;
    @Column(name = "CENTER_CODE", length = 3) private String centerCode;
    @Column(name = "MIDDLE_IMG", length = 100) private String middleImg;
    @Column(name = "ELAN_PATH", length = 100) private String elanPath;
    @Column(name = "EXTERIOR_COLORDCD", length = 10) private String exteriorColordcd;
    @Column(name = "CENTER_REGION", length = 10) private String centerRegion;
    @Column(name = "ECC_REG_DTM", length = 14) private String eccRegDtm;
    @Column(name = "BEGIN_YEAR") private Integer beginYear;
    @Column(name = "MAKECD", length = 3) private String makecd;
    @Column(name = "SMALL_IMG", length = 100) private String smallImg;
    @Column(name = "MODELNM", length = 45) private String modelnm;
    @Column(name = "OPTIONCD_NAME", length = 1500) private String optioncdName;
    @Column(name = "CAR_TYPE", length = 3) private String carType;
    @Column(name = "PN_MOBILE", length = 16) private String pnMobile;
    @Column(name = "USERNM", length = 8) private String usernm;
    @Column(name = "MILEAGE") private Integer milage;
    @Column(name = "SIMPLE_COMMENT", length = 100) private String simpleComment;
    @Column(name = "MODELNM_TEXT", length = 100) private String modelnmText;
    @Column(name = "TRANSMISSIONCD", length = 100) private String transmissioncd;
    @Column(name = "CAR_NUMBER", length = 9) private String carNumber;
    @Column(name = "PRICE") private Integer price;
    @Column(name = "FUEL_TYPED_NAME", length = 9) private String fuleTypedName;
    @Column(name = "CAR_URL", length = 100) private String carUrl;
    @Column(name = "FULE_TYPED", length = 3) private String fuelTyped;
    @Column(name = "CENTER_REGION_CODE", length = 10) private String centerRegionCode;
    @Column(name = "TRUCK_NAME", length = 100) private String truckName;
    @Column(name = "CATEGORYNM", length = 50) private String categorynm;
    @Column(name = "EXTERIOR_COLORNM", length = 50) private String exteriorColornm;
    @Column(name = "HOT_MARKCD", length = 15) private String hotMarkcd;
    @Column(name = "TRANSMISSIONCD_NAME", length = 10) private String transmissioncdName;
    @Column(name = "PASS_CNT", length = 10) private String passCnt;
    @Column(name = "MODELCD", length = 3) private String modelcd;
    @Column(name = "REC_COMMENT_CD", length = 3) private String recCommentCd;
    @Column(name = "MAKENM", length = 20) private String makenm;
    @Column(name = "PN", length = 16) private String pn;
    @Column(name = "MFR_DATE", length = 60) private String mfrDate;
    @Column(name = "MODEL_GRP_CD", length = 50) private String modelGrpCd;
    @Column(name = "CENTER_NAME", length = 20) private String centerName;
    @Column(name = "MODEL_GRP_NM", length = 100) private String modelGrpNm;

    @Builder
    public Car(
            String carcd, String optioncd, String categorycd, String centerCode, String middleImg, String elanPath,
            String exteriorColordcd, String centerRegion, String eccRegDtm, int beginYear, String makecd,
            String smallImg, String modelnm, String optioncdName, String carType, String pnMobile, String usernm,
            int milage, String simpleComment, String modelnmText, String transmissioncd, String carNumber,
            int price, String fuleTypedName, String carUrl, String fuelTyped, String centerRegionCode,
            String truckName, String categorynm, String exteriorColornm, String hotMarkcd, String transmissioncdName,
            String passCnt, String modelcd, String recCommentCd, String makenm, String pn, String mfrDate,
            String modelGrpCd, String centerName, String modelGrpNm
    ) {
        Assert.hasText(carcd, "carcd must not be empty");

        this.centerRegion = centerRegion;
        this.modelGrpNm = modelGrpNm;
        this.carcd = carcd;
        this.optioncd = optioncd;
        this.categorycd = categorycd;
        this.centerCode = centerCode;
        this.middleImg = middleImg;
        this.elanPath = elanPath;
        this.exteriorColordcd = exteriorColordcd;
        this.centerCode = centerCode;
        this.eccRegDtm = eccRegDtm;
        this.beginYear = beginYear;
        this.makecd = makecd;
        this.smallImg = smallImg;
        this.modelnm = modelnm;
        this.optioncdName = optioncdName;
        this.carType = carType;
        this.pnMobile = pnMobile;
        this.usernm = usernm;
        this.milage = milage;
        this.modelnmText = modelnmText;
        this.transmissioncd = transmissioncd;
        this.simpleComment = simpleComment;
        this.price = price;
        this.fuleTypedName = fuleTypedName;
        this.carUrl = carUrl;
        this.fuelTyped = fuelTyped;
        this.centerRegionCode = centerRegionCode;
        this.truckName = truckName;
        this.categorynm = categorynm;
        this.exteriorColornm = exteriorColornm;
        this.hotMarkcd = hotMarkcd;
        this.transmissioncdName = transmissioncdName;
        this.passCnt = passCnt;
        this.modelcd = modelcd;
        this.recCommentCd = recCommentCd;
        this.makenm = makenm;
        this.pn = pn;
        this.mfrDate = mfrDate;
        this.modelGrpCd = modelGrpCd;
        this.centerName = centerName;
        this.modelGrpCd = modelGrpCd;
        this.carNumber = carNumber;
    }



}
