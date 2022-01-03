package com.harry.fileupload.service.impl;

import com.harry.fileupload.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.NewThreadAction;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${filepath}")
    private String filePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public String upLoad(MultipartFile file) {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try(FileOutputStream out = new FileOutputStream(new File(filePath+ "/" +file.getOriginalFilename()));){
            out.write(file.getBytes());
        }catch(Exception e){
            e.printStackTrace();
            log.error("文件上传失败！");
            return "uploading failure";
        }
        log.info("文件上传成功");
        return "uploading success";
    }

    /**
     * 文件下载
     * @param response
     */
    @Override
    public void downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String downLoadFile = "Java程序员面试笔试真题库.pdf";
        String downLoadPath = "/Users/mac/Downloads/study";

        File file = new File(downLoadPath + "/" + downLoadFile);
        if(file.exists()){
            response.setContentType("application/octet-stream");
            response.setHeader("content-type","application/octet-stream");
            response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode(downLoadFile,"utf8"));
            byte[] buffer = new byte[1024];
            //输出流
            OutputStream os = null;
            try(FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);){
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
