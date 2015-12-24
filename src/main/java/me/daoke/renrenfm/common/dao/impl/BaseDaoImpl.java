package me.daoke.renrenfm.common.dao.impl;


import me.daoke.renrenfm.common.dao.IBaseDao;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作数据库基础类
 * @author wangzp
 *
 */
public class BaseDaoImpl extends SqlSessionDaoSupport implements IBaseDao {

	/**
	 * 根据sql文查询一行记录
	 * @param <T>
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:17:04 PM
	 */
	public <T> T selectOne(String statement) {
		return (T)getSqlSession().selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter){
		return (T)getSqlSession().selectOne(statement, parameter);
	}

	/**
	 * 查询多行记录
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:36:16 PM
	 */
	public <T> List<T> selectList(String statement){
		return getSqlSession().selectList(statement);
	}

	public <T> List<T> selectList(String statement, Object parameter){
		return (List<T>)getSqlSession().selectList(statement, parameter);
	}

	public <T> List<T> selectList(String statement, Object parameter, RowBounds rowBounds){
		return  (List<T>)getSqlSession().selectList(statement, parameter, rowBounds);
	}

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
	public <K, V> Map<K, V> selectMap(String statement, String mapKey){
		return (Map<K, V>)getSqlSession().selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey){
		return (Map<K, V>)getSqlSession().selectMap(statement, parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
										 String mapKey, RowBounds rowBounds){
		return (Map<K, V>)getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
	}

	/**
	 * 插入记录
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:18:09 PM
	 */
	public int insert(String statement){
		return getSqlSession().insert(statement);
	}

	/**
	 * 插入记录
	 * @param statement
	 * @param parameter
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:18:22 PM
	 */
	public int insert(String statement, Object parameter){
		return getSqlSession().insert(statement, parameter);
	}

	/**
	 * 修改
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:19:06 PM
	 */
	public int update(String statement){
		return getSqlSession().update(statement);
	}

	public int update(String statement, Object parameter){
		return getSqlSession().update(statement, parameter);
	}

	/**
	 * 删除
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:19:35 PM
	 */
	public int delete(String statement){
		return getSqlSession().delete(statement);
	}

	public int delete(String statement, Object parameter){
		return getSqlSession().delete(statement, parameter);
	}

	/**
	 * 提交事务
	 *
	 * @author wangzp
	 * @date May 16, 2014 4:19:50 PM
	 */
	public void commit(){
		getSqlSession().commit();
	}

	public void commit(boolean force){
		getSqlSession().commit(force);
	}

	/**
	 * 回滚事务
	 * @author wangzp
	 * @date May 16, 2014 4:20:01 PM
	 */
	public void rollback(){
		getSqlSession().rollback();
	}

	public void rollback(boolean force){
		getSqlSession().rollback(force);
	}

	public List<BatchResult> flushStatements(){
		return getSqlSession().flushStatements();
	}

	/**
	 * 关闭sqlsession
	 * @author wangzp
	 * @date May 16, 2014 4:20:33 PM
	 */
	public void close(){
		getSqlSession().close();
	}

	/**
	 * 清缓存
	 * @author wangzp
	 * @date May 16, 2014 4:20:45 PM
	 */
	public void clearCache(){
		getSqlSession().clearCache();
	}

	/**
	 * 获取sqlsession配置信息
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:21:07 PM
	 */
	public Configuration getConfiguration(){
		return getSqlSession().getConfiguration();
	}

	/**
	 * 检索映射
	 * @param <T>
	 * @param type
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:23:58 PM
	 */
	public <T> T getMapper(Class<T> type){
		return (T)getSqlSession().getMapper(type);
	}

	/**
	 * 获取内部数据库连接
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:21:40 PM
	 */
	public Connection getConnection(){
		return getSqlSession().getConnection();
	}




}
