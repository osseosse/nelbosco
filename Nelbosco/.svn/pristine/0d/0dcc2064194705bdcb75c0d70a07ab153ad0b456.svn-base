package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {

	public GoodsDTO selectGoodsDetail(Long goodsCode);

	public List<GoodsDTO> selectGoodsList(GoodsDTO goods);

	public int selectGoodsTotalCount(GoodsDTO goods);

}
