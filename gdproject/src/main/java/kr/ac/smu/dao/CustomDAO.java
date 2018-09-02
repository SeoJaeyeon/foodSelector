package kr.ac.smu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.smu.DTO.CustomDTO;
import kr.ac.smu.mybatis.mapper.CustomMapper;

/*
 * create custom data
 * read custom data
 */
@Repository
public class CustomDAO {	
	
	@Autowired
	private CustomMapper customMapper;

	public List<String> findAllCustomIdByCustomName(String userId, String customName){
		return customMapper.selectCustom(userId, customName);
	}
	
	public CustomDTO insertCustomData(CustomDTO custom){
		customMapper.insertCustom(custom);
		return custom;
	}
	
	public boolean deleteCustom(String id, String user_id, String custom_name){
		customMapper.deleteCustom(user_id, id, custom_name);
		return true;
	}

}
