package kr.ar.mjc.HomeP.controller;

import kr.ar.mjc.HomeP.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/example")
    public String example(Model model){
        Person examplePerson = new Person();
        examplePerson.setId(2l);
        examplePerson.setName("박진원");
        examplePerson.setAge(23);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";  //examle.html을 찾아서 화면에다가 위의 데이터를 가지고 있는 모델을 보내서 그려지게 함
    }
}
