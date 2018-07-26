package com.gzf.util.storage;

import com.gzf.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger logger = Logger.getLogger(FileSystemStorageService.class) ;

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }


    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String fileName = System.currentTimeMillis() +"-"+ file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            return fileName ;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public String store(MultipartFile file, String sonDir) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Path root = null;
            StorageProperties storageProperties = new StorageProperties();
            if(StringUtil.isNullOrEmpty(sonDir)){
                root = Paths.get(storageProperties.getLocation());
            }else{
                root = Paths.get(storageProperties.getLocation() + File.separator + sonDir);
                if(!Files.exists(root)){
                    Files.createDirectory(root);
                }
            }
            String fileName = System.currentTimeMillis() +"-"+ file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(fileName));
            if(StringUtil.isNullOrEmpty(sonDir)){
                return fileName ;
            }else{
                return sonDir + "/" + fileName;
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public void load(HttpServletResponse response, String path) {
        try {
            this.load(response.getOutputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(OutputStream os, String path) {
        Resource res = this.loadAsResource(path);
        try {
            File file = res.getFile();
            if(file.exists()){
                FileInputStream fis = null;
                FileChannel channel = null;
                try {
                    fis = new FileInputStream(file);
                    channel = fis.getChannel();
                    //缓冲区
                    ByteBuffer buf = ByteBuffer.allocate((int)channel.size());
                    while(channel.read(buf) > 0){}
                    //输出
                    os.write(buf.array());
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    if(channel != null){
                        try{
                            channel.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(fis != null){
                        try{
                            fis.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(os != null){
                        try{
                            os.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        System.out.println(filename+"11111111111111111111111111");
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        /* FileSystemUtils.deleteRecursively(rootLocation.toFile()); */
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch(FileAlreadyExistsException e){
            logger.info("目录"+rootLocation.getFileName()+"已经创建!");
        }catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
