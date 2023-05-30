package com.example.demo.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoTest {
    public static void main(String[] args) {
        String text = "22.345678,99.876543";




        if (text.matches("\\d{2}.\\d{6},\\d{2}.\\d{6}")) {
            System.out.println("Valore trovato: " );

        }else{
            System.out.println("Nessuna corrispondenza trovata.");
        }
    }
}
