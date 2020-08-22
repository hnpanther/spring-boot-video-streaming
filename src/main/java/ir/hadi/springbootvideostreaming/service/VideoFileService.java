package ir.hadi.springbootvideostreaming.service;


import ir.hadi.springbootvideostreaming.model.VideoFile;
import ir.hadi.springbootvideostreaming.repository.VideoFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoFileService {

    private VideoFileRepository videoFileRepository;

    public VideoFileService(@Autowired VideoFileRepository videoFileRepository) {
        this.videoFileRepository = videoFileRepository;
    }

    public VideoFile getVideoFileByName(String fileName) {
        return this.videoFileRepository.findByFileName(fileName);
    }
}
