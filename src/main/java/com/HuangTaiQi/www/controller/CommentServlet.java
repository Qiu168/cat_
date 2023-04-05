package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.CommentEntity;
import com.HuangTaiQi.www.service.CommentService;
import com.HuangTaiQi.www.service.impl.CommentServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 14629
 */
public class CommentServlet extends BaseServlet{
    CommentService commentService=new CommentServiceImpl();
    private static CommentServlet instance;
    private CommentServlet (){}
    public static synchronized CommentServlet getInstance() {
        if (instance == null) {
            instance = new CommentServlet();
        }
        return instance;
    }
    public List<CommentEntity> getComments(int pileId) {
        try {
            return commentService.getComments(pileId);
        } catch (Exception e) {
            handleException(CommentServlet.class,e);
        }
        return null;
    }

    public void addComment(int pileId, String comm) {
        try {
            commentService.addComment(pileId,comm);
        } catch (SQLException e) {
            handleException(CommentServlet.class,e);
        }
    }
}
