package kr.ar.mjc.HomeP.repository;

import kr.ar.mjc.HomeP.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> { //블로그를 다루기 위한 인터페이스
}
