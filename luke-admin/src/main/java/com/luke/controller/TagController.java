package com.luke.controller;

import com.luke.domain.ResponseResult;
import com.luke.domain.dto.AddTagDto;
import com.luke.domain.dto.EditTagDto;
import com.luke.domain.dto.TagListDto;
import com.luke.domain.entity.Tag;
import com.luke.service.TagService;
import com.luke.utils.BeanCopyUtils;
import com.luke.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ReaderEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Tags;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping("")
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto){
        Tag tag = BeanCopyUtils.copyBean(addTagDto, Tag.class);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id){
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping("")
    public ResponseResult updateTag(@RequestBody EditTagDto editTagDto){
        Tag tag = BeanCopyUtils.copyBean(editTagDto, Tag.class);
        tagService.updateById(tag);

        return ResponseResult.okResult();
    }



    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }

}
