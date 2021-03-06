package com.cafe24.ecoshaur.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;

@Controller
public class RatingCont {
 
  @Autowired
  private RatingDAO dao;
  
  public RatingCont() {
	  System.out.println("---RatingCont()객체 생성 됨");
  }
  
  //전체보기 
  @RequestMapping(value = "Rating.do", method = RequestMethod.GET)
  public ModelAndView List() {
	  ModelAndView mav = new ModelAndView();
	  mav.setViewName("rank/ratingList");   
	  mav.addObject("root", Utility.getRoot());
	  mav.addObject("list", dao.list());	  	
	  return mav;
  }//PointList() end
 
}
