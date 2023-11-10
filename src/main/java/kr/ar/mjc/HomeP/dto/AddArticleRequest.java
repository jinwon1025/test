package kr.ar.mjc.HomeP.dto;

import kr.ar.mjc.HomeP.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //파라미터 없는 (기본)생성자 추가
@AllArgsConstructor  // 모든 멤버변수를 가지는 생성자 추가
@Getter

public class AddArticleRequest {  //글 추가 요청을 받아주기 위한 클래스

    private String title;  //글 추가에 필요한 멤버 변수를 갖고 있게끔 함.
    private String content;  // 글 추가에 필요한 멤버 변수를 갖고 있게끔 함.

    public Article toEntity(){  //객체 생성
        Article article = new Article(title,content);
        return article;
    }
}
