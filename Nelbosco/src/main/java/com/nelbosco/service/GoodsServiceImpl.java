package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.GoodsDTO;
import com.nelbosco.mapper.GoodsMapper;
import com.nelbosco.paging.PaginationInfo;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;


	@Override
	public GoodsDTO getGoodsDetail(Long goodsCode) {
		return goodsMapper.selectGoodsDetail(goodsCode);
	}


	@Override
	public List<GoodsDTO> getGoodsList(GoodsDTO goods) {
		List<GoodsDTO> goodsList = Collections.emptyList();

		int goodsTotalCount = goodsMapper.selectGoodsTotalCount(goods);

		PaginationInfo paginationInfo = new PaginationInfo(goods);
		paginationInfo.setTotalRecordCount(goodsTotalCount);

		goods.setPaginationInfo(paginationInfo);

		if (goodsTotalCount > 0) {
			goodsList = goodsMapper.selectGoodsList(goods);
		}

		return goodsList;
	}

}
