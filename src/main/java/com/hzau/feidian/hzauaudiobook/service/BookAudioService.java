package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.dao.entity.BookAudio;
import com.hzau.feidian.hzauaudiobook.dao.mapper.BookAudioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class BookAudioService {

    @Resource
    private BookAudioMapper bookAudioMapper;

    public void saveBookAudio(BookAudio bookAudio) {
        bookAudioMapper.insertOne(bookAudio);
    }

    public BookAudio getOne(long id) {
        return bookAudioMapper.selectOne(id);
    }

    public List<BookAudio> listBookAudiosByBook(long id) {
        List<BookAudio> bookAudios = bookAudioMapper.selectBookAudiosByBook(id);
        bookAudios.forEach(item -> {
            String path = item.getPath();
            item.setName(path.substring(path.lastIndexOf(File.separator) + 1));
        });
        return bookAudios;
    }

    @Transactional
    public void removeOne(long id) {
        File file = new File(bookAudioMapper.selectOne(id).getPath());
        file.delete();
        bookAudioMapper.deleteOne(id);
    }


}
