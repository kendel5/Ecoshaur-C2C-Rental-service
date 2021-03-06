package com.cafe24.ecoshaur.community;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class QnaCont {
  @Autowired
  QnaDAO dao;

  //Qna 페이지 호출
  @RequestMapping("Qna.do")
  public ModelAndView Qna() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/Qna");
    return mav;
  }
  
  //Qna 목록
  @RequestMapping("QList.do")
  public ModelAndView QList(int nowpage) {
    int recordPerPage = 8;
    int endRow   = nowpage * recordPerPage;

    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QList");
    mav.addObject("list", dao.list(nowpage, recordPerPage));
    mav.addObject("end", endRow);
    mav.addObject("nowpage", nowpage);
    mav.addObject("recordPerPage", recordPerPage);
    mav.addObject("count", dao.count());
    return mav;
  }

  //Qna 상세보기
  @RequestMapping("QRead.do")
  public ModelAndView Qread(int postno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QRead");
    mav.addObject("dto", dao.read(postno));
    return mav;
  }
  
  //Qna 작성 페이지 호출
  @RequestMapping(value = "QCreate.do", method = RequestMethod.GET)
  public String QCreate() {
    return "community/QCreate";
  }

  //Qna 작성
  @RequestMapping(value = "QCreate.do", method = RequestMethod.POST)
  public ModelAndView QCreate(QnaDTO dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QResult");
    String basePath = req.getRealPath("/community/storage");
    
    MultipartFile posterMF = dto.getPosterMF();

    String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);

    dto.setImage_name(poster);

    int cnt = dao.create(dto);
    if (cnt == 0) {
      mav.addObject("msg",  "<p>문의사항 작성을 실패하였습니다.ㅠㅠ</p>");
    } else {
      mav.addObject("msg",  "<p>문의사항 작성을 성공하였습니다!</p>");
    }
    return mav;
  }
  
  //Qna 답변 페이지 호출
  @RequestMapping(value = "QRes.do", method = RequestMethod.GET)
  public ModelAndView QRes(QnaDTO dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QRes");
    mav.addObject("dto", dao.read(dto.getPostno()));
    mav.addObject("ccode", dto.getCcode());
    return mav;
  }
  
  //Qna 답변 작성
  @RequestMapping(value = "QRes.do", method = RequestMethod.POST)
  public ModelAndView QRes(QnaDTO dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QResult");
    String basePath = req.getRealPath("/community/storage");
    
    MultipartFile posterMF = dto.getPosterMF();

    String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);

    dto.setImage_name(poster);

    int cnt = dao.Rcreate(dto);
    if (cnt == 0) {
      mav.addObject("msg",  "<p>문의사항 작성을 실패하였습니다.ㅠㅠ</p>");
    } else {
      mav.addObject("msg",  "<p>문의사항 작성을 성공하였습니다!</p>");
    }
    return mav;
  }
  
  //Qna 삭제 페이지 호출
  @RequestMapping(value="QDelete.do", method=RequestMethod.GET)
  public ModelAndView QDelete(QnaDTO dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("community/QDelete");
    mav.addObject("dto", dao.read(dto.getPostno()));
    return mav;
  }
  
  //Qna 삭제
  @RequestMapping(value = "QDelete.do", method = RequestMethod.POST)
  public ModelAndView QDelete(QnaDTO dto, HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();  
    mav.setViewName("community/QResult"); 
    int cnt = dao.delete(dto.getPostno());
    if (cnt == 0) {
      mav.addObject("msg",  "<p>문의사항 삭제를 실패하였습니다.ㅠㅠ</p>");
    } else {
      mav.addObject("msg",  "<p>정상적으로 문의사항이 삭제되었습니다!</p>");
    }
    return mav;
  }
}
