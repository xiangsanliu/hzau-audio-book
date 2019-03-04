package com.hzau.feidian.hzauaudiobook.controller;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import com.hzau.feidian.hzauaudiobook.service.BookAudioService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    private final BookAudioService bookAudioService;

    @Autowired
    public FileController(BookAudioService bookAudioService) {
        this.bookAudioService = bookAudioService;
    }

    @RequestMapping("/upload/bookAudio/{id}/{name}")
    public ResponseBean uploadBookAudio(MultipartFile file, @PathVariable("id") Long id, @PathVariable String name) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return ResponseBean.error("空文件");
        }
        try {
            File dir = new File("C:\\Users\\xiang\\Documents\\" + name);
            if (dir.exists() || dir.mkdir()) {
                byte[] bytes = file.getBytes();
                Path realPath = Paths.get(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                if (new File(realPath.toString()).exists()) {
                    return ResponseBean.ok("该文件已存在");
                }
                String path = Files.write(realPath, bytes).toAbsolutePath().toString();
                BookAudio bookAudio = new BookAudio(path, id);
                bookAudioService.saveBookAudio(bookAudio);
                return ResponseBean.ok();
            }
            return ResponseBean.error("unknown");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseBean.error("error");
    }


    @RequestMapping("/download/bookAudio/{id}")
    public void downloadBookAudio(HttpServletResponse response, @PathVariable("id") long id) throws UnsupportedEncodingException {
        String path = bookAudioService.getOne(id).getPath();
        String filName = path.substring(path.lastIndexOf(File.separator));
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filName, "UTF-8"));
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));) {
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = inputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
