package com.gzf.util.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    /**
     * 文件上传
     * @param file
     * @param sonDir 需要创建的子文件夹
     * @return
     */
    String store(MultipartFile file, String sonDir);

    void load(HttpServletResponse response, String path);

    void load(OutputStream os, String path);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
