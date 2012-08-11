package com.ckarthik17.bestpcconfig;

import java.io.Serializable;

public class BlogPost implements Serializable {
    private int layoutId;
    private int tableId;
    private String tableName;
    private String postId;

    public BlogPost(int layoutId, int tableId, String tableName, String postId) {
        this.layoutId = layoutId;
        this.tableId = tableId;
        this.tableName = tableName;
        this.postId = postId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
