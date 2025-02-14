package com.luke.controller;


import com.luke.constants.SystemConstants;
import com.luke.domain.ResponseResult;
import com.luke.domain.dto.AddCommentDto;
import com.luke.domain.entity.Comment;
import com.luke.service.CommentService;
import com.luke.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }



    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addcommentDto){
        Comment comment = BeanCopyUtils.copyBean(addcommentDto, Comment.class);
        return commentService.addComment(comment);
    }




    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表", notes = "获取一页友联评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")

    })
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);
    }
}
