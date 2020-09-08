package ir.hadi.springbootvideostreaming.config;

import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableFilesystemStores
public class StoreConfig {

    public File fileSystemRoot() throws IOException {
        return new File("local_storage/video");
    }

    @Bean
    public FileSystemResourceLoader fsResourceLoader() throws Exception
    {
        return new FileSystemResourceLoader(fileSystemRoot().getAbsolutePath());
    }

}
