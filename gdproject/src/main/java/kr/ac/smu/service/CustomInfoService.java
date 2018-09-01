package kr.ac.smu.service;

import java.util.List;
import java.util.Map;

import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;

public interface CustomInfoService {
	public Map<Integer, CustomPlaceDTO> selectAllCustomsByCustomName(String userId, String customName);
	
	public void addCustom(PlaceDTO place, String user_id, String customName);
	
}
