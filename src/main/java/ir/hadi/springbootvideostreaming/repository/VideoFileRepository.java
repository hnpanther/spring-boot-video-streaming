package ir.hadi.springbootvideostreaming.repository;

import ir.hadi.springbootvideostreaming.model.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {
}
