package kr.ac.smu.DTO;

import lombok.Data;


// 음식점 리스트 담는   
@Data
public class PlaceDTO extends CustomPlaceDTO{
	private String category_group_name;
	private String category_group_code;
	private String address_name;
	private String road_address_name;
	private String distance;
}
