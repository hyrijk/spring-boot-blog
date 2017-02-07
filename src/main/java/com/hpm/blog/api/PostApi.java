package com.hpm.blog.api;

import com.alibaba.fastjson.JSONObject;
import com.hpm.blog.annotation.CurrentUser;
import com.hpm.blog.annotation.LoginRequired;
import com.hpm.blog.model.Post;
import com.hpm.blog.model.User;
import com.hpm.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章接口
 */
@RestController
@RequestMapping("/api/post")
public class PostApi {
    private PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    @LoginRequired
    public Post add(@RequestBody Post post, @CurrentUser User user) {
        post.setAuthorId(user.getId());
        post = postService.add(post);
        return post;
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable int id) {
        Post post = postService.findById(id);
        if (post == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "文章不存在");
            return jsonObject;
        } else {
            return post;
        }
    }
}
