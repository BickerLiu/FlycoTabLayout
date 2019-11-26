package com.flyco.tablayoutsamples.entity;

/**
 * @Author: lbq
 * @Description:
 * @CreateDate: 2019/11/25 20:16
 */
public class ListEntity {
    private String title;
    private String entityText;

    public ListEntity(String title, String entityText) {
        this.title = title;
        this.entityText = entityText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntityText() {
        return entityText;
    }

    public void setEntityText(String entityText) {
        this.entityText = entityText;
    }
}
