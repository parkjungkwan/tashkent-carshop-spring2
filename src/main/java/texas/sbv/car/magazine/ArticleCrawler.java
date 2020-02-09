package texas.sbv.car.magazine;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import texas.sbv.car.car.SearchDetailCondition;
import texas.sbv.car.proxy.Trunk;

@Component
public class ArticleCrawler {
    @Autowired Trunk<Object> trunk;
    private ArticleRepository articleRepository;
    private ExtractedWordRepository extractedWordRepository;

    public ArticleCrawler(ArticleRepository articleRepository , ExtractedWordRepository extractedWordRepository) {
        this.articleRepository = articleRepository;
        this.extractedWordRepository = extractedWordRepository;
    }

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36";

    public Map<String, Object> motorgraphComtroller()  {
        try {
            Map< String, String > map = new HashMap<>();
            int menuCnt = 0;

            for ( menuCnt = 1 ; menuCnt < 7 ; menuCnt++) {

                String motorgraph = "https://www.motorgraph.com/news/articleList.html?sc_section_code=S1N"+menuCnt+"&view_type=sm" ;
                // 2. SSL 체크
                if(motorgraph.indexOf("https://") >= 0){
                    ArticleCrawler.setSSL();
                }
                Connection.Response homePage;
                homePage = Jsoup.connect(motorgraph)
                        .method(Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();
                Document temp = homePage.parse();
                Elements articleListCntElement = temp.select("small.text-muted");
                map.put("S1N"+menuCnt , articleListCntElement.text().replaceAll("[^0-9]",""));
            }
            map.forEach(this::getMotorgraphNewcar);
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getMotorgraphNewcar( String menuName , String menuTotal )  {

        boolean flag = true;
        try {
            int pagecnt = 1;
            int year = 202000;
            int cnt = 0;

            while ( Integer.parseInt(String.valueOf(year).substring(0,4)) > 2017 && pagecnt < 51) {
                String motorgraph = "https://www.motorgraph.com/news/articleList.html?page=" + pagecnt + "&total="+menuTotal+"&sc_section_code="+ menuName
                        +"&sc_sub_section_code=&sc_serial_code=&sc_area=&sc_level=&sc_article_type=&sc_view_level=&sc_sdate=&sc_edate=&sc_serial_number=&sc_word=&sc_word2=&sc_andor=&sc_order_by=E&view_type=sm" ;
                Connection.Response homePage;
                homePage = Jsoup.connect(motorgraph)
                        .method(Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();
                Document entirePage = homePage.parse();
                Elements articleListCntElement = entirePage.select("small.text-muted");
                Elements articleList = entirePage.select("div.list-block");
                Article article = new Article();
                flag = (articleListCntElement.text() == "") ? true : false;
                for (Element item : articleList) {
                    article.setAricleId(menuName + "t" + menuTotal + cnt);
                    article.setSubject(item.select("div.list-titles").select("strong").text());
                    article.setWriter("motorgraph");
                    article.setWriteDate(item.select("div.list-dated").text().substring(item.select("div.list-dated").text().length() - 16, item.select("div.list-dated").text().length() - 9).replace("-",""));
                    article.setArticleImg("https://www.motorgraph.com/news" + item.select("div.list-image").select("img").attr("src").replace("./","/"));
                    article.setArticleHref("https://www.motorgraph.com" + item.select("div.list-image").select("a").attr("href").replace("./","/"));
                    year = Integer.parseInt(item.select("div.list-dated").text().substring(item.select("div.list-dated").text().length() - 16, item.select("div.list-dated").text().length() - 9).replace("-",""));
                    cnt++;
                    articleRepository.save(article);
                }
                pagecnt += 1;
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    public void extractor () {
        List<SearchDetailCondition> result = new ArrayList<>();
        String strToExtrtKwrd = null;
        for (Article item : articleRepository.findAll()) {
            strToExtrtKwrd += item.getSubject();
        }
        // init KeywordExtractor
        KeywordExtractor ke = new KeywordExtractor();
        // extract keywords
        KeywordList kl = ke.extractKeyword( strToExtrtKwrd , true);
        // print result
        for( int i = 0; i < kl.size(); i++ ) {
            SearchDetailCondition searchDetailCondition = new SearchDetailCondition();
            Keyword kwrd = kl.get(i);
            if (kwrd.getString().length() > 1 && kwrd.getCnt() > 2 ) {
                extractedWordRepository.save(new ExtractedWord( kwrd.getString() , kwrd.getCnt() ));
            }
        }
    }
    public void morphemeAnalyzer () throws Exception {
        // string to analyze
        String strToExtrtKwrd = null;
        for (Article item : articleRepository.findAll()) {
            strToExtrtKwrd += item.getSubject();
        }
        // init MorphemeAnalyzer
        MorphemeAnalyzer ma = new MorphemeAnalyzer();
        // create logger, null then System.out is set as a default logger
        ma.createLogger(null);
        // analyze morpheme without any post processing
        List ret = ma.analyze(strToExtrtKwrd);
        // refine spacing
        ret = ma.postProcess(ret);
        // leave the best analyzed result
        ret = ma.leaveJustBest(ret);
        // divide result to setences
        List stl = ma.divideToSentences(ret);
        // print the result
        for( int i = 0; i < stl.size(); i++ ) {
            Sentence st = (Sentence) stl.get(i);
            for( int j = 0; j < st.size(); j++ ) {
            }
        }

        ma.closeLogger();

    }


    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

}
