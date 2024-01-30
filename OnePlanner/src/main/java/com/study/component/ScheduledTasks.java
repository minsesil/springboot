package com.study.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.service.ChangePasswordService;


 //주기적으로 실행되는 작업을 처리하는 컴포넌트 클래스.
@Component
public class ScheduledTasks {
	
	@Autowired
	private ChangePasswordService changePasswordService;


    //5분마다 실행되며, 만료된 인증 번호를 자동으로 삭제하는 메서드. (임시닫음)
//	public void deleteExpiredAuthNum() {
//		changePasswordService.deleteExpiredAuthNum();
//	}

}
