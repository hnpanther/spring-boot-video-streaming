package ir.hadi.springbootvideostreaming.controller;

import ir.hadi.springbootvideostreaming.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
