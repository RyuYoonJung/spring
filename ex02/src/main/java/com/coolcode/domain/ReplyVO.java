package com.coolcode.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data @Alias("reply") 
public class ReplyVO {
	private Long rno;
	private Long bno;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
