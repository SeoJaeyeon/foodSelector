package kr.ac.smu.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.ac.smu.DTO.CustomPlaceDTO;
import kr.ac.smu.DTO.PlaceDTO;

@Mapper
public interface PlaceMapper {
	
	
	@Select("SELECT* FROM place WHERE id=#{id} limit 1")
	public PlaceDTO selectByPlaceId(@Param("id") String id);
	@Select("SELECT* FROM place WHERE place_name=#{id} limit 1")
	public PlaceDTO selectByPlaceName(@Param("id") String id);

	@Insert("INSERT INTO place values(#{place.id},#{place.x}, #{place.y},#{place.place_name},#{place.category_name},#{place.address_name}, #{place.phone},#{place.place_url})")
	public void insertPlace(@Param("place") CustomPlaceDTO place);


}
