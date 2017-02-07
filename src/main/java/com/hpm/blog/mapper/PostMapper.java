package com.hpm.blog.mapper;

import com.hpm.blog.model.Post;

public interface PostMapper {
    int add(Post post);

    Post findOne(Post param);
}
