package com.example.NeoflexTestTask.service;

import com.example.NeoflexTestTask.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Создание сервиса для бизнеслогики, что бы не захламлять контроллер
 *
 */
@Service
public class VacationService {

    @Autowired
    private DateService dateService = new DateService();

    /**
     * Метод, производящий расчет отпускных, учитывая два случая:
     * 1) Дата начала отпуска не была введена пользователем, происзводим расчет по общей формуле
     * 2) Дата начала была введена, передается так же кол-во дней которые будут учитываться, вычесления идут по своей формуле
     * */
    public Double getVacationPay(Vacation vacation, Integer count){

        // Проверяем что пользователь не ввел дату начала отпуска
        if(vacation.getDate() == null){
            return vacation.getSalary() / 29.3 * vacation.getDays();
        }
        // Если все-таки ввел считаем отпускные согластно кол-ву свободных дней
        return vacation.getSalary() / 29.3 * count;

    }

}