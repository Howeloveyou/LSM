package edu.ujs.lsm.dao;

import edu.ujs.lsm.core.Mapper;
import edu.ujs.lsm.model.Record;
import org.springframework.stereotype.Repository;

@Repository("recordMapper")
public interface RecordMapper extends Mapper<Record> {
}