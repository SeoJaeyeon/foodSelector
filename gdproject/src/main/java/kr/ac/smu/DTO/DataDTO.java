package kr.ac.smu.DTO;

import lombok.Data;

@Data
public class DataDTO {
	String x;
	String y;
	
	@Override
	public String toString(){
		return "{x:"+this.x+", \"y\":"+this.y+"}";
	}
}
