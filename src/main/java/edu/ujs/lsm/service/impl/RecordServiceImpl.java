package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.RecordMapper;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.service.RecordService;
import edu.ujs.lsm.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-9
 * @version V1.0
 */
@Service
@Transactional
public class RecordServiceImpl extends AbstractService<Record> implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    public List<Record> findRecord(Record record) {
        return recordMapper.select(record);
    }
}
