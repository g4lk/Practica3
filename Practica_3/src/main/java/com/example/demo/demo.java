package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class demo {
    public static void main(String[] args){
//        Cliente c = new Cliente(1,"Elena","Ocon","","urjc.es",11,22,2018);
//        Cliente c2 = new Cliente(2,"Elena","Ocon","","urjc.es",11,22,2018);
//        Set<Cliente> s = new HashSet<>();
//        s.add(c);
//        System.out.println(s.contains(c2));
//        Lugar l = new Lugar("Madrid","España",12121);
//        Lugar l2 = new Lugar();
//        l2.setCapital("Madrid");
//        Set<Lugar> ls = new HashSet<>();
//        ls.add(l);
//        System.out.println(ls.contains(l2));
//        Producto a = new Producto("abc","chd");
//        Producto b = new Producto("abc","ahd");
//        Set<Producto> ps = new HashSet<>();
//        ps.add(a);
//        System.out.println(ps.contains(b));
        Date date;
        date = null;
        try {
            date = new SimpleDateFormat("MMM", new Locale("es", "ES")).parse("Feb.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
        cal.setTime(date);
        System.out.println(cal.get(Calendar.MONTH));
        date = null;
        try {
            date = new SimpleDateFormat("MMMM", new Locale("es", "ES")).parse("febrero");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal = Calendar.getInstance(new Locale("es", "ES"));
        cal.setTime(date);
        System.out.println(cal.get(Calendar.MONTH));
    }

}
