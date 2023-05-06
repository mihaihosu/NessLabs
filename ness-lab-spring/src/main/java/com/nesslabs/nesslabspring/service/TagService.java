package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.TagRequestDto;
import com.nesslabs.nesslabspring.model.Tag;

import java.util.List;

public interface TagService {
    Tag createTag(String name);

    List<TagRequestDto> getAllTags();

}
