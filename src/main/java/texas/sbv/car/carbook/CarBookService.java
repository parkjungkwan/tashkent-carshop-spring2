package texas.sbv.car.carbook;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import texas.sbv.car.record.Record;

import java.util.List;

@Component
@Lazy
public interface CarBookService {

    public List<Record> getRecords(long id);
}
