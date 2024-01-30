package com.study.domain;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reply {
	@Id
	@SequenceGenerator (
			name="myReplySEQ",
			sequenceName="Reply_SEQ",
			allocationSize = 1
			)
	@GeneratedValue(generator="myReplySEQ")
	private Long rno;
	private String rname;
	private String rcontent;
	private Long refBno;
	
	@CreatedDate
	@Column(name="create_rdate")
	private LocalDateTime redate;

}
