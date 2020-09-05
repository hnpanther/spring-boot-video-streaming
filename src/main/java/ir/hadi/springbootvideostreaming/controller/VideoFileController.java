package ir.hadi.springbootvideostreaming.controller;

import ir.hadi.springbootvideostreaming.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/video")
public class VideoFileController {

    private VideoFileService videoFileService;

    public VideoFileController(@Autowired  VideoFileService videoFileService) {
        this.videoFileService = videoFileService;
    }

    @GetMapping("/player")
    public String getVideo() {
        return "video";
    }

    @GetMapping("/stream/file-system-resource")
    @ResponseBody
    public FileSystemResource streamVideo() {
        // create folder file-system-resource in root of project
        return new FileSystemResource("local_storage/video/test3.mp4");
    }

    @GetMapping("stream/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadVideo() throws FileNotFoundException {

        File file = new File("local_storage/video/test3.mp4");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");


        InputStreamResource resource =
                new InputStreamResource(new FileInputStream("local_storage/video/test3.mp4"));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }




}
