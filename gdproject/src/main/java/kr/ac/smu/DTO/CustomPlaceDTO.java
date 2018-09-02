package kr.ac.smu.DTO;

import lombok.Data;


@Data
public class CustomPlaceDTO {
	private String id;
	private String x;
	private String y;
	private String place_name;
	private String category_name;
	private String address_name;
	private String phone;
	private String place_url;
	private String flag="false";
}
