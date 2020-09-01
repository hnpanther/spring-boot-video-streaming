package ir.hadi.springbootvideostreaming.config;

import org.springframework.content.commons.repository.Store;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path="videosrc")
public interface VideoStore extends Store<String> {
}
