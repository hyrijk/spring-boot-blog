package com.hpm.blog.service;

import com.hpm.blog.mapper.PostMapper;
import com.hpm.blog.model.Post;
import com.hpm.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

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
        Post post = postMapper.findOne(param);
        checkNotNull(post, "文章不存在");
        return post;
    }

    public List<Post> all() {
        return postMapper.all();
    }

    public Post update(Post post, User currentUser) {
        checkNotNull(post.getId(), "id不能为空");
        checkOwner(post.getId(), currentUser);
        postMapper.update(post);
        return findById(post.getId());
    }

    private void checkOwner(Integer id, User currentUser) {
        Post post = findById(id);
        if (!post.getAuthorId().equals(currentUser.getId())) {
            throw new RuntimeException("不能删除或修改别人的文章");
        }
    }

    public void delete(int id, User currentUser) {
        checkOwner(id, currentUser);
        postMapper.delete(id);
    }
}
