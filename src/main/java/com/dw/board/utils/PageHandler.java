package com.dw.board.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageHandler {

	private int total; // 전체 게시물 수 
	private int pageNum; // 현재 페이지
	private int pageSize; // 1페이지당 게시물 갯수
	private int startPage; //현재 블록 첫 페이지
	private int endPage; // 현재 블록 마지막 페이지
	private boolean hasPreviousPage;// 이전 버튼 유무
	private boolean hasNextPage;// 다음 버튼 유무
	private int nowBlock; // 현재 블록 == pageNum / navigatePages (feat. if절 이용해서 nowBlock++;)
	private int lastBlock; // 마지막 블록
	private int navigatePages; // 1블록당 페이지 갯수(ex) 1블록 : [1][2][3][4][5][6][7][8][9][10])
	

	/**
		 * @Method Name : calcPage
		 * @param total
		 * @param pageSize
		 * @return : int
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 총 페이지 수
		 */
	public int calcPage(int total, int pageSize) {
		int pages = total / pageSize;
		if(total % pageSize > 0){
			pages++;
		}
		return pages;
	}
	

	/**
		 * @Method Name : setNowBlock
		 * @param pageNum
		 * @param navigatePages
		 * @return : void
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 현재 페이지 블록 알아내기
		 */
	public void setNowBlock(int pageNum, int navigatePages) {
		this.nowBlock = pageNum / navigatePages;
		if(pageNum % navigatePages > 0) {
			this.nowBlock++;
		}
	}

	/**
		 * @Method Name : setLastBlock
		 * @param total
		 * @return : void
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 마지막 블록 알아내기
		 */
	public void setLastBlock(int total) {
		this.lastBlock = total / (this.navigatePages * this.pageSize);
		if(total % (this.navigatePages * this.pageSize) > 0) {
			this.lastBlock++;
		}
	}
	
	
	/**
		 * @Method Name : setStartPage
		 * @param nowBlock
		 * @return : void
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 현재 블록의 첫 페이지
		 */
	public void setStartPage(int nowBlock) {
		this.startPage = (nowBlock * this.navigatePages) - (this.navigatePages - 1);
		
	}
	
	/**
		 * @Method Name : setEndPage
		 * @param lastBlock
		 * @param nowBlock
		 * @return : void
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 현재 블록의 마지막 페이지
		 */
	public void setEndPage(int lastBlock, int nowBlock) {
		this.endPage = nowBlock * this.navigatePages;
		if(nowBlock == this.lastBlock) {
			this.endPage = total;
		}
	}
	
	
	/**
		 * @Method Name : setPreNext
		 * @param pageNum
		 * @return : void
		 * @author : geunhwan Ryu
		 * @date : 2022. 5. 31.
		 *comment : 이전버튼, 다음버튼 유무
		 */
	public void setPreNext(int pageNum) {
		if(this.lastBlock == 1) {
			setHasPreviousPage(false);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && this.lastBlock == this.nowBlock) {//마지막 블록이라면 이전버튼만 존재
			setHasPreviousPage(true);
			setHasNextPage(false);
		}
		if(this.lastBlock > 1 && pageNum <= this.navigatePages) {//첫번 째 블록인데 블록이 1 이상일 경우 다음 버튼 존재
			setHasPreviousPage(false);
			setHasNextPage(true);
		}
		
	}


	
	
	
	
	
	
	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}


	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}


	public boolean isHasNextPage() {
		return hasNextPage;
	}


	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}


	public int getNowBlock() {
		return nowBlock;
	}


	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}


	public int getNavigatePages() {
		return navigatePages;
	}


	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}


	public int getStartPage() {
		return startPage;
	}


	public int getLastBlock() {
		return lastBlock;
	}
	

}
