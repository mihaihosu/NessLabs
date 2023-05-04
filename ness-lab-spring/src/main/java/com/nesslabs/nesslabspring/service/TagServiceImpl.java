package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.model.Tag;
import com.nesslabs.nesslabspring.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }


}
