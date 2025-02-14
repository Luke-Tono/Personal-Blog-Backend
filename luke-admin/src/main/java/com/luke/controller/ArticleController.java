package com.luke.controller;


import com.luke.domain.ResponseResult;
import com.luke.domain.dto.ArticleDto;
import com.luke.domain.entity.Article;
import com.luke.domain.vo.ArticleVo;
import com.luke.service.ArticleService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.RuntimeErrorException;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Article article){
        return articleService.getArticleList(pageNum, pageSize, article);
    }

    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){

        ArticleVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto articleDto){
        articleService.edit(articleDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return articleService.delete(id);
    }



}
