package com.HuangTaiQi.www.service.impl;

import com.HuangTaiQi.www.dao.CommentDao;
import com.HuangTaiQi.www.dao.impl.CommentDaoImpl;
import com.HuangTaiQi.www.po.CommentEntity;
import com.HuangTaiQi.www.service.CommentService;
import com.HuangTaiQi.www.utils.DBUtil;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    CommentDao commentDao=CommentDaoImpl.getInstance();
    @Override
    public List<CommentEntity> getComments(int pileId) throws Exception {
        List<CommentEntity> commentEntities =commentDao.getCommentsByPileId(pileId);
        DBUtil.close();
        return commentEntities;
    }

    @Override
    public void addComment(int pileId, String comm) throws SQLException {
        commentDao.addComment(pileId,searchChange(comm));
    }
    public static String searchChange(String word) {
        //定义敏感词汇库
        String[] arr = {"尼玛", "nnd", "NND", "TMD", "有病"};
        for (String s : arr) {
            word = word.replace(s, "***");
        }
        return word;
    }

}
