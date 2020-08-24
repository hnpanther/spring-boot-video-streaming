package ir.hadi.springbootvideostreaming.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "video_file")
public class VideoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // unique in @Column only used in create table by jpa
    @Column(name = "file_name", unique = true)
    private String fileName;

    @Column(name = "dir_path")
    private String dirPath;

    @Column(name = "file_size")
    private double fileSize;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "added_date")
    private LocalDateTime addedDate;

    public VideoFile() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "VideoFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", dirPath='" + dirPath + '\'' +
                ", fileSize=" + fileSize +
                ", description='" + description + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }
}
