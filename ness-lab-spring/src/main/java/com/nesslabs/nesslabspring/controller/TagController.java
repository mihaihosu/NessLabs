package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.TagRequestDto;
import com.nesslabs.nesslabspring.model.Tag;
import com.nesslabs.nesslabspring.service.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/tags")
@RequiredArgsConstructor
public class TagController {

    @Autowired
    private final TagServiceImpl tagService;

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