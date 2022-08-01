package com.coolcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller  
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping("/all")
	public void doAll(){
		log.info("모든 사용자 접근가능 페이지입니다.");
	}

	@GetMapping("/member")
	public void doMember(){
		log.info("멤버 페이지입니다.");
	}
	
	@GetMapping("/admin")
	public void doAdmin(){
		log.info("어드민 페이지입니다.");
	}
	
}



