package texas.sbv.car.carbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.car.record.Record;
import texas.sbv.car.record.RecordRepository;
import texas.sbv.car.user.User;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class CarBookController {
    @Autowired CarBookRepository carbookRepository;
    @Autowired CarBook carbook;
    @Autowired Record record;
    @Autowired CarBookService carbookService;
    @Autowired RecordRepository recordRepository;


    @PostMapping("/getMycar")
    public HashMap<String, Object> getMycar(@RequestBody User param){
        HashMap<String, Object> map = new HashMap<>();
        if(param.getUserid()!=null){
            carbook = carbookRepository.findBySeq(param.getUserSeq());

            if(carbook != null){
                map.put("result" , "success");
                map.put("mycar" , carbook);
                Iterable<Record> itRecord =  carbookService.getRecords(carbook.getMycarId());
                List<Record> records = new ArrayList<>();
                if(itRecord !=null){
                    for(Record r: itRecord){
                        records.add(r);
                    }
                    map.put("record", records);

                }
                return map;


            }
        }

        map.put("result", "fail");
        return map;

    }
    @PostMapping("/getRecord")
    public HashMap<String, Object> getRecord(@RequestBody CarBook param){
        HashMap<String, Object> map = new HashMap<>();
        Iterable<Record> records = carbookService.getRecords(param.getMycarId());

        List<Record> list = new ArrayList<>();
        for(Record r : records){
            list.add(r);

        }


        if(list != null){
            map.put("result" , "success");
            map.put("record" , list);
            return map;
        }
        map.put("result", "fail");
        return map;
    }
    @PostMapping("/insertRecord")
    public HashMap<String, Object> addRecord(@RequestBody Record param){
        HashMap<String, Object> map = new HashMap<>();
        record = recordRepository.save(param);
        if(record != null){
            map.put("rec", record);
            map.put("result" , "success");
        }
        else{
            map.put("result" , "fail");

        }
        return map;
    }
    @GetMapping("/deleteRecord")
    public HashMap<String, Object> delRecord(@PathVariable String recoId){

        HashMap<String, Object> map = new HashMap<>();
        long id = Long.parseLong(recoId);
        recordRepository.deleteRecordByRecordId(id);
        map.put("delete", "success");
        return map;

    }



}
