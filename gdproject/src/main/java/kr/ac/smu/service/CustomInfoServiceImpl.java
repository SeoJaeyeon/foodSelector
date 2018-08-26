package kr.ac.smu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.mybatis.mapper.CustomMapper;
import kr.ac.smu.mybatis.mapper.PlaceMapper;

@Service
public class CustomInfoServiceImpl implements CustomInfoService {
	
	@Autowired
	private CustomMapper customMapper;
	@Autowired
	private PlaceMapper placeMapper;
	

	@Override
	public List<String> selectAllCustomId(String userId, String customName) {
		// TODO Auto-generated method stub
		return customMapper.selectCustom(userId, customName);
	}

	@Override
	public PlaceDTO selectPlaceByCustomId(String customId) {
		// TODO Auto-generated method stub
		return placeMapper.selectByPlaceId(customId);
	}

	@Override
	@Transactional
	public void addCustom(PlaceDTO place, String user_id, String customName) {
		// TODO Auto-generated method stub
		CustomDTO custom=new CustomDTO();
		custom.setUser_id(user_id);
		custom.setId(place.getId());
		custom.setCustomName(customName);
		customMapper.insertCustom(custom);
		placeMapper.insertPlace(place);
	}

}
