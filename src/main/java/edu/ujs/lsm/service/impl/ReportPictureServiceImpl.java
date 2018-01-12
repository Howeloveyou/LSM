package edu.ujs.lsm.service.impl;

import edu.ujs.lsm.dao.ReportPictureMapper;
import edu.ujs.lsm.model.ReportPicture;
import edu.ujs.lsm.service.ReportPictureService;
import edu.ujs.lsm.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/12.
 */
@Service("reportPictureService")
@Transactional
public class ReportPictureServiceImpl extends AbstractService<ReportPicture> implements ReportPictureService {
    @Resource
    private ReportPictureMapper reportPictureMapper;

}
