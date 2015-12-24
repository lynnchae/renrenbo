package me.daoke.renrenfm.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页对象 ，含分页数据 及分页信息
 *
 * @param <T>
 */
public class PageList<T> implements Serializable {

	private static final long serialVersionUID = -2949497622293336316L;

	/**
	 * 当前页第一条数据在List中的位置,从0开始
	 */
	//private int start = 0;
	/**
	 * 当前页 默认1 
	 */
	private int currentPage = 1;

	/**
	 * 总页数
	 */
	private int tatalPage;

	/**
	 * 总记录数 
	 */
	private int totalCount;
	/**
	 * 页大小
	 */
	private int pageSize = 20;
	/**
	 * 当前页中存放的数据
	 */
	private List<T> records = new ArrayList<T>();

	/**
	 * 构造方法，构造空数
	 */
	public PageList() {
		this(1,0, 20, new ArrayList<T>());
	}

	/**
	 * 带参数构造方法
	 * @param currentPage
	 *         当前页
	 * @param totalCount
	 * 			总记录行数
	 * @param pageSize
	 * 			当前页大中
	 * @param records
	 * 			当前页记录数据
	 */
	public PageList(Integer currentPage, Integer totalCount, Integer pageSize, List<T> records){
		if(currentPage == null || currentPage == 0){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}

		if(pageSize == null || pageSize == 0){
			this.pageSize = 10;
		}else{
			this.pageSize = pageSize;
		}
		this.totalCount = totalCount;
		this.records = records;
		if(this.totalCount%this.pageSize == 0){
			this.totalCount = this.totalCount/this.pageSize;
		}else{
			this.totalCount = this.totalCount/this.pageSize+1;
		}
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	/**
	 * 设置当前页
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage < 1)
			currentPage = 1;
		this.currentPage = currentPage;
	}

	/**
	 * 取当前页码 ，从1开始
	 * @return
	 */
	public int get(){
		return this.currentPage;
	}
	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean hashNextPage(){
		return this.get() < this.getTotalPage();
	}
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean hasPreviousPage(){
		return this.get() >1;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		if(this.totalCount % pageSize == 0){
			return  totalCount / pageSize;
		}else{
			return totalCount / pageSize + 1;
		}
	}

	public void setTatalPage(int tatalPage) {
		this.tatalPage = tatalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTatalPage() {
		return tatalPage;
	}

	/**
	 * 获取任一页第一条数据的位置,startIndex从0开始
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}



	/**
	 * 取前一页
	 * @return
	 */
	public int getPreviousPage() {
		return currentPage > 1 ? currentPage - 1 : currentPage;
	}
	/**
	 * 取下一页  如果当前页大于总页数返回-1
	 * @return
	 */
	public int getNextPage() {
		return currentPage < this.getTotalPage() ? currentPage + 1 :  -1;
	}

	/**
	 * 封装分页信息
	 * @return
	 */
	public static Map  packagePageInfo(PageList pageList){
		Map map  = new HashMap<String,Object>();
		if(pageList == null){
			pageList = new PageList(0,0,0,null);
		}
		map.put("size",pageList.getPageSize());
		map.put("totalCount",pageList.getTotalCount());
		map.put("currentPage",pageList.getCurrentPage());
		map.put("totalPage",pageList.getTotalPage());
		return map;
	}


	@Override
	public String toString() {
		return "PageList [currentPage=" + currentPage
				+ ", totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", records=" + records + "]";
	}
}
