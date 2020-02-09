package texas.sbv.car.util;

import java.io.IOException;

import org.jsoup.Jsoup;

import texas.sbv.car.car.CarRepository;


public class MakeCarDummyList {
    private CarRepository carsRepository;
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36";
    public String sendPost(String targetUrl, String pageno) throws Exception {
        String jsonResult = "";
        try {

              jsonResult  = Jsoup
                    .connect(targetUrl)
                    .userAgent(USER_AGENT)
                    .data("price_tab_flag", "2")
                    .data("limit", "1")
                    .data("orderFlag", "true")
                    .data("orderby", "n_order:desc")
                    .data("wr_eq_v_usernm", "")
                    .data("pageno", pageno)
                    .ignoreContentType(true)
                    .execute()
                    .body();

        } catch( IOException e ) {
            e.printStackTrace();
        }
            return jsonResult;
    }
    public String getCarCount(String targetUrl) throws Exception {
        String result = "";
        try{
            result  = Jsoup
                    .connect(targetUrl)
                    .userAgent(USER_AGENT)
                    .data("price_tab_flag", "2")
                    .data("limit", "1")
                    .data("orderFlag", "true")
                    .data("orderby", "n_order:desc")
                    .data("wr_eq_v_usernm", "")
                    .data("pageno", "1")
                    .ignoreContentType(true)
                    .execute()
                    .body();
            result.split(",")[6].replaceAll("^[0-9]", "");
    } catch (Exception e) {
        e.printStackTrace();
    }
        return result.split(",")[6].replaceAll("[^0-9]", "");
    }



}
