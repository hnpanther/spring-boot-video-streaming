package ir.hadi.springbootvideostreaming.controller;

import ir.hadi.springbootvideostreaming.config.VideoStore;
import ir.hadi.springbootvideostreaming.model.VideoFile;
import ir.hadi.springbootvideostreaming.repository.VideoFileRepository;
import ir.hadi.springbootvideostreaming.service.VideoFileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/video")
public class VideoFileController {

    private VideoFileService videoFileService;
    private VideoFileRepository videoFileRepository;
    private VideoStore videoStores;

    public VideoFileController(VideoFileService videoFileService, VideoFileRepository videoFileRepository, VideoStore videoStores) {
        this.videoFileService = videoFileService;
        this.videoFileRepository = videoFileRepository;
        this.videoStores = videoStores;
    }

    @GetMapping({"/","index.html,"})
    public String indexPage() {
        return "index";
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

    @PostMapping("/files")
    @ResponseBody
    public ResponseEntity<?> setContent(@RequestParam("file") MultipartFile file)
            throws IOException {

        VideoFile vf = new VideoFile();
        vf.setFileName(file.getOriginalFilename());
        vf.setMimeType(file.getContentType());
        VideoFile savedFile = videoFileRepository.save(vf);

        videoStores.setContent(savedFile, file.getInputStream());
        videoFileRepository.save(savedFile);


        return null;
    }

    @GetMapping("/files/{fileId}")
    @ResponseBody
    public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {

        Optional<VideoFile> f = videoFileRepository.findById(id);
        if (f.isPresent()) {
            InputStreamResource inputStreamResource = new InputStreamResource(videoStores.getContent(f.get()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getContentLength());
            headers.set("Content-Type", f.get().getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }








}
