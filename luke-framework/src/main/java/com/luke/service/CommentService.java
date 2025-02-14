package com.luke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.domain.ResponseResult;
import com.luke.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2025-02-07 17:16:37
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

