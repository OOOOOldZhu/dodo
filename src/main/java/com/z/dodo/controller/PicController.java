package com.z.dodo.controller;

import com.z.dodo.bean.Data;
import com.z.dodo.tool.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

@RestController
@RequestMapping(value = "/api")
public class PicController {

    @GetMapping(value = "/pic")
    public InputStream get(String picName) {
        try{
            return getResource(picName);
        }catch (Exception e){
//            return new Data().setCode(500).setResult(e.toString());
            Log.d(e.toString());
            return null;
        }
    }

    private InputStream getResource(String picName) {
        //URL url = this.getClass().getClassLoader().getResource(picName);//获取文件路径
        //String fileUrl = this.getClass().getResource(picName).getFile();
        InputStream in = getClass().getClassLoader().getResourceAsStream(picName);
        return in;
    }
}