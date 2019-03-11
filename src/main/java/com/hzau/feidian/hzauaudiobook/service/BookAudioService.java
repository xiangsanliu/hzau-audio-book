package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class BookAudioService {

    @Value("${upload-folder}")
    private String baseFolder;

    @Resource
    private BookAudioMapper bookAudioMapper;

    public void saveBookAudio(BookAudio bookAudio) {
        bookAudioMapper.insertOne(bookAudio);
    }

    private BookAudio getOne(long id) {
        return bookAudioMapper.selectOne(id);
    }

    public List<BookAudio> listBookAudiosByBook(long id) {
        List<BookAudio> bookAudios = bookAudioMapper.selectBookAudiosByBook(id);
        bookAudios.forEach(item -> {
            String url = "books/" + item.getBookName() + '/' + item.getName();
            item.setUrl(url);
        });
        return bookAudios;
    }

    @Transactional
    public void removeOne(long id) {
        BookAudio item = getOne(id);
        String path = baseFolder + "books" + File.separator + item.getBookName() + File.separator + item.getName();
        File file = new File(path);
        file.delete();
        bookAudioMapper.deleteOne(id);
    }


}
