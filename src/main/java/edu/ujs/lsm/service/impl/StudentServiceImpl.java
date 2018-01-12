package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.RecordMapper;
import edu.ujs.lsm.dao.SeatMapper;
import edu.ujs.lsm.dao.StudentMapper;
import edu.ujs.lsm.model.Record;
import edu.ujs.lsm.model.Student;
import edu.ujs.lsm.service.StudentService;
import edu.ujs.lsm.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 * @Description: TODO
 * @author Howe
 * @date 2018-1-4
 * @version V1.0
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl extends AbstractService<Student> implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RecordMapper recordMapper;

    @Resource
    private SeatMapper seatMapper;

    public Student login (String sid,String psw){
        Student student = new Student();
        student.setSid(sid);
        student.setPassword(psw);
        List<Student> list = studentMapper.select(student);
        if (list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int singIn(String sid, Integer rid, String seNum) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Record record = new Record();
        int flag = 3;
        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+ 1) + "-" + (c.get(Calendar.DATE));
        logger.info(date);
        record.setDate(new java.sql.Date(format.parse(date).getTime()));
        record.setSid(sid);
        List<Record> list = recordMapper.select(record);
        logger.info(list.toString());
        if (list != null && list.size() > 0){
            record = list.get(0);
            switch (record.getMark()){
                case 0:
                    flag = 0;
                    break;
                case 1:
                    flag = cheackSeat(record,rid,seNum);
                    break;
                case 2:
                    flag = 2;
                    break;
            }

        }
        return flag;
    }

    private int cheackSeat(Record record, Integer rid, String seNum) {
        String Num = seatMapper.selectByPrimaryKey(record.getSeat()).getNumber();
        if (rid.equals(record.getRid())  && seNum.equals(Num)){
            record.setMark(0);
            recordMapper.updateByPrimaryKey(record);
            return 1;
        }
        return 3;
    }

}
