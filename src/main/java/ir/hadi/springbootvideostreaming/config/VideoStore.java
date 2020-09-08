package ir.hadi.springbootvideostreaming.config;

import ir.hadi.springbootvideostreaming.model.VideoFile;
import org.springframework.content.commons.repository.ContentStore;


public interface VideoStore extends ContentStore<VideoFile, String> {
}
