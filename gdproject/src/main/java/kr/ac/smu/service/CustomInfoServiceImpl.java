package kr.ac.smu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.dao.CustomDAO;
import kr.ac.smu.mybatis.mapper.CustomMapper;
import kr.ac.smu.mybatis.mapper.PlaceMapper;

@Service
public class CustomInfoServiceImpl implements CustomInfoService {
	
	@Autowired
	private CustomDAO customDao;
	
	@Override
	@Transactional
	public Map<Integer, CustomPlaceDTO> selectAllCustomsByCustomName(String userId, String customName) {
		List<String> ids=customDao.findAllCustomIdByCustomName(userId, customName);
		Map<Integer, CustomPlaceDTO> customs=new HashMap<Integer, CustomPlaceDTO>();
		
		for(int i=0; i<ids.size(); i++){
			customs.put(i, customDao.findCustomPlaceById(ids.get(i)));
		}
		return customs;
	}

	@Override
	public void addCustom(PlaceDTO place, String user_id, String customName) {
		// TODO Auto-generated method stub
		
	}

}
