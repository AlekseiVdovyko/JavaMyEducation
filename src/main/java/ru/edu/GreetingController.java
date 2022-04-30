package ru.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/author")
public class GreetingController {

    @GetMapping
    public ModelAndView aboutAuthor() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.jsp");
        modelAndView.addObject("title", "Об авторе");
        modelAndView.addObject("author", "Информация об авторе");
        modelAndView.addObject("familyname", "Фамилия: Вдовыко");
        modelAndView.addObject("firstname", "Имя: Алексей");
        modelAndView.addObject("patronymic", "Отчество: Игоревич");
        modelAndView.addObject("hobby", "Хобби: компьютеры, программирование, горный туризм");
        modelAndView.addObject("bitbucket", "Bitbucket url: " +
                "<a href=\"https://bitbucket.org/alekseivdovyko/homework/src/master/\">" +
                "https://bitbucket.org/alekseivdovyko</a>");

        return modelAndView;
    }
}
