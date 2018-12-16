package service;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Detail;
import mapper.DetailMapper;
@Service
public class DetailService {
   DetailMapper detailMapper;
public void setDetailMapper(DetailMapper detailMapper) {
	this.detailMapper = detailMapper;
}
   public void addDetail(Detail detail) {
	   detailMapper.addDetail(detail);
   }
   
 
   
}
