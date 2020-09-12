package ir.hadi.springbootvideostreaming.config;

import ir.hadi.springbootvideostreaming.model.VideoFile;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;


@StoreRestResource(path = "videosrc")
public interface VideoContentStore extends ContentStore<VideoFile, String> {
}
