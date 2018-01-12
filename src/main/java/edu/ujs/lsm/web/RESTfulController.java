package edu.ujs.lsm.web;

import edu.ujs.lsm.core.Result;
import edu.ujs.lsm.core.ResultCode;
import edu.ujs.lsm.model.ReportPicture;
import edu.ujs.lsm.service.ReportPictureService;
import edu.ujs.lsm.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Howe
 * @version V1.0
 * @Description: TODO
 * @date 2018-1-11
 */
@RestController
@RequestMapping("/api")
public class RESTfulController {

    @Resource
    private StudentService studentService;

    @Resource
    private ReportPictureService reportPictureService;

    @Value("${web.upload-path}")
    private String webUploadPath;

    /*
    *签到接口
    * @sid 学号
    * @rid 阅览室ID
    * @seNum 座位号
    */
    @RequestMapping("/signIn")
    public Object signIn(
            @RequestParam String sid,
            @RequestParam Integer rid,
            @RequestParam String seNum
    ) throws ParseException {
        Result result = new Result();
        int flag = studentService.singIn(sid,rid,seNum);
        switch (flag){
            case 0:
                result.setCode(ResultCode.FAIL);
                result.setMessage("您已签到，请勿重读签到！");
                break;
            case 1:
                result.setCode(ResultCode.SUCCESS);
                result.setMessage("签到成功！");
                break;
            case 2:
                result.setCode(ResultCode.FAIL);
                result.setMessage("对不起，您已超过预约时间！");
                break;
            case 3:
                result.setCode(ResultCode.FAIL);
                result.setMessage("对不起，签到失败！请确认您的预约是否存在或位置是否坐对！");
        }
        return result;
    }


    @PostMapping("/fileUpload")
    public Object fileUpload(
            @RequestParam MultipartFile file,
            @RequestParam String sid
            ) throws IOException {
        Result result = new Result();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")){
                //构造文件保存路径
                ReportPicture reportPicture = new ReportPicture();
                StringBuilder filePath = new StringBuilder(webUploadPath);
                filePath = filePath.append(sid).append(File.separator);
                // 获取图片的文件名
                String fileName = file.getOriginalFilename();
                // 获取图片的扩展名
                String extensionName = StringUtils.substringAfter(fileName, ".");
                // 新的图片文件名 = 获取时间戳+"."图片扩展名
                String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                File dest = new File(filePath.toString(), newFileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                //数据库保存路径
                String dbFilePath =filePath.deleteCharAt(filePath.length()-1).append("/").append(newFileName).toString();
                // 上传到指定目录
                file.transferTo(dest);
                reportPicture.setSid(sid);
                reportPicture.setDate(new Date());
                reportPicture.setImg(dbFilePath);
                reportPicture.setMark(1);
                reportPictureService.save(reportPicture);
                result.setCode(ResultCode.SUCCESS);
                result.setMessage("上传成功！");
                return result;
            }
        }
        result.setCode(ResultCode.FAIL);
        result.setMessage("上传失败！");
        return result;
    }
}
