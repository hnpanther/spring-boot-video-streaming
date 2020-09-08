package ir.hadi.springbootvideostreaming.model;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

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

    @ContentId
    private String contentId;
    @ContentLength
    private long contentLength;
    @MimeType
    private String mimeType;

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

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
                ", contentId='" + contentId + '\'' +
                ", contentLength=" + contentLength +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }
}
