package kr.ac.smu.service;

import java.util.Map;

import kr.ac.smu.DTO.CustomPlaceDTO;

public interface CustomInfoService {
	
	public Map<Integer, String> selectCustomListById(String userId);

	public Map<Integer, CustomPlaceDTO> selectAllCustomsByCustomName(String userId, String customName);
	
	public boolean addCustom(CustomPlaceDTO place, String user_id, String customName);
	
	public boolean deleteCustom(String id, String user_id, String custom_name);
	
}
