package com.gzf.service.iml;


import com.gzf.authorization.annotation.Disauth;
import com.gzf.util.storage.StorageProperties;
import com.gzf.util.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yangweiguang on 2017/6/14.
 */
@CrossOrigin(origins = "*")
@Controller
public class GlobalControler {
    private final StorageService storageService;

    @Autowired
    StorageProperties storageProperties;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public GlobalControler(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload_form")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        "/files/"+path.getFileName().toString())
                .collect(Collectors.toList()));

        return "upload_form";
    }
    @Disauth
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename()+"\"")
                .body(file);
    }

    @GetMapping("/files/{sonDir}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String sonDir, @PathVariable String filename) {
        filename = sonDir + "/" + filename;
        return serveFile(filename);
    }

    @PostMapping("/upload_form")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        String fileName = storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "/files/" + fileName;
    }


    @PostMapping("/upload_form2")
    @ResponseBody
    public Map<String,String> handleFileUpload2(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        String fileName = storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        Map map = new HashMap();
        map.put("data", "/files/" + fileName);
        return map;
    }

}
