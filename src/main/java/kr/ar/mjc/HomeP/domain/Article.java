package kr.ar.mjc.HomeP.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity //데이터베이스 테이블과 매핑하는 어노테이션
@Getter //private 으로 선언한 변수들을 외부에서 접근할 수 있도록 지정하는 어노테이션
@Setter //private 으로 선언한 변수들을 외부에서 접근할 수 있도록 지정하는 어노테이션
@NoArgsConstructor //파라미터 없는 생성자 지정 => 아래에 파라미터 있는 생성자를 만들어주었기 때문
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id  // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name="id", updatable = false) //테이블 이름 지정(이름 : id , 업데이트 허용 x)
    private Long id; //아이디 멤버 변수 선언

    @Column(name="title", nullable = false) //(테이블 이름 : title, NULL값 허용 x)
    private String title; //글 제목 멤버 변수 선언

    @Column(name = "content", nullable = false)
    private String content; //글 내용 멤버 변수 선언


    @CreatedDate  //데이터가 생성되면 자동으로 시간을 저장하는 어노테이션
    @Column(name="created_at") //테이블 이름 지정
    private LocalDateTime createdAt; //글이 생성되었을 때의 시간 저장

    @LastModifiedDate //값이 바뀌면 변경된 시간을 저장하는 어노테이션
    @Column(name="updated_at") //테이블 이름 지정
    private LocalDateTime updatedAt; // 글이 수정되었을 때의 시간 저장


    @Builder //
    public Article(String title, String content){ //생성자 넣기
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

    public String getFormattedCreateDate(){ // cr_at와 up_at가 String 타입이 아니기 때문에 보기 좋게 포맷팅하는 함수(속성값으로 사용 가능)

        if(createdAt==null){ //createAt이 없는 경우
            return ""; //빈 문자열 리턴
        }
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //규칙에 맞게 포맷팅되도록 설계
        return createdAt.format(pattern);
    }

}
