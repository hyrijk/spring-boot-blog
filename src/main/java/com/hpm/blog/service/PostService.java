package com.hpm.blog.service;

import com.hpm.blog.mapper.PostMapper;
import com.hpm.blog.model.Post;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private PostMapper postMapper;

    @Autowired
    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional
    public Post add(Post post) {
        postMapper.add(post);
        return findById(post.getId());
    }

    public Post findById(Integer id) {
        Post param = new Post();
        param.setId(id);
        return postMapper.findOne(param);
    }
}
