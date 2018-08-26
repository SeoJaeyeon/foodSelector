package kr.ac.smu.service;

import java.util.List;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.DTO.PlaceDTO;

/**
 * 
 * @author seojaeyeon
 * custom place 조회/추가/삭제
 * 변경가능성 - KAKAO API변경에 의존
 */
public interface CustomInfoService {
	public List<String> selectAllCustomId(String userId, String customName);
	public PlaceDTO selectPlaceByCustomId(String customId);
	
	public void addCustom(PlaceDTO place, String user_id, String customName);
	
}
