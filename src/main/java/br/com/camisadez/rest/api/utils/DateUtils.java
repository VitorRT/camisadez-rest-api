/**
 * @(#)DateUtils.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.utils;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe <code>DateUtils</code>
 *
 * Esse classe concentra funcionalidades de utilidades relacionadas a datas do sistema.
 * */
public class DateUtils {

    /**
     * Método estático que formata uma data e uma hora para um texto.
     *
     * @param date
     * @param time
     * */
    public static String formatDateAndTime(LocalDate date, LocalTime time) {
        DateTime now = new DateTime();
        DateTime dateTime = new DateTime(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());
        Period period = new Period(dateTime, now);

        if(period.getYears() > 0) {
            if(period.getYears() == 1)
                return "há 1 ano";
            return String.format("há %d anos", period.getWeeks());
        }
        else if (period.getWeeks() > 0) {
            if(period.getWeeks() == 1)
                return "há 1 semana";
            return String.format("há %d semanas", period.getWeeks());
        } else if (period.getDays() > 0) {
            if(period.getDays() == 1)
                return "há 1 dia";
            return String.format("há %d dias", period.getDays());
        } else if (period.getHours() > 0) {
            if(period.getHours() == 1)
                return "há 1 hora";
            return String.format("há %d horas", period.getHours());
        } else if (period.getMinutes() > 0) {
            if(period.getMinutes() == 1)
                return "há 1 minuto";
            return String.format("há %d minutos", period.getMinutes());
        } else {
            return "agora";
        }
    }

    public static boolean isPastDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }
}
