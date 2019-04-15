package com.hzau.feidian.hzauaudiobook.service.wechat;

import com.alibaba.fastjson.JSON;
import com.hzau.feidian.hzauaudiobook.dao.entity.Comment;
import com.hzau.feidian.hzauaudiobook.dao.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/4/15 12:23
 * @comment
 */

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public void comment(String openid, String data, boolean flag) {
        Comment comment = JSON.parseObject(data, Comment.class);
        comment.setOpenid(openid);
        if (flag) {
            commentMapper.insertIntoBookAudio(comment);
        } else {
            commentMapper.insertIntoShortAudio(comment);
        }
    }

    public List<Comment> listShortAudioComments(String openid, long id) {
        return commentMapper.selectByShort(openid, id);
    }

    public List<Comment> listBookAudioComments(String openid, long id) {
        return commentMapper.selectByBook(openid, id);
    }

}
