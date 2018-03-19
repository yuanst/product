package com.product.search.pojo;

import java.util.List;

public class SearchResult {
	//总记录数
		private long recordCount;
		//总页数
		private long pageCount;
		//当前页
		private long curPage;
		private List<TbItem> tbItems;
		public long getRecordCount() {
			return recordCount;
		}
		public void setRecordCount(long recordCount) {
			this.recordCount = recordCount;
		}
		public long getPageCount() {
			return pageCount;
		}
		public void setPageCount(long pageCount) {
			this.pageCount = pageCount;
		}
		public long getCurPage() {
			return curPage;
		}
		public void setCurPage(long curPage) {
			this.curPage = curPage;
		}
		public List<TbItem> getTbItems() {
			return tbItems;
		}
		public void setTbItems(List<TbItem> tbItems) {
			this.tbItems = tbItems;
		}
		

}
