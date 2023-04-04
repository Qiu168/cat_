package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.CommentEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * comment
 * @author 14629
 */
public interface CommentDao {
    /**
     * 获取某个充电桩的id
     * @param pileId 充电桩id
     * @return comments集合
     * @throws Exception 异常
     */
    List<CommentEntity> getCommentsByPileId(int pileId) throws Exception;

    /**
     * 新增comment
     * @param pileId 充电桩id
     * @param comment 评论
     * @throws SQLException 异常
     */
    void addComment(int pileId, String comment) throws SQLException;
}
