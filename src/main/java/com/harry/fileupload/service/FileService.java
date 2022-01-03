package com.harry.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface FileService {

    String upLoad(MultipartFile file);

    void downLoad(HttpServletResponse response) throws UnsupportedEncodingException;

}
