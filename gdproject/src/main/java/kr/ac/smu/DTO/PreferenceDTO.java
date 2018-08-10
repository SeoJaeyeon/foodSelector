package kr.ac.smu.DTO;

import lombok.Data;

@Data
public class PreferenceDTO {
	private String user_id;
	private String id;
	private boolean flag;
	private boolean custom;
	private String customName;	
}
