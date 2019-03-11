package com.hzau.feidian.hzauaudiobook.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author 项三六
 * @time 2019/3/11 12:26
 * @comment
 */

@Service
public class FileService {

    @Value("${upload-folder}")
    String uploadFolder;

    public Pair<Boolean, String> uploadFile(MultipartFile file, String parentDir) throws IOException {
        if (Objects.isNull(file) || file.isEmpty()) {
            return new Pair<>(false, "空文件");
        }
        File dir = new File(uploadFolder + parentDir);
        System.out.println(dir);
        if (dir.exists() || dir.mkdir()) {
            byte[] bytes = file.getBytes();
            Path realPath = Paths.get(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            if (new File(realPath.toString()).exists()) {
                return new Pair<>(false, "该文件已存在");
            }
            String path = Files.write(realPath, bytes).toAbsolutePath().toString();
            return new Pair<>(true, path);
        }
        return new Pair<>(false, "上传文件失败");
    }

}
