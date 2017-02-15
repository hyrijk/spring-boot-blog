package com.hpm.blog.api;

import com.alibaba.fastjson.JSONObject;
import com.hpm.blog.annotation.CurrentUser;
import com.hpm.blog.annotation.LoginRequired;
import com.hpm.blog.model.Post;
import com.hpm.blog.model.User;
import com.hpm.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        post.setAuthorId(user.getId()); // 添加作者信息
        post = postService.add(post);
        return post;
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable int id) {
        Post post = postService.findById(id);
        return postService.findById(id);
    }

    @GetMapping("")
    public List<Post> all() {
        return postService.all();
    }

    /**
     *  更新文章，需要登录
     * @param post  需要修改的内容
     * @param id    文章 id
     * @param currentUser  当前用户
     * @return 更新之后的文章
     */
    @LoginRequired
    @PutMapping("/{id}")
    public Post update(@RequestBody Post post, @PathVariable int id, @CurrentUser User currentUser) {
        post.setId(id);
        return postService.update(post, currentUser);
    }

    /**
     * 删除文章，需要登录
     * @param id 文章 id
     * @param currentUser 当前登录用户
     * @return 提示信息
     */
    @LoginRequired
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id, @CurrentUser User currentUser) {
        postService.delete(id, currentUser);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "删除成功");
        return jsonObject;
    }
}
