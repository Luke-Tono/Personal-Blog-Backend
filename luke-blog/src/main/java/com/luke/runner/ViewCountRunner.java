package com.luke.runner;


import com.luke.domain.entity.Article;
import com.luke.mapper.ArticleMapper;
import com.luke.service.ArticleService;
import com.luke.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleService.list(null);
        Map<String, Integer> collect = articles.stream()
                .collect(Collectors.toMap(Article -> Article.getId().toString(), Article -> Article.getViewCount().intValue()));

        //存储到redis中

        redisCache.setCacheMap("article:viewCount", collect);
    }
}
