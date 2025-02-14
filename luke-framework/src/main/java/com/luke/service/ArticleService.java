package com.luke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.domain.ResponseResult;
import com.luke.domain.dto.ArticleDto;
import com.luke.domain.entity.Article;
import com.luke.domain.vo.ArticleVo;
import com.luke.domain.vo.HotArticleVo;

import java.util.List;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(ArticleDto articleDto);

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, Article article);



    void edit(ArticleDto articleDto);

    ArticleVo getInfo(Long id);

    ResponseResult delete(Long id);

    ResponseResult searchArticle(String keywords, Integer pageNum, Integer pageSize);
}
