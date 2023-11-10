package kr.ar.mjc.HomeP.service;

import kr.ar.mjc.HomeP.domain.Article;
import kr.ar.mjc.HomeP.dto.AddArticleRequest;
import kr.ar.mjc.HomeP.dto.UpdateArticleRequest;
import kr.ar.mjc.HomeP.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  //서비스를 담당할 것이라는 어노테이션 부여
public class BlogService {  //특정한 일을 할 때 어떻게 해야하는 지를 정의

    @Autowired  // 스프링 부트가 자동으로 BlogRepository에서 가져와서 주입(메모리 낭비 방지)
    private BlogRepository blogRepository;  //repository에 있는 것을 가져다가 쓸 것이기 때문에 객체 생성


    public Article save(AddArticleRequest request){  //새로운 글을 저장

        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){  //전체 불러오기

        List<Article> articles = blogRepository.findAll();
        return articles;
    }


    public Article findOne(long id){  //한 개의 글 불러오기
        Article article = blogRepository.findById(id).orElseThrow();
        // orElseThrow() => id를 찾지 못할 경우 에러가 나게 처리함.
        return article;
    }

    public void delete(long id){

        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article= blogRepository.findById(id).orElseThrow();
        // 어떠한 Article의 값을 업데이트 시킬 건지 찾는 코드
        article.update(request.getTitle(), request.getContent());
        //찾아온 Article에 request에 해당하는 글과 내용을 적용
        return article;
    }
}
