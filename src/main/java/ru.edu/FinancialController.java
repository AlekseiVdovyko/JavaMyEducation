package ru.edu;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/finance", consumes = MediaType.ALL_VALUE)
public class FinancialController {

    @GetMapping(produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ModelAndView doGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/doGet.jsp");
        modelAndView.addObject("title", "Калькулятор");
        modelAndView.addObject("body", "Калькулятор доходности вклада");
        modelAndView.addObject("sum", "Сумма на момент открытия");
        modelAndView.addObject("percentage", "Процентная ставка");
        modelAndView.addObject("years", "Количество лет");
        return modelAndView;
    }

    @PostMapping(produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ModelAndView doPost(@RequestParam("sum") String sum,
                               @RequestParam("percentage") String percentage,
                               @RequestParam("years") String years) {

        ModelAndView modelAndView = new ModelAndView();

        try {

            if (Integer.parseInt(sum) < 50000) {
                modelAndView.setViewName("/error.jsp");
                modelAndView.addObject("title", "Ошибка");
                modelAndView.addObject("body", "Ошибка");
                modelAndView.addObject("text", "Минимальная сумма на момент");
                modelAndView.addObject("text1", " открытия вклада 50 000 рублей");
            } else {

                int result = ((Integer.parseInt(sum) / Integer.parseInt(percentage)) * Integer.parseInt(years)) + Integer.parseInt(sum);
                modelAndView.setViewName("/doPost.jsp");
                modelAndView.addObject("result", result);
                modelAndView.addObject("title", "Результат");
                modelAndView.addObject("body", "Результат");
                modelAndView.addObject("text", "Итоговая сумма");
                modelAndView.addObject("text1", " рублей");
            }
        } catch (NumberFormatException ex) {
            modelAndView.setViewName("/wrongformat.jsp");
            modelAndView.addObject("title", "Результат");
            modelAndView.addObject("body", "Результат");
            modelAndView.addObject("text", "Неверный формат данных.");
            modelAndView.addObject("text1", "Скорректируйте значения");
        }

        return modelAndView;

    }
}
