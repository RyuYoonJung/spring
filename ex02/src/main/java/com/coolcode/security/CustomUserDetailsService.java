package com.coolcode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.coolcode.domain.CustomUser;
import com.coolcode.domain.MemberVO;
import com.coolcode.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired @Setter
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.warn(username);
		MemberVO vo = mapper.read(username);
		UserDetails details = vo == null ? null : new CustomUser(vo);
		log.warn(details);
		return details;
	}
	
}
