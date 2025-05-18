package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que contiene información de fecha y hora.")
public class TimeResponseDTO {

    @Schema(description = "Año de la fecha y hora.", example = "2025")
    private int year;

    @Schema(description = "Mes de la fecha y hora.", example = "5")
    private int month;

    @Schema(description = "Día del mes de la fecha y hora.", example = "17")
    private int day;

    @Schema(description = "Hora del día.", example = "14")
    private int hour;

    @Schema(description = "Minuto de la hora.", example = "30")
    private int minute;

    @Schema(description = "Segundos de la hora.", example = "45")
    private int seconds;

    @Schema(description = "Milisegundos de la hora.", example = "500")
    private int milliSeconds;

    @Schema(description = "Fecha y hora en formato string.", example = "2025-05-17T14:30:45.500")
    private String dateTime;

    @Schema(description = "Fecha en formato string.", example = "2025-05-17")
    private String date;

    @Schema(description = "Hora en formato string.", example = "14:30:45")
    private String time;

    @Schema(description = "Zona horaria de la fecha y hora.", example = "GMT-03:00")
    private String timeZone;

    @Schema(description = "Día de la semana.", example = "Lunes")
    private String dayOfWeek;

    @Schema(description = "Indica si el horario de verano está activo.", example = "true")
    private boolean dstActive;

    // Getters y setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(int milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isDstActive() {
        return dstActive;
    }

    public void setDstActive(boolean dstActive) {
        this.dstActive = dstActive;
    }
}
