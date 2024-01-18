package com.study.dto;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
@NoArgsConstructor  	//빈생성자 (가장많이쓰임)
@AllArgsConstructor     //모든필드있는생성자 (가장많이쓰임)
*/

/*
@Getter  				//getter메소드
@Setter  				//setter메소드
@RequiredArgsConstructor
@ToString				//ToString
@EqualsAndHashCode
*/

// 위의 어노테이션을 하나로 만든 어노테이션
/*
 * 생성자 1개만 생성됨
 *  - @NonNull이 붙어있는 필드만 매개변수로한 생성자
 *  - @NonNull이 하나도 없을때는 매개변수가 없는 생성자
 */

@NoArgsConstructor // 기본생성자 반드시 넣어준다. 없으면 오류
@Data   // 가장많이쓰임
public class Board {
	@NonNull  			//널값을 가질수 없다.널이면 exception 발생(not null인거 바로위에 붙임)
	private int no;
	private String title;
	private String writer;
	private String content;

}
