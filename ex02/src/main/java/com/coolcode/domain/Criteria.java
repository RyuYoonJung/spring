package com.coolcode.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	private String type; // T C W
	private String keyword; 
	public String getParams(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.toUriString();
	}
	public String getParamWithoutPageNum(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.toUriString();
	}
	public String[] getTypeArr(){
		return type == null ? new String[] {} : type.split("");
 	}
	
}
