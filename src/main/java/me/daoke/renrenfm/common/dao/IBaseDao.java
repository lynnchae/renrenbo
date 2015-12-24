package me.daoke.renrenfm.common.dao;



import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author zhuosh
 * @date 2015/5/16
 */
public interface IBaseDao {

    /**
     * 根据sql文查询一行记录
     * @param <T>
     * @param statement
     * @return
     * @author wangzp
     * @date May 16, 2014 4:17:04 PM
     */
    public <T> T selectOne(String statement);

    /**
     * 根据映射文件sql的ID 查询一条记录
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, Object parameter);

    /**
     * 根据sql 查询多行记录
     * @param statement
     * @return
     * @author wangzp
     * @date May 16, 2014 4:36:16 PM
     */
    public <T> List<T> selectList(String statement);


    /**
     * 根据映射文件的sql的ID  查询多条记录
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> List<T> selectList(String statement, Object parameter);


    public <T> List<T> selectList(String statement, Object parameter, RowBounds rowBounds);

    /**
     * 返回值封装为map类型
     * @param <K>
     * @param <V>
     * @param statement
     * @param mapKey
     * @return
     * @author wangzp
     * @date May 16, 2014 4:36:42 PM
     */
    public <K, V> Map<K, V> selectMap(String statement, String mapKey);


    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);


    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);


    /**
     * 插入记录
     * @param statement
     * @return
     * @author wangzp
     * @date May 16, 2014 4:18:09 PM
     */
    public int insert(String statement);


    /**
     * 插入记录
     * @param statement
     * @param parameter
     * @return
     * @author wangzp
     * @date May 16, 2014 4:18:22 PM
     */
    public int insert(String statement, Object parameter);


    /**
     * 修改
     * @param statement
     * @return
     * @author wangzp
     * @date May 16, 2014 4:19:06 PM
     */
    public int update(String statement);


    public int update(String statement, Object parameter);


    /**
     * 删除
     * @param statement
     * @return
     * @author wangzp
     * @date May 16, 2014 4:19:35 PM
     */
    public int delete(String statement);


    public int delete(String statement, Object parameter);


    /**
     * 提交事务
     *
     * @author wangzp
     * @date May 16, 2014 4:19:50 PM
     */
    public void commit();


    public void commit(boolean force);


    /**
     * 回滚事务
     * @author wangzp
     * @date May 16, 2014 4:20:01 PM
     */
    public void rollback();


    public void rollback(boolean force);


    public List<BatchResult> flushStatements();


    /**
     * 关闭sqlsession
     * @author wangzp
     * @date May 16, 2014 4:20:33 PM
     */
    public void close();


    /**
     * 清缓存
     * @author wangzp
     * @date May 16, 2014 4:20:45 PM
     */
    public void clearCache();


    /**
     * 获取sqlsession配置信息
     * @return
     * @author wangzp
     * @date May 16, 2014 4:21:07 PM
     */
    public Configuration getConfiguration();


    /**
     * 检索映射
     * @param <T>
     * @param type
     * @return
     * @author wangzp
     * @date May 16, 2014 4:23:58 PM
     */
    public <T> T getMapper(Class<T> type);


    /**
     * 获取内部数据库连接
     * @return
     * @author wangzp
     * @date May 16, 2014 4:21:40 PM
     */
    public Connection getConnection();






}
