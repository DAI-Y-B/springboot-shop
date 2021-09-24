package com.turing.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    private String[] parttens;

    public String[] getParttens() {
        return parttens;
    }

    public void setParttens(String[] parttens) {
        this.parttens = parttens;
    }

    public Date convert(String arg0) {
        Date date = null;
        for (int i = 0; i < parttens.length; i++) {
            SimpleDateFormat format = new SimpleDateFormat(parttens[i]);
            try {
                date = format.parse(arg0);
                return date;
            } catch (ParseException e) {
                System.out.println(parttens[i] + "转换失败");
            }
        }
        return date;
    }

}
