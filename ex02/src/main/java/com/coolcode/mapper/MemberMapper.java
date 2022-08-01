package com.coolcode.mapper;

import com.coolcode.domain.AuthVO;
import com.coolcode.domain.MemberVO;

public interface MemberMapper {
	MemberVO read(String userid);
	
	int insertMember(MemberVO vo);
	
	int insertAuth(AuthVO vo);
}
