package kr.ac.smu.DTO;

import lombok.Data;


// 음식점 리스트 담는   
@Data
public class PlaceDTO {
	private String id;
	private String place_name;
	private String category_group_name;
	private String category_name;
	private String category_group_code;
	private String phone;
	private String address_name;
	private String road_address_name;
	private String x;
	private String y;
	private String place_url;
	private String distance;
	private boolean flag=false; 
}
