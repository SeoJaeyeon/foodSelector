package kr.ac.smu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.mybatis.mapper.PlaceMapper;

@Repository
public class PlaceDAO {
	
	@Autowired
	private PlaceMapper placeMapper;
	
	public CustomPlaceDTO findCustomPlaceById(String id){
		return placeMapper.selectByPlaceId(id);	
	}
	
	public CustomPlaceDTO insertCustomPlace(CustomPlaceDTO place){
		placeMapper.insertPlace(place);
		return place;
	}

}
