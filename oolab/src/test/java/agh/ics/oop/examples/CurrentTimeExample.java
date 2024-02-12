package agh.ics.oop.examples;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeExample {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(formatter.format(now));
    }
}
