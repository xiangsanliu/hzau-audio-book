package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.share.Pair;
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
    private String uploadFolder;

    public Pair<Boolean, String> uploadFile(MultipartFile file, String parentDir) throws IOException {
        return uploadFile(file, parentDir, file.getOriginalFilename(), false);
    }

    public Pair<Boolean, String> uploadFile(MultipartFile file, String parentDir, String fileName, boolean replace) throws IOException {
        if (Objects.isNull(file) || file.isEmpty()) {
            return new Pair<>(false, "空文件");
        }
        File dir = new File(uploadFolder + parentDir);
        if (dir.exists() || dir.mkdir()) {
            byte[] bytes = file.getBytes();
            Path realPath = Paths.get(dir.getAbsolutePath() + File.separator + fileName);
            File realFile = new File(realPath.toString());
            if (realFile.exists()) {
                if (replace) {
                    realFile.delete();
                } else {
                    return new Pair<>(false, "该文件已存在");
                }
            }
            Files.write(realPath, bytes);
            return new Pair<>(true, null);
        }
        return new Pair<>(false, "上传文件失败");
    }

}
