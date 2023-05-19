package com.example.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.admin.dto.AdminDTO;

import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;

@Mapper
@Repository
public interface AdminDAO {	
	public int insertAdmin(AdminDTO dto);
	public AdminDTO selectById(String admin_id);
	public int idCheck(String email);
	public int updateAdmin(AdminDTO dto);
	public List<ReviewDTO> editSpoiler();
	public List<ReviewDTO> editReport();
	public void deleteReview (ReviewInfoDTO deletereview);
	public void blurSpoiler(ReviewInfoDTO blurspoiler);
	public void returnSpoiler(ReviewInfoDTO returnspoiler);
	//public void해주는 이유는 리턴할 값이 없을때 void를 쓰는데 델리트 리뷰나 블러 스포일러
//	같은 경우는 값을 리턴해줄 필요없고 상태만 업데이트 해주기 떄문이다 (댓글을 블러, 또는 삭제는 업데이트 해주는 것 )

}
