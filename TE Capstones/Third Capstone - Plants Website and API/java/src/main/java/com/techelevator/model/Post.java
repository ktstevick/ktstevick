package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Post {
    @JsonProperty("post_id")
    private int postId;
    @JsonProperty("plant_id")
    private int plantId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("forum_id")
    private int forumId;
    @JsonProperty("post_description")
    private String postDescription;
    private BigDecimal price;
    private int quantity;
    private String title;
    @JsonProperty("post_img")
    private String postImg;
    @JsonProperty("post_category")
    private String postCategory;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty ("reply_to_post_id")
    private int replyPostId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getReplyPostId() {
        return replyPostId;
    }

    public void setReplyPostId(int replyPostId) {
        this.replyPostId = replyPostId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", plantId=" + plantId +
                ", userId=" + userId +
                ", forumId=" + forumId +
                ", postDescription='" + postDescription + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", title='" + title + '\'' +
                ", postImg='" + postImg + '\'' +
                ", postCategory='" + postCategory + '\'' +
                ", isActive=" + isActive +
                ", replyPostId=" + replyPostId +
                '}';
    }
}
