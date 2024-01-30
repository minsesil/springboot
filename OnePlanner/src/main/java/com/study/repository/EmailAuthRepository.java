/*
 * 이메일 인증과 관련된 데이터베이스 상호 작용을 관리하는 레포지토리
 */

package com.study.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.domain.EmailAuthDto;

@Repository
public class EmailAuthRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//인증코드 생성
	public Boolean createAuthCode(EmailAuthDto emailAuthDto) {
		String sql = "INSERT INTO emailauth(user_id, auth_num, created_at, email) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(
				sql, 
				emailAuthDto.getUser_id(), 
				emailAuthDto.getAuth_num(), 
				emailAuthDto.getCreated_at(),
				emailAuthDto.getEmail()
				);
		
		return true;
	}
	
	//이메일로 연결된 인증코드 삭제(실행시키면 데이타 삭제되서 닫음)
//	public Boolean deleteByUserId(Long user_id) {
//		if(user_id != null) {
//			String sql = "DELETE FROM emailauth WHERE user_id=?";
//			jdbcTemplate.update(sql, user_id);
//		}		
//		return true; 
//	}
	
	//모든 이메일 인증정보 조회
	public List<EmailAuthDto> findAll(){
		String sql = "SELECT * FROM emailauth";
		return jdbcTemplate.query(sql, new RowMapper<EmailAuthDto>() {
			@Override
			public EmailAuthDto mapRow(ResultSet rs, int rowNum) throws SQLException{
				//EmailAuthDto emailAuthDto = new EmailAuthDto(rowNum, sql, rowNum, null);
				EmailAuthDto emailAuthDto = new EmailAuthDto();				
				emailAuthDto.setUser_id(rs.getLong("user_id"));
				emailAuthDto.setEmail(rs.getString("email"));
				emailAuthDto.setAuth_num(rs.getString("auth_num"));
				
				//emailAuthDto.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
	            // created_at 컬럼이 null인 경우에 대한 처리 추가
				Timestamp timestamp = rs.getTimestamp("created_at");
				LocalDateTime createdAt = (timestamp != null) ? timestamp.toLocalDateTime() : null;
				emailAuthDto.setCreated_at(createdAt);

				return emailAuthDto;
			}
		});
	}
	
	//특정 사용자의 인증코드 삭제
//	public void deleteByUser_Id(Long user_Id) {
//		String sql = "DELETE FROM emailauth WHERE user_id=?";
//		jdbcTemplate.update(sql, user_Id);
//	}
	
	
	//특정 사용자의 인증정보 조회
	public EmailAuthDto findByUserId(Long user_Id) {
	    try {
	        String sql = "SELECT * FROM emailauth WHERE user_id=?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{user_Id}, (rs, rowNum) -> {
	            return new EmailAuthDto(
	            		rs.getLong("user_id"),
	                    rs.getString("auth_num"),
	                    rs.getTimestamp("created_at").toLocalDateTime(),
	                    rs.getString("email")

	            );
	        });
	    } catch (EmptyResultDataAccessException e) {
	        throw new IllegalArgumentException("No email auth record found for user_id: " + user_Id);
	    }
	}

	
	//이메일로 사용자 ID 찾기(미구현)
	public EmailAuthDto findByUserId(Object userIdByEmail) {
		return null;
	}
	
	//인증정보 생성(미구현)
	public void createAuthDto(EmailAuthDto emailAuthDto) {
		
	}

	public void deleteByEmail(Long userIdByEmail) {
		// TODO Auto-generated method stub
		
	}



	

}
