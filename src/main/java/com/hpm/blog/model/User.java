package com.hpm.blog.model;

public class User {
    private Integer id; // 这里不用 int， 应为 int 自动初始化为0，mybatis mapper 文件 就不能使用 <if test="id!=null"> 了
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
