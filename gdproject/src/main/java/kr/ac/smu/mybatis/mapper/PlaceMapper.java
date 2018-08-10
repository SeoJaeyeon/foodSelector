package kr.ac.smu.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.ac.smu.DTO.PlaceDTO;

@Mapper
public interface PlaceMapper {
	
	
	@Select("SELECT* FROM place WHERE id=#{id} limit 1")
	public PlaceDTO selectByPlaceId(@Param("id") String id);
	@Select("SELECT* FROM place WHERE place_name=#{id} limit 1")
	public PlaceDTO selectByPlaceName(@Param("id") String id);

}
