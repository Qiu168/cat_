package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.CommentEntity;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    List<CommentEntity> getCommentsByPileId(int pileId) throws Exception;

    void addComment(int pileId, String searchChange) throws SQLException;
}
