package texas.sbv.car.carbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import texas.sbv.car.record.Record;
import texas.sbv.car.record.RecordRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Lazy
public class CarBookServiceImpl implements CarBookService {
    @Autowired RecordRepository recordRepository;

    @Override
    public List<Record> getRecords(long id) {
        return recordRepository.findByMycarId(id).stream()
                .sorted(Comparator.comparing(Record::getRecordDate).reversed())
                .collect(Collectors.toList());
    }
}
