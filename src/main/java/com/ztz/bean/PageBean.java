package com.ztz.bean;

import java.util.List;

//分页使用
public class PageBean<T> {
	// 存储要查询的数据
	private List<T> beans;
	// 总数据量
	private int allCounts;
	// 总页数
	private int pageCounts;
	// 每页记录数
	private int pageSize;
	// 当前页索引
	private int pageIndex = 1;
	// 是否为第一页
	private boolean isFirstPage;
	// 是否为最后一页
	private boolean isLastPage;
	// 是否有前一页
	private boolean hasPreviousPage;
	// 是否有下一页
	private boolean hasNextPage;

	/**
	 *
	 * @param allCounts  所有数据
	 * @param pageSize   每页数据量
	 */
	public PageBean(int allCounts, int pageSize) {
		this.allCounts = allCounts;
		this.pageSize = pageSize;
		//计算一共有多少页
		this.pageCounts = countTotalPage(allCounts,pageSize);
		init();
	}

	private void init(){
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasNextPage = isHasNextPage();
		this.hasPreviousPage = isHasPreviousPage();
	}

	/**
	 * 计算总页数,静态方法,供外部直接通过类名调用 计算总页数,静态方法,供外部直接通过类名调用
	 *
	 * @param allRow总记录数
	 * @param pageSize每页记录数
	 * @return 总页数
	 */
	public static int countTotalPage(final int allCounts,final int pageSize) {
		int totalPage = allCounts % pageSize == 0 ? allCounts / pageSize : allCounts
				/ pageSize + 1;
		return totalPage;
	}

	/**
	 * 计算当前页开始记录
	 *
	 * @param pageSize每页记录数
	 * @param currentPage当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	 *
	 * @paramPage 传入的参数(可能为空,即0,则返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}

	public List<T> getBeans() {
		return beans;
	}

	public void setBeans(List<T> beans) {
		this.beans = beans;
	}

	public int getAllCounts() {
		return allCounts;
	}

	public void setAllCounts(int allCounts) {
		this.allCounts = allCounts;
	}

	public int getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(int pageCounts) {
		this.pageCounts = pageCounts;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFirstPage() {
		return pageIndex == 1 ? true : false;
	}

	public boolean isLastPage() {
		return pageIndex == pageCounts ? true : false;
	}

	public boolean isHasPreviousPage() {
		return pageIndex!=1?true:false;
	}

	public boolean isHasNextPage() {
		return pageIndex!=pageCounts?true:false;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}
