package com.nelbosco.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nelbosco.adapter.GsonLocalDateTimeAdapter;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.GoodsDTO;
import com.nelbosco.service.GoodsService;
import com.nelbosco.util.UiUtils;

@Controller
public class GoodsController extends UiUtils {

	@Autowired
	private GoodsService goodsService;

	@GetMapping(value = "/cafe/menu/{categoryName}")
	public String openGoodsList(@ModelAttribute("params") GoodsDTO goods, @PathVariable(value = "categoryName", required = false) String categoryName, Model model) {
		goods.setCategoryName(categoryName);
		List<GoodsDTO> goodsList = goodsService.getGoodsList(goods);
		if(goodsList != null) {
			model.addAttribute("goodsList", goodsList);
		} else {
			model.addAttribute("goodsList", "");
		}

		return "menu/"+categoryName;
	}
	
	 @GetMapping(value = "/getGoodsList/{categoryName}")
	 public @ResponseBody JsonObject getCommentList(@PathVariable("categoryName") String categoryName, @ModelAttribute("params") GoodsDTO params) {

		JsonObject jsonObj = new JsonObject();
		params.setCategoryName(categoryName);
	 	List<GoodsDTO> goodsList = goodsService.getGoodsList(params);
		if (CollectionUtils.isEmpty(goodsList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(goodsList).getAsJsonArray();
			jsonObj.add("goodsList", jsonArr);
		}

		return jsonObj;
	 }

	@GetMapping(value = "/cafe/menu/detail")
	public String openGoodsDetail(@ModelAttribute("params") GoodsDTO params, @RequestParam(value = "goodsCode", required = false) Long goodsCode, Model model) {
		if (goodsCode == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/cafe/goods/list", Method.GET, null, model);
		}

		GoodsDTO goods = goodsService.getGoodsDetail(goodsCode);
		if (goods == null || "Y".equals(goods.getDeleteYn())) {
			return showMessageWithRedirect("풀절된 상품입니다.", "/cafe/goods/list", Method.GET, null, model);
		}
		model.addAttribute("goods", goods);

		return "menu/view";
	}


}
