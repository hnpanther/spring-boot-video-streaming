package ir.hadi.springbootvideostreaming.bootstrap;

import ir.hadi.springbootvideostreaming.model.VideoFile;
import ir.hadi.springbootvideostreaming.repository.VideoFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {

    private boolean initialized = false;

    private VideoFileRepository videoFileRepository;

    public InitializeData(@Autowired VideoFileRepository videoFileRepository) {
        this.videoFileRepository = videoFileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!this.initialized) {
//            initData();
        }
    }

    private void initData() {

        VideoFile videoFile1 = new VideoFile();
        videoFile1.setFileName("test1.mp4");
        videoFile1.setDirPath("/resources");

        VideoFile videoFile2 = new VideoFile();
        videoFile2.setFileName("test2.mp4");
        videoFile2.setDirPath("/resources");

        VideoFile videoFile3 = new VideoFile();
        videoFile3.setFileName("test3.mp4");
        videoFile3.setDirPath("/resources");

        this.videoFileRepository.save(videoFile1);
        this.videoFileRepository.save(videoFile2);
        this.videoFileRepository.save(videoFile3);

        this.initialized = true;
    };
}
