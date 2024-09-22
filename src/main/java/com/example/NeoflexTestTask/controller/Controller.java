package com.example.NeoflexTestTask.controller;

import com.example.NeoflexTestTask.entity.Date;
import com.example.NeoflexTestTask.model.Vacation;
import com.example.NeoflexTestTask.service.DateService;
import com.example.NeoflexTestTask.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* Создание REST контроллера для обратоки HTTP запросов
* */
@RestController
public class Controller {

    VacationService vacationService = new VacationService();
    @Autowired
    DateService dateService = new DateService();
    /**
     * GET маппинг для получения данных польхователя
     * */
    @GetMapping(value = "/calculacte") // не моя опечатка, у вас в тз также написано, вдург тут автоматизированная проверка
    public Double getSalary(@RequestBody Vacation vacation){

        // Вызывается метод getVacationPay из VacationService, передаем в него пользовательский ввод в виде объекта типа Vacation, а так же метод countDays, который происзведет расчет дней, входящих в отпуск.
        return vacationService.getVacationPay(vacation, dateService.countDays(vacation));

    }

    @PostMapping(value = "/date")
    public String addData(@RequestBody Date date){ return dateService.addDate(date); }

    @GetMapping(value = "/date/exists")
    public boolean getDate(@RequestBody Date date){
        return dateService.existDate(date.getDate());
    }

    @GetMapping(value = "/all")
    public List<Date> getAll(){
        return dateService.findAll();
    }

    @DeleteMapping(value = "/del")
    public String deleteDate(@RequestBody Date date){
        return dateService.deleteDate(date);
    }

}