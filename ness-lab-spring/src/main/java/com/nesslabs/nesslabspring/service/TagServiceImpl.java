package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.TagRequestDto;
import com.nesslabs.nesslabspring.model.Tag;
import com.nesslabs.nesslabspring.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    @Autowired
    private final TagRepository tagRepository;

    @Override
    public Tag createTag(String name) {
        Tag existingTag =  tagRepository.findByName(name);
        if(existingTag != null){
            throw new IllegalArgumentException("Tag with name" + name + " already exists");
        }
        Tag tag = new Tag(name);
        return tagRepository.save(tag);
    }



    @CachePut(value="tags")
    public List<TagRequestDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return convertTagsToDtos(tags);
    }

    private List<TagRequestDto> convertTagsToDtos(List<Tag> tags) {
        return tags.stream()
                .map(tag -> new TagRequestDto(tag.getName()))
                .collect(Collectors.toList());
    }



}
