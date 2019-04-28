package com.example.demo;

import java.io.BufferedReader;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BDController {

	@Autowired
	private  RepositorioCliente repositorio_cliente;

	@Autowired
	private  RepositorioCompra repositorio_compra;

	@Autowired
	private  RepositorioLugar repositorio_lugar;
	
	@Autowired
	private  RepositorioProducto repositorio_producto;

	@Autowired
	private  RepositorioTiempo repositorio_tiempo;

	private List<Cliente> clientes = new ArrayList<>();
	private List<Compra> compras = new ArrayList<>();
	private List<Lugar> lugares = new ArrayList<>();
	private List<Producto> productos = new ArrayList<>();
	private List<Tiempo> tiempos = new ArrayList<>();

	@PostConstruct
	private void initDatos(){
		parseCsv1();
		parseCsv2();
		parseCsv3();
		guardarParseos();
	}

	// Parsea el fichero csv hechos 1 y añade a base de datos
	private  void parseCsv1() {
		
		try { 
			List<String[]> allData = crearParser("csvs/Practica_3_SSII_hechos1.csv");
			for (String[] row : allData) {
				Cliente c = new Cliente(Integer.valueOf(row[0]),row[1],row[2],"",row[3],Integer.valueOf(row[4]),monthByName(row[5]),Integer.valueOf(row[6]));

				Compra compra = new Compra(Integer.valueOf(row[10]),Integer.valueOf(row[11]),c);

				Lugar lugar = new Lugar();
				lugar.setCapital(row[7]);

				Producto producto = new Producto(row[8],row[9]);

				String[] dias = row[12].split("/");
				String dia = dayOfADate(row[12]);
				Tiempo tiempo = new Tiempo(dia ,Integer.valueOf(dias[0]),monthByNumber(Integer.valueOf(dias[1])),Integer.valueOf(dias[1]),Integer.valueOf(dias[2]),esFinSemana(dia));

				clientes.add(c);
				compras.add(compra);
				lugares.add(lugar);
				productos.add(producto);
				tiempos.add(tiempo);
			}
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	// Parsea el fichero csv hechos 2 y añade a base de datos
	private  void parseCsv2(){
		try {
			List<String[]> allData = crearParser("csvs/Practica_3_SSII_hechos2.csv");

			for (String[] row : allData) {
				String[] nombre_apellidos = row[0].split(" ");
				String dominio = row[1].substring(row[1].indexOf("@")+1);
				String[] fechas = row[2].split(" de ");
				Cliente c = new Cliente(nombre_apellidos[0],nombre_apellidos[1],row[1],dominio,
						Integer.valueOf(fechas[0]),monthByName(fechas[1]),Integer.valueOf(fechas[2]));

				Compra compra = new Compra(Integer.valueOf(row[8]),Integer.valueOf(row[7]),c);

				Lugar lugar = new Lugar();
				lugar.setCapital(row[4]);

				Producto producto = new Producto(row[5],row[6]);

				String[] dias = row[9].split("/");
				String dia = dayOfADate(row[9]);
				Tiempo tiempo = new Tiempo(dia ,Integer.valueOf(dias[0]),monthByNumber(Integer.valueOf(dias[1])),
						Integer.valueOf(dias[1]),Integer.valueOf(dias[2]),esFinSemana(dia));

				clientes.add(c);
				compras.add(compra);
				lugares.add(lugar);
				productos.add(producto);
				tiempos.add(tiempo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Parsea el fichero csv hechos 3 y añade a base de datos
	private  void parseCsv3(){
		try {
			List<String[]> allData = crearParser("csvs/Practica_3_SSII_hechos3.csv");

			for (String[] row : allData) {
				String[] nombre_apellidos = row[0].split(", ");
				Cliente c = new Cliente(nombre_apellidos[1],nombre_apellidos[0],"",row[1],
						Integer.valueOf(row[2]),monthByName3letters(row[3]),Integer.valueOf(row[4]));

				Compra compra = new Compra(Integer.valueOf(row[10]),Integer.valueOf(row[11]),c);

				Lugar lugar = new Lugar();
				lugar.setCapital(row[6]);

				Producto producto = new Producto(row[8],row[9]);

				String[] dias = row[12].split("/");
				String dia = dayOfADate(row[12]);
				Tiempo tiempo = new Tiempo(dia ,Integer.valueOf(dias[0]),monthByNumber(Integer.valueOf(dias[1])),
						Integer.valueOf(dias[1]),Integer.valueOf(dias[2]),esFinSemana(dia));

				clientes.add(c);
				compras.add(compra);
				lugares.add(lugar);
				productos.add(producto);
				tiempos.add(tiempo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Dado un numero te devuelve el nombre del mes en español
	private  String monthByNumber(int month){
		return new DateFormatSymbols(new Locale("es", "ES")).getMonths()[month-1];
	}
	// Dado el nombre completo del mes te devuelve su numero
	private  int monthByName(String month){
		Date date = null;
		try {
			date = new SimpleDateFormat("MMMM", new Locale("es", "ES")).parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	// Dado el nombre abreviado del mes te devuelve su numero
	private  int monthByName3letters(String month){
		Date date;
		date = null;
		try {
			date = new SimpleDateFormat("MMM", new Locale("es", "ES")).parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	// Dada una fecha en tipo string te devuelve el nombre del dia en español
	private  String dayOfADate(String date){
		Date d = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE",new Locale("es", "ES"));
		return sdf.format(d);
	}
	// Comprueba si el día es fin de semana ( sabado o domingo )
	private  boolean esFinSemana(String dia){
		return dia.equalsIgnoreCase("sabado") || dia.equalsIgnoreCase("domingo");
	}
	// Guarda los objetos en base de datos
	private void guardarParseos(){
		repositorio_cliente.saveAll(clientes);
		repositorio_compra.saveAll(compras);
		repositorio_lugar.saveAll(lugares);
		repositorio_producto.saveAll(productos);
		repositorio_tiempo.saveAll(tiempos);
	}
	// Devuelve todas las filas de un csv, sin contar el header
	private List<String[]> crearParser(String file){
		BufferedReader br = null;
		List<String[]> rows = new ArrayList<>();

		try {
			boolean  counter = false;
			br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				if (!counter){
					counter = true;
				} else {
					line = line.replace("Sep.","Sept.");
					String[] campos = line.split(";");
					rows.add(campos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rows;
	}

}
