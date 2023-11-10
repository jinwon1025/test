package kr.ar.mjc.HomeP.controller;

import kr.ar.mjc.HomeP.domain.Article;
import kr.ar.mjc.HomeP.dto.AddArticleRequest;
import kr.ar.mjc.HomeP.dto.UpdateArticleRequest;
import kr.ar.mjc.HomeP.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //역할 부여
public class BlogController { //어떤 요청이 왔을 때 어떤 일을 해야하는지

    @Autowired //매번 생성해서 쓰지 않고 생성한 것을 부여받아서 씀
    private BlogService blogService; //BlogController는 BlogService를 가지고 역할 수행

    @PostMapping("/api/articles") // "/api/articles"라는 요청이 들어오면 요청을 받아서 처리할 메소드 작성
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        //사용자가 파라미터로 title과 content를 보내주는 것을 AddArticleRequest 클래스에서 받아서 처리하게 함.
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
        // 블로그글을 닫으면 바로 데이터베이스에 저장되게 만듦

        //Postman에서 보내온 데이터를 AddArticleRequest 클래스의 title과 content가 담겨진 상태로 스프링이 만들어서 파라미터로 실행
        //

    }


    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> findAllArticle() { // 전체 글 목록이 응답으로 (article 하나가 아니고 배열이 오게)
        List<Article> articles = blogService.findAll();
        return ResponseEntity.ok().body(articles);

    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable long id){
        //PathVariable : {id} 해당 자리에 있는 수를 long 타입으로 넣어서 함수를 실행
        Article article=blogService.findOne(id);
        return ResponseEntity.ok().body(article);
        // 한 개의 글을 응답 본문에 포함하고 http 상태 코드 200(OK)를 반환하는
        // ResponseEntity 객체를 생성하여 클라이언트에게 반환
    }

    @DeleteMapping("/api/articles/{id}")  //삭제에 해당하는 요청
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")   //업데이트에 해당하는 요청 , 제목+내용 -> url에 불러올 수 없음(길수도 있어서)
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest updateArticle){
        // 글 제목과 글 내용을 받기 위한 클래스 : /dto(데이터를 주거나 받기 위해 만드는 클래스)/UpdateArticleRequest
        // 파라미터 : 사용자가 수정하려고 입력한 글과 내용   +  아이디
        Article article = blogService.update(id,updateArticle);
        return ResponseEntity.ok().body(article);
    }
}
