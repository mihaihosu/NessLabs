package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.model.Tag;

import java.util.List;

public interface TagService {
    Tag createTag(String name);

    List<Tag> getAllTags();

    Tag getTagByName(String name);
}
