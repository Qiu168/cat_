package com.HuangTaiQi.www.po;

/**
 * 评论类
 * @author 14629
 */
public class CommentEntity {
    private Integer id;
    private Integer pileId;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPileId() {
        return pileId;
    }

    public void setPileId(Integer pileId) {
        this.pileId = pileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
