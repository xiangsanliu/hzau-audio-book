package com.hzau.feidian.hzauaudiobook.service;

import com.hzau.feidian.hzauaudiobook.dao.entity.Comment;
import com.hzau.feidian.hzauaudiobook.dao.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/4/17 15:03
 * @comment
 */

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public List<Comment> getAllBookComment() {
        List<Comment> comments = commentMapper.selectAllBookComment();
        comments.forEach(e -> {
            if (!e.getChecked()) {
                e.setStatus("未审核");
            } else if (e.getApproved()) {
                e.setStatus("审核通过");
            } else {
                e.setStatus("审核未通过");
            }
        });
        return comments;
    }

    public List<Comment> getAllShortComment() {
        List<Comment> comments = commentMapper.selectAllShortComment();
        comments.forEach(e -> {
            if (!e.getChecked()) {
                e.setStatus("未审核");
            } else if (e.getApproved()) {
                e.setStatus("审核通过");
            } else {
                e.setStatus("审核未通过");
            }
        });
        return comments;
    }

    public void checkShort(long id, boolean approved) {
        commentMapper.updateShortApprove(id, approved);
    }

    public void checkBook(long id, boolean approved) {
        commentMapper.updateBookApprove(id, approved);
    }

}
