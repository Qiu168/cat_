package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.CommentEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * comment
 * @author 14629
 */
public interface CommentService {
    /**
     * 获取充电桩的评论
     * @param pileId 充电桩的id
     * @return 评论集合
     * @throws Exception 异常
     */
    List<CommentEntity> getComments(int pileId) throws Exception;

    /**
     *新增评论
     * @param pileId 充电桩的id
     * @param comm 评论
     * @throws SQLException 异常
     */

    void addComment(int pileId, String comm) throws SQLException;
}
