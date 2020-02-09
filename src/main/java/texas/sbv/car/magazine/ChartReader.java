package texas.sbv.car.magazine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import texas.sbv.car.proxy.Trunk;

public class ChartReader {
    @Autowired
    Trunk<String> trunk;

    public Map<String, Object>  fileReader ( String fileName ) {

        Map <String , Object> resultMap = new HashMap<>();
        Map <String , Object> vlaueMap = new HashMap<>();
        List<String> xSubject = new ArrayList<>();
        List<Object> yValueTmp = new ArrayList<>();
        List<Object> yValue = new ArrayList<>();

        File file = new File(String.format(
                "C:%sSTSWorkspace%sTeamRPM%scsv%s%s.csv",
                File.separator,
                File.separator,
                File.separator,
                File.separator,
                fileName));

        try {
            int cnt =0;
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line="";

                while ((line = br.readLine()) !=null ) {

                    if (cnt == 2 ) {
                        yValue.clear();
                        String [] temp = line.split(",\"",-1);
                        for (int i = 3; i < temp.length ; i++) {
                            yValueTmp.add(temp[i]);
                            System.out.println(temp[i]);
                        }
                    }
                    if ( cnt > 7 ) {
                        String [] value = new String[13];
                        if(line.contains("\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"")) break;
                        String [] temp = line.split(",\"",-1);
                        xSubject.add(temp[2].replace("\"",""));
                        for (int i = 3 ; i < temp.length ; i++) {
                            value[i-3] = temp[i].replaceAll("[^0-9]", "");
                            System.out.println(value[i-3]);
                        }
                        yValue.add(value);
                    }
                    cnt++;
                }
                resultMap.put("labels" , yValueTmp );
                resultMap.put( "modelNM" , xSubject );
                resultMap.put("valueofModel" , yValue );
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


}
