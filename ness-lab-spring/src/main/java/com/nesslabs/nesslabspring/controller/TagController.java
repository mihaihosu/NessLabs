package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.TagRequestDto;
import com.nesslabs.nesslabspring.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagRequestDto> createTag(@RequestBody TagRequestDto tagDto) {
        TagRequestDto createdTagDto = tagService.createTag(tagDto.getName());
        return new ResponseEntity<>(createdTagDto, HttpStatus.CREATED);
    }


    @GetMapping
    @Cacheable(value = "tags")
    public ResponseEntity<List<TagRequestDto>> getAllTags() {
        List<TagRequestDto> tagDtos = tagService.getAllTags();
        return new ResponseEntity<>(tagDtos, HttpStatus.OK);
    }



}
