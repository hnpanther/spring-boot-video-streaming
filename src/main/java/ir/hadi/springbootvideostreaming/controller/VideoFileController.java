package ir.hadi.springbootvideostreaming.controller;

import ir.hadi.springbootvideostreaming.config.VideoContentStore;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/video")
public class VideoFileController {

    private VideoFileService videoFileService;
    private VideoFileRepository videoFileRepository;
    private VideoContentStore videoContentStores;

    public VideoFileController(VideoFileService videoFileService, VideoFileRepository videoFileRepository, VideoContentStore videoContentStores) {
        this.videoFileService = videoFileService;
        this.videoFileRepository = videoFileRepository;
        this.videoContentStores = videoContentStores;
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
        return new FileSystemResource("local_storage/video/zxc.mp4");
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("test");
        String UPLOAD_DIR = "local_storage/video";

        // create a path from file name
        Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

        // save the file to `UPLOAD_DIR`
        // make sure you have permission to write
        Files.write(path, file.getBytes());
        return null;
    }

    @GetMapping("stream/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadVideo() throws FileNotFoundException {

        File file = new File("local_storage/video/zxc.mp4");
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
        System.out.println(savedFile.toString());

        savedFile.setContentId(file.getOriginalFilename());
        videoContentStores.setContent(savedFile, file.getInputStream());
        videoFileRepository.save(savedFile);
        System.out.println("=======\n"+ savedFile.toString());


        return null;
    }

    @GetMapping("/files/{fileId}")
    @ResponseBody
    public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {

        Optional<VideoFile> f = videoFileRepository.findById(id);
        if (f.isPresent()) {
            InputStreamResource inputStreamResource = new InputStreamResource(videoContentStores.getContent(f.get()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getContentLength());
            headers.set("Content-Type", f.get().getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }








}
