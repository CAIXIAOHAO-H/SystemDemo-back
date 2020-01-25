package com.back.entity;

import java.util.Date;
import java.util.List;

public class Resourse {
    private String id;

    private String name;

    private String url;

    private String permission;

    private String lever;

    private String parentId;

    private Date createTime;

    private List<Resourse> children;

    private String title;

    private String menuTag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever == null ? null : lever.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Resourse> getChildren() {
        return children;
    }

    public void setChildren(List<Resourse> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenuTag() {
        return menuTag;
    }

    public void setMenuTag(String menuTag) {
        this.menuTag = menuTag;
    }
}