package com.coolcode.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private static final int PAGE_COUNT = 10;
	
	private int start; // 시작 숫자 
	private int end; // 종료 숫자 
	private int total; // 게시글 총 갯수
	private boolean next; // 다음 페이지 가능여부
	private boolean prev; // 이전 페이지 가능여부
	private Criteria cri; // pageNum, amount
	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		end = (cri.getPageNum() + (PAGE_COUNT-1)) / PAGE_COUNT * PAGE_COUNT; // 1의 자릿수에서 10의 자리로 올림계산
		start = end - PAGE_COUNT + 1;
		
		int realEnd = (total + (cri.getAmount()-1)) / cri.getAmount(); // 소수점 첫번째자리를 1의 자릿수로 올림계산
		if(realEnd < end) {
			end = realEnd;
		}
		
//		prev = cri.getPageNum() > 1;
//		next = cri.getPageNum() < realEnd;
		
		prev = start > 1;
		next = end < realEnd;
		
	}
}
