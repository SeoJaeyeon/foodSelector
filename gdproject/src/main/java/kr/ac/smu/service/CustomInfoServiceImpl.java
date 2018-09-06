package kr.ac.smu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.dao.CustomDAO;
import kr.ac.smu.dao.PlaceDAO;


@Service
public class CustomInfoServiceImpl implements CustomInfoService {
	
	@Autowired
	private CustomDAO customDao;
	
	@Autowired
	private PlaceDAO placeDao;
	
	@Override
	@Transactional
	public Map<Integer, CustomPlaceDTO> selectAllCustomsByCustomName(String userId, String customName) {
		List<String> ids=customDao.findAllCustomIdByCustomName(userId, customName);
		Map<Integer, CustomPlaceDTO> customs=new HashMap<Integer, CustomPlaceDTO>();
		
		for(int i=0; i<ids.size(); i++){
			customs.put(i, placeDao.findCustomPlaceById(ids.get(i)));
		}
		return customs;
	}

	@Override
	public boolean addCustom(CustomPlaceDTO place, String user_id, String customName) {
		if(placeDao.findCustomPlaceById(place.getId())==null)
			placeDao.insertCustomPlace(place);
		CustomDTO custom=new CustomDTO();
		custom.setCustomName(customName);
		custom.setId(place.getId());
		custom.setUser_id(user_id);
		customDao.insertCustomData(custom);

		return true;
		
	}

	@Override
	public boolean deleteCustom(String id, String user_id, String custom_name) {
		customDao.deleteCustom(id, user_id, custom_name);
		return true;
	}

	@Override
	public Map<Integer, String> selectCustomListById(String userId) {
		Map<Integer,String> customList=new HashMap<Integer, String>();
		List<String> list=customDao.findCustomListById(userId);
		
		for(int i=1; i<=list.size();i++)
			customList.put(i, list.get(i-1));
		return customList;
	}
	

}
