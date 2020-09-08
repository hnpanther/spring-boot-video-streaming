package ir.hadi.springbootvideostreaming.repository;

import ir.hadi.springbootvideostreaming.model.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {

    public VideoFile findByFileName(String fileName);
}
