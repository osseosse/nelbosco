package com.nelbosco.service;

import java.util.List;

import com.nelbosco.domain.GoodsDTO;

public interface GoodsService {

	public GoodsDTO getGoodsDetail(Long goodsCode);

	public List<GoodsDTO> getGoodsList(GoodsDTO goods);

}
