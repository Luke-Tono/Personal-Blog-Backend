package com.luke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.domain.entity.Article;
import com.luke.domain.entity.Link;

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 友链(Link)表数据库访问层
     *
     * @author makejava
     * @since 2025-02-05 22:59:49
     */
    interface LinkMapper extends BaseMapper<Link> {

    }
}
