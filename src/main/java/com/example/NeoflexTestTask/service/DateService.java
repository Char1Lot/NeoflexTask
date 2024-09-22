package com.example.NeoflexTestTask.service;

import com.example.NeoflexTestTask.entity.Date;
import com.example.NeoflexTestTask.model.Vacation;
import com.example.NeoflexTestTask.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    /**
     * Метод сохранения даты в БД
     * */
    public String addDate(Date date) {
        dateRepository.save(date);
        return "Date record created successfully.";
    }

    /**
     * Метод просмотра всех дат в БД
     * */
    public List<Date> findAll(){
        return dateRepository.findAll();
    }

    /**
     * Метод проверяющий наличие даты в БД, используется при подсчете дней отпуска
     * */
    public boolean existDate(java.util.Date date){
        return dateRepository.existsByDate(date);
    }

    public String deleteDate(Date date){
        dateRepository.delete(date);
        return "Deleting successfully.";
    }

    /**
     * Метод подсчитывающий кол-во дней из отпуска выпадающих на даты праздников, хочелось бы сделать в VacationService но возникли трудности, которые я не смог решить
     * */
    public int countDays(Vacation vacation){

        if(vacation.getDate() == null){
            return vacation.getDays();
        }
        //Принимает объект Vacation, который является пользовательским инпутом, вытаскивает из него дату начала отпуска
        java.util.Date date = vacation.getDate();
        //переменная отвечающая за колличество занятых дней
        int count = 0;

        //по циклу пробегаемся по каждому дню отпуска, проверяя его наличие в бд, что будет значить что этот день праздничный и не учитывается в расчете отпускных
        for(int i = 0; i < vacation.getDays(); i++){
            if(!existDate(date)){
                count++; // инкрементируем кол-во дней, попадающих под расчет, то есть если дата отсутствует в БД(!existDate(date)), то этот день идет в расчет отпускных
            }
            date = new java.util.Date(date.getTime() + (1000*60*60*24)); // переходим на следующий день отпуска
        }

        return count;


    }

}
