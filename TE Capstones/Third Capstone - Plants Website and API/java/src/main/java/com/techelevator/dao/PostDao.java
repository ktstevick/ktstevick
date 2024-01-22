package com.techelevator.dao;

import com.techelevator.model.Garden;
import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getPostByUserId(int id);
    Post createPost(Post post);
    Post getPostById(int id);
    Post updatePost(Post post);
    List<Post> getPostByForumId(int forumId);
}
