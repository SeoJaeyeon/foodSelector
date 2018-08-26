package kr.ac.smu.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.ac.smu.DTO.CustomDTO;

@Mapper
public interface CustomMapper {

	
	@Select("SELECT id FROM custom WHERE user_id=#{userId} and customName=#{customName}")
	public List<String> selectCustom(@Param("userId") String userId, @Param("customName") String customName);

	@Insert("INSERT INTO custom VALUES(#{pre.userId},#{pre.id},#{pre.customName}")
	public void insertCustom(@Param("pre") CustomDTO pre);
	
}	
