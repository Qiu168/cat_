package com.HuangTaiQi.www.dao.impl;

import com.HuangTaiQi.www.dao.CommentDao;
import com.HuangTaiQi.www.po.CommentEntity;
import com.HuangTaiQi.www.utils.DBUtil;
import com.HuangTaiQi.www.utils.sql.SQLBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * comment
 * @author 14629
 */
public class CommentDaoImpl implements CommentDao {
    private final Connection connection= DBUtil.getConnection();

    BaseDao baseDao=new BaseDao(connection);
    private static CommentDaoImpl instance;
    private CommentDaoImpl (){}
    public static synchronized CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<CommentEntity> getCommentsByPileId(int pileId) throws Exception {
        String sql=new SQLBuilder("comment").select("*").where("pile_id").buildSelect();
        return baseDao.selectByParams(sql, CommentEntity.class,pileId );
    }

    @Override
    public void addComment(int pileId, String searchChange) throws SQLException {
        String sql="insert into comment values(null,?,?)";
        baseDao.updateCommon(sql,pileId,searchChange);
    }
}
