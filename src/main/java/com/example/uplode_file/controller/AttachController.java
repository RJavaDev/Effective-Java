package com.example.uplode_file.controller;

import com.example.uplode_file.dto.AttachDownloadDTO;
import com.example.uplode_file.dto.AttachResponseDTO;
import com.example.uplode_file.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
@RequiredArgsConstructor
public class AttachController {

    private final AttachService service;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file){
        AttachResponseDTO attach = service.saveToSystem(file);
        return ResponseEntity.ok().body(attach);
    }


    @GetMapping(value = "/public/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return service.open(fileName);
    }


    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        AttachDownloadDTO result = service.download(fileName);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(result.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + result.getResource().getFilename() + "\"").body(result.getResource());
    }



    @GetMapping("/get")
    public ResponseEntity<?> getWithPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<AttachResponseDTO> result = service.getWithPage(page, size);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteById(@PathVariable("fileName") String fileName) {
        String result = service.deleteById(fileName);
        return ResponseEntity.ok(result);
    }

}
