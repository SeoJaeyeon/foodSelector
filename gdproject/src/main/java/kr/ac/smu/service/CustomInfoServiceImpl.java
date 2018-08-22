package kr.ac.smu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
