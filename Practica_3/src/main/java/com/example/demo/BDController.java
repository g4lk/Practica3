package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
public class BDController {
	
	private static Map<String,Integer> meses = new HashMap<>();
	private static Map<Integer,String> mesesAlReves = new HashMap<>();
	@Autowired
	private static RepositorioCliente repositorio_cliente;

	@Autowired
	private static RepositorioCompra repositorio_compra;

	@Autowired
	private static RepositorioLugar repositorio_lugar;
	
	@Autowired
	private static RepositorioProducto repositorio_producto;

	@Autowired
	private static RepositorioTiempo repositorio_tiempo;
	
	@PostConstruct
	private void initDatos() {
		meses.put("enero", 1);
		meses.put("febrero", 2);
		meses.put("marzo", 3);
		meses.put("abril", 4);
		meses.put("mayo", 5);
		meses.put("junio", 6);
		meses.put("julio", 7);
		meses.put("agosto", 8);
		meses.put("septiembre", 9);
		meses.put("octubre", 10);
		meses.put("noviembre", 11);
		meses.put("diciembre", 12);
		mesesAlReves.put(1,"enero");
		mesesAlReves.put(2,"febrero");
		mesesAlReves.put(3,"marzo");
		mesesAlReves.put(4, "abril");
		mesesAlReves.put(5,"mayo");
		mesesAlReves.put(6, "junio");
		mesesAlReves.put(7, "julio");
		mesesAlReves.put(8,"agosto");
		mesesAlReves.put(9, "septiembre");
		mesesAlReves.put(10, "octubre");
		mesesAlReves.put(11, "noviembre");
		mesesAlReves.put(12, "diciembre");
		
		parseCsv1("csvs/Practica_3_SSII_hechos1.csv");
		parseCsv2("csvs/Practica_3_SSII_hechos2.csv");
		parseCsv3("csvs/Practica_3_SSII_hechos3.csv");
	}
	
	// Java code to illustrate 
	// Reading CSV File with different separator 
	public static void parseCsv1(String file) {
		
		try { 
			List<String[]> allData = crearParser(file);
			
			for (String[] row : allData) {
				Cliente c = new Cliente(Integer.valueOf(row[0]),row[1],row[2],"",row[4],Integer.valueOf(row[5]),meses.get(row[6]),Integer.valueOf(row[7]));
				Compra compra = new Compra(Integer.valueOf(row[10]),Integer.valueOf(row[11]),c);
				Lugar lugar = new Lugar();
				lugar.setCapital(row[7]);
				Producto producto = new Producto(row[8],row[9]);
				String[] dias = row[12].split("/");
				
				//Tiempo tiempo = new Tiempo(Integer.valueOf(dias[0]),Integer.valueOf(dias[1]),mesesAlReves.get(dias[1]));
				repositorio_cliente.save(c);
				repositorio_compra.save(compra);
				repositorio_lugar.save(lugar);
				repositorio_producto.save(producto);
				//repositorio_tiempo.save(tiempo);
			} 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	// Java code to illustrate 
		// Reading CSV File with different separator 
		public static void parseCsv2(String file) 
		{ 
			try { 
				List<String[]> allData = crearParser(file);

				// Print Data. 
				for (String[] row : allData) { 
					for (String cell : row) { 
						System.out.print(cell + "\t"); 
					} 
					System.out.println(); 
				} 
			} 
			catch (Exception e) { 
				e.printStackTrace(); 
			} 
		} 
		// Java code to illustrate 
		// Reading CSV File with different separator 
		public static void parseCsv3(String file) 
		{ 
			try { 
				List<String[]> allData = crearParser(file);

				// Print Data. 
				for (String[] row : allData) { 
					for (String cell : row) { 
						System.out.print(cell + "\t"); 
					} 
					System.out.println(); 
				} 
			} 
			catch (Exception e) { 
				e.printStackTrace(); 
			} 
		} 
	public static List<String[]> crearParser(String file){
		// Create an object of file reader class with CSV file as a parameter. 
		FileReader filereader;
		try {
			filereader = new FileReader(file);
			// create csvParser object with 
			// custom seperator semi-colon 
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); 

			// create csvReader object with parameter 
			// filereader and parser 
			CSVReader csvReader = new CSVReaderBuilder(filereader)
									.withSkipLines(1)
									.withCSVParser(parser) 
									.build();
			// Read all data at once 
			return csvReader.readAll();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		return null;
	}

}
