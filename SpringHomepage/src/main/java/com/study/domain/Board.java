package com.study.domain;


import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Board {
	@Id
	@SequenceGenerator(
			name = "myBoardSEQ",
			sequenceName = "Board_SEQ", 
			allocationSize = 1
			)
	@GeneratedValue(generator="myBoardSEQ")
	private Long bno;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;
	
	@Column(insertable=false, columnDefinition="NUMBER DEFAULT 0")
	private Long count;
	
	// 생성되는 시간을 자동으로 넣어줌
	@CreatedDate
	@Column(name="create_date")
	private LocalDate createDate;
	
	// 엔티티가 수정될 때 수정시간을 넣어줌
	@LastModifiedDate
	@Column(name="update_date")
	private LocalDate updateDate;


}