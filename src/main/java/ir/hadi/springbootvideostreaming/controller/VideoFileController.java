package ir.hadi.springbootvideostreaming.controller;

import ir.hadi.springbootvideostreaming.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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




}
