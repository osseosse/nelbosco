package com.nelbosco.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nelbosco.adapter.GsonLocalDateTimeAdapter;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.MusicDTO;
import com.nelbosco.domain.ReservationDTO;
import com.nelbosco.service.AdminMusicService;
import com.nelbosco.util.UiUtils;

@Controller
public class MusicController extends UiUtils {

	@Autowired
	private AdminMusicService musicService;
	
	@GetMapping(value = "/restaurant/music/music")
	public String openConcertList(@ModelAttribute("params") MusicDTO params, Model model , HttpServletRequest req) {
		
		LocalDate curr = LocalDate.now();
		int curDate = curr.getDayOfWeek().getValue();
		if(curDate==7) curDate=0;
		LocalDate start = curr.minusDays(curDate);
		
		String startDay = curr.minusDays(curDate).toString();
		String endDay = start.plusDays(7).toString();
		
		MusicDTO period = new MusicDTO();
		MusicDTO tmp = new MusicDTO();
		
		String[] dayOfWeek = {"(일)","(월)","(화)","(수)","(목)","(금)","(토)"};
		
		period.setStartDay(startDay);
		period.setEndDay(endDay);
			
		List<MusicDTO> concertList = musicService.getConcertList(period);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

		List<String> dayList = new ArrayList<String>();
		List<String> concertList2 = new ArrayList<String>();
		
		List<MusicDTO> schedule = new ArrayList<MusicDTO>();
			
		for(int i=0;i<7;i++) {
			dayList.add(start.plusDays(i).format(formatter).toString() + dayOfWeek[i]);
			concertList2.add("");
		}
		
		for(int i=0;i<concertList.size();i++) {
			concertList2.set(Integer.parseInt(concertList.get(i).getDay()), concertList2.get(Integer.parseInt(concertList.get(i).getDay()))+concertList.get(i).getTitle()+"<br><br>");
		}
		
		for(int i=0;i<7;i++) {
			tmp.setWeekDay(dayList.get(i));
			tmp.setConcert(concertList2.get(i));
			schedule.add(i, tmp);
			tmp = new MusicDTO();
		}

		model.addAttribute("schedule", schedule);

		return "music/music";
	}

}
