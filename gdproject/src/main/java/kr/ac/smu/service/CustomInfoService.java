package kr.ac.smu.service;

import java.util.List;

import kr.ac.smu.DTO.PlaceDTO;

public interface CustomInfoService {
	public List<String> selectAllCustomId(String userId, String customName);
	public PlaceDTO selectPlaceByCustomId(String customId);
}
