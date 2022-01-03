package com.harry.fileupload.controller;

import com.harry.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file){
        if(null == file || "".equals(file)){
            return "请选择文件！";
        }
        return fileService.upLoad(file);

    }

    /**
     * 下载具体文件
     * @param response
     */
    @RequestMapping("download")
    public void doweload(HttpServletResponse response) {
        try{
            fileService.downLoad(response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
