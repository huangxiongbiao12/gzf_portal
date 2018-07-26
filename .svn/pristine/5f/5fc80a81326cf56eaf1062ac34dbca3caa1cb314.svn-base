package com.gzf.config.web;

import com.gzf.authorization.annotation.Disauth;
import com.gzf.util.Templater;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Administrator on 2017/9/4.
 */
@RestController
@RequestMapping("/")
public class ConfigController {

    private String charset = "UTF-8";

    @Disauth
    @RequestMapping("config")
    public String config(){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("config.json");
        if(stream != null){
            final char[] buffer = new char[1024];
            StringBuilder builder = new StringBuilder();
            try {
                Reader reader = new InputStreamReader(stream, charset);
                int len = -1;
                while((len = reader.read(buffer, 0, buffer.length)) >= 0){
                    builder.append(buffer, 0, len);
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return Templater.ANGULAR.solid(builder.toString(), null);
        }
        return "";
    }
}
