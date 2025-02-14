package com.luke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.domain.ResponseResult;
import com.luke.domain.dto.TagListDto;
import com.luke.domain.entity.Tag;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2025-02-09 20:42:28
 */
public interface TagService extends IService<Tag> {


    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult listAllTag();
}

