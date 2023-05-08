package com.nesslabs.nesslabspring.service;

import com.nesslabs.nesslabspring.dto.TagRequestDto;
import com.nesslabs.nesslabspring.model.Tag;

import java.util.List;

public interface TagService {
    TagRequestDto createTag(String name);

    List<TagRequestDto> getAllTags();

    TagRequestDto convertTagToDto(Tag tag);

    List<TagRequestDto> convertTagsToDtos(List<Tag> tags);

}
