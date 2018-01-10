package edu.ujs.lsm.service;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.core.Service;

import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-9
 * @version V1.0
 */
public interface RecordService extends Service<Record> {

    List<Record> findRecord(Record record);
}
