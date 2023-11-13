package kr.ar.mjc.HomeP.controller;

import kr.ar.mjc.HomeP.domain.Article;
import kr.ar.mjc.HomeP.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogViewController {

    @Autowired
    BlogService blogService;

    @GetMapping("/articles")
    public ModelAndView getArticles(){  // 전체 글 목록 보여주기
        //controller에서는 항상 모델(화면에 보여줘야 하는 데이터)과 view위치(예시 : example.html)를 보내줘야함.

        ModelAndView mav = new ModelAndView(); //객체 선언
        List<Article> articles = blogService.findAll();  //글 전체목록 가져오기
        mav.addObject("articles", articles); //모델(객체) 넣기
        mav.setViewName("articleList"); // view 지정
        return mav;
    }

    @GetMapping("/articles/{id}")  //각 데이터 테이블의 id가 {id}로 들어가게 되고 함수가 실행됨.
    public ModelAndView getArticle(@PathVariable long id){  //id가 {id}와 매칭 된다는 것을 인식시킴.

        ModelAndView mav= new ModelAndView();
        Article article = blogService.findOne(id); //글 1개 가져오기
        mav.addObject("article",article);
        mav.setViewName("article");  //view 이름 : article => article.html 생성해야함.
        return mav;


    }

    @GetMapping("/new-article")
    public String createArticle(){
        return "newArticle"; //글을 작성하는 화면에는 데이터가 들어갈 필요가 없기 때문에 바로 리턴
    }


    @GetMapping("/articles/modify/{id}")
    public ModelAndView modifyArticle(@PathVariable long id){ //id가 url에 포함되어 있는 id를 가지고 온다는 것을 알려줌.
        ModelAndView mav = new ModelAndView(); //모델과 뷰의 정보를 가지고 있는 객체, 모델 : 수정할 article(글)의 객체 view : 수정 화면
        Article article = blogService.findOne(id); // 블로그 글 1개 가져오기
        mav.addObject("article", article); //오브젝트 설정
        mav.setViewName("articleModify"); //html 이름
        return mav;
    }
}
