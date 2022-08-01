package com.coolcode.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.coolcode.domain.BoardAttachVO;
import com.coolcode.mapper.BoardAttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@AllArgsConstructor
public class FileCheckTask {
	private BoardAttachMapper mapper;
	
	@Scheduled(cron= "0 0 2 * * *")  // 매일 새벽 2시마다 적용
	public void checkFiles(){
		log.warn("File check task");
		log.warn("===============");
		log.warn(getFolderYesterday());
		
		File file = new File("c:/upload", getFolderYesterday());
		if(!file.exists()){
			return;
		}
		
		List<BoardAttachVO> dbFiles = mapper.getOldFiles();  //db내 파일들
		
		// List<BoardAttachVO> >> List<File>
		// db내 파일들 > file 타입으로 list
		List<File> dbFiles2 = dbFiles.stream().map(attach -> {
			File f = new File(file, attach.getUuid());
			log.info(f);
			return f;		
		}).collect(Collectors.toList());  
		dbFiles2.forEach(log::warn);
		
		// thumbnail 추가
		dbFiles.stream()
		.filter(BoardAttachVO::isImage)
		.map(attach->new File(file, "s_" + attach.getUuid()))
		.forEach(dbFiles2::add); 
		
		dbFiles.forEach(log::warn);
		
		// db에 존재하지 않는 파일 삭제
		Arrays.asList(file.listFiles(f->!dbFiles2.contains(f))).forEach(File::delete);  // 시스템에 있는 실제 업로드 폴더 내 파일들
	}
	
	private String getFolderYesterday(){
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);  // 매일 전일 파일 체크
	}
}
