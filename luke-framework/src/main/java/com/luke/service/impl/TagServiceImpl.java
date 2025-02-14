package com.luke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luke.domain.ResponseResult;
import com.luke.domain.dto.AllTagDto;
import com.luke.domain.dto.TagListDto;
import com.luke.domain.entity.Tag;
import com.luke.domain.vo.PageVo;
import com.luke.enums.AppHttpCodeEnum;
import com.luke.exception.SystemException;
import com.luke.mapper.TagMapper;
import com.luke.service.TagService;
import com.luke.utils.BeanCopyUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.security.auth.callback.LanguageCallback;
import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2025-02-09 20:42:28
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page(page, queryWrapper);


        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }



    @Override
    public ResponseResult addTag(Tag tag) {
        //内容不能为空
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.NAME_NOT_NULL);
        }
        if(!StringUtils.hasText(tag.getRemark())){
            throw new SystemException(AppHttpCodeEnum.REMARK_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }



    @Override
    public ResponseResult listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);

        List<Tag> list = list(queryWrapper);
        List<AllTagDto> allTagVos = BeanCopyUtils.copyBeanList(list, AllTagDto.class);

        return ResponseResult.okResult(allTagVos);

    }


}
