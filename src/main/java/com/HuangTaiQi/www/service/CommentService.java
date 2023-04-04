package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.CommentEntity;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    List<CommentEntity> getComments(int pileId) throws Exception;

    void addComment(int pileId, String comm) throws SQLException;
}
