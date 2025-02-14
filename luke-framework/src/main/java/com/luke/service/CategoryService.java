package com.luke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.domain.ResponseResult;
import com.luke.domain.entity.Category;
import com.luke.domain.vo.CategoryVo;
import com.luke.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2025-02-05 17:18:42
 */
public interface CategoryService extends IService<Category> {


    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategoty();

    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}

