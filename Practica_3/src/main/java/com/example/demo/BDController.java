package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

	//Hash nombre apellidos y fecha, con clientes con esos datos :) 
	private Map<String, ArrayList<Cliente>> clientes = new HashMap<String, ArrayList<Cliente>>();
	
	private Set<Compra> compras = new HashSet<>();
	private Set<Lugar> lugares = new HashSet<>();
	private Set<Producto> productos = new HashSet<>();
	private Set<Tiempo> tiempos = new HashSet<>();
	
	

	@PostConstruct
	private void initDatos(){
		parseCsv3();
		parseCsv2();
		parseCsv1();
		limpiarResultados();
		guardarParseos();
	}

	// Parsea el fichero csv hechos 1 y añade a base de datos
	private  void parseCsv1() {
		try { 
			List<String[]> allData = crearParser("csvs/Practica_3_SSII_hechos1.csv");
			for (String[] row : allData) {
				Cliente c = new Cliente(row[1],row[2],"",row[3],Integer.parseInt(row[4]),monthByName(row[5],"MMMM"),Integer.parseInt(row[6]));

			
                Compra compra = new Compra(Integer.parseInt(row[10]),Integer.parseInt(row[11]),c);
                List<Compra> compras_cliente = new ArrayList<Compra>();
                compras_cliente.add(compra);
                c.setCompra(compras_cliente);

				Lugar lugar = new Lugar();
				lugar.setCapital(row[7]);

				Producto producto = new Producto(row[8],row[9]);

				String[] dias = row[12].split("/");
				String dia = dayOfADate(row[12]);
				Tiempo tiempo = new Tiempo(dia, Integer.parseInt(dias[0]), monthByNumber(Integer.parseInt(dias[1])),
						Integer.parseInt(dias[1]), Integer.parseInt(dias[2]), esFinSemana(dia));

				if (!clientes.containsKey(c.getClave())) {
					clientes.put(c.getClave(), new ArrayList<Cliente>(Arrays.asList(c)));
				} else {

					boolean cliente_igual = false;
					ArrayList<Cliente> lista_cliente = new ArrayList<Cliente>();
					lista_cliente = clientes.get(c.getClave());
					for (int i = 0; i < lista_cliente.size() || cliente_igual; i++) {
						Cliente client = lista_cliente.get(i);
						if (client.equals(c)) {
							if (!client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()
									&& !client.getCorreo().equals(c.getCorreo())) {
								lista_cliente.add(c);
								clientes.put(c.getClave(), lista_cliente);
							} else {
								if (!client.getDominio().isEmpty() && !c.getDominio().isEmpty()
										&& !client.getDominio().equals(c.getDominio())) {
									lista_cliente.add(c);
									clientes.put(c.getClave(), lista_cliente);
								} else {
									if (client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()) {
										client.setCorreo(c.getCorreo());
									}
									if (client.getDominio().isEmpty() && !c.getDominio().isEmpty()) {
										client.setDominio(c.getDominio());
									}
									lista_cliente.set(i, client);
									clientes.put(c.getClave(), lista_cliente);
									compra.setCliente(client);
									cliente_igual = true;
								}

							}

						} // Fin del if
					} // Fin del for

					if (!cliente_igual) {
						lista_cliente.add(c);
						clientes.put(c.getClave(), lista_cliente);
					} 
					
				}

                if (!lugares.contains(lugar))
                    lugares.add(lugar);
                if (!productos.contains(producto))
                    productos.add(producto);
                if (!tiempos.contains(tiempo))
                    tiempos.add(tiempo);
                if (!compras.contains(compra))
                    compras.add(compra);

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
						Integer.parseInt(fechas[0]),monthByName(fechas[1],"MMMM"),Integer.parseInt(fechas[2]));

				Compra compra = new Compra(Integer.parseInt(row[8]),Integer.parseInt(row[7]),c);
                List<Compra> compras_cliente = new ArrayList<Compra>();
                compras_cliente.add(compra);
                c.setCompra(compras_cliente);

				Lugar lugar = new Lugar(row[4],row[3]);

				Producto producto = new Producto(row[5],row[6]);

				String[] dias = row[9].split("/");
				String dia = dayOfADate(row[9]);
				Tiempo tiempo = new Tiempo(dia ,Integer.parseInt(dias[0]),monthByNumber(Integer.parseInt(dias[1])),
						Integer.parseInt(dias[1]),Integer.parseInt(dias[2]),esFinSemana(dia));

				if (!clientes.containsKey(c.getClave())) {
					clientes.put(c.getClave(), new ArrayList<Cliente>(Arrays.asList(c)));
				} else {

					boolean cliente_igual = false;
					ArrayList<Cliente> lista_cliente = new ArrayList<Cliente>();
					lista_cliente = clientes.get(c.getClave());
					for (int i = 0; i < lista_cliente.size() || cliente_igual; i++) {
						Cliente client = lista_cliente.get(i);
						if (client.equals(c)) {
							if (!client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()
									&& !client.getCorreo().equals(c.getCorreo())) {
								lista_cliente.add(c);
								clientes.put(c.getClave(), lista_cliente);
							} else {
								if (!client.getDominio().isEmpty() && !c.getDominio().isEmpty()
										&& !client.getDominio().equals(c.getDominio())) {
									lista_cliente.add(c);
									clientes.put(c.getClave(), lista_cliente);
								} else {
									if (client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()) {
										client.setCorreo(c.getCorreo());
									}
									if (client.getDominio().isEmpty() && !c.getDominio().isEmpty()) {
										client.setDominio(c.getDominio());
									}
									lista_cliente.set(i, client);
									clientes.put(c.getClave(), lista_cliente);
									compra.setCliente(client);
									cliente_igual = true;
								}

							}

						} // Fin del if
					} // Fin del for

					if (!cliente_igual) {
						lista_cliente.add(c);
						clientes.put(c.getClave(), lista_cliente);
					} 
					
				}

				
				
                if (!lugares.contains(lugar))
                    lugares.add(lugar);
                if (!productos.contains(producto))
                    productos.add(producto);
                if (!tiempos.contains(tiempo))
                    tiempos.add(tiempo);
                if (!compras.contains(compra))
                    compras.add(compra);
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
						Integer.parseInt(row[2]),monthByName(row[3],"MMM"),Integer.parseInt(row[4]));

				Compra compra = new Compra(Integer.parseInt(row[10]),Integer.parseInt(row[11]),c);
                List<Compra> compras_cliente = new ArrayList<Compra>();
                compras_cliente.add(compra);
                c.setCompra(compras_cliente);

				Lugar lugar = new Lugar(row[6],row[5],Integer.parseInt(row[7]));

				Producto producto = new Producto(row[8],row[9]);

				String[] dias = row[12].split("/");
				String dia = dayOfADate(row[12]);
				Tiempo tiempo = new Tiempo(dia ,Integer.parseInt(dias[0]),monthByNumber(Integer.parseInt(dias[1])),
						Integer.parseInt(dias[1]),Integer.parseInt(dias[2]),esFinSemana(dia));


				if (!clientes.containsKey(c.getClave())) {
					clientes.put(c.getClave(), new ArrayList<Cliente>(Arrays.asList(c)));
				} else {

					boolean cliente_igual = false;
					ArrayList<Cliente> lista_cliente = new ArrayList<Cliente>();
					lista_cliente = clientes.get(c.getClave());
					for (int i = 0; i < lista_cliente.size() || cliente_igual; i++) {
						Cliente client = lista_cliente.get(i);
						if (client.equals(c)) {
							if (!client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()
									&& !client.getCorreo().equals(c.getCorreo())) {
								lista_cliente.add(c);
								clientes.put(c.getClave(), lista_cliente);
							} else {
								if (!client.getDominio().isEmpty() && !c.getDominio().isEmpty()
										&& !client.getDominio().equals(c.getDominio())) {
									lista_cliente.add(c);
									clientes.put(c.getClave(), lista_cliente);
								} else {
									if (client.getCorreo().isEmpty() && !c.getCorreo().isEmpty()) {
										client.setCorreo(c.getCorreo());
									}
									if (client.getDominio().isEmpty() && !c.getDominio().isEmpty()) {
										client.setDominio(c.getDominio());
									}
									lista_cliente.set(i, client);
									clientes.put(c.getClave(), lista_cliente);
									compra.setCliente(client);
									cliente_igual = true;
								}

							}

						} // Fin del if
					} // Fin del for

					if (!cliente_igual) {
						lista_cliente.add(c);
						clientes.put(c.getClave(), lista_cliente);
					} 
					
				}

				
				
                if (!lugares.contains(lugar))
                    lugares.add(lugar);
                if (!productos.contains(producto))
                    productos.add(producto);
                if (!tiempos.contains(tiempo))
                    tiempos.add(tiempo);
                if (!compras.contains(compra))
                    compras.add(compra);
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

	// Dado el nombre y su patron del mes te devuelve su numero
	private  int monthByName(String month,String pattern){
		Date date;
		date = null;
		try {
			date = new SimpleDateFormat(pattern, new Locale("es", "ES")).parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance(new Locale("es", "ES"));
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
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
		List<Cliente> client = new ArrayList<Cliente>();
		for (List<Cliente> l: clientes.values()) {
			client.addAll(l);
		}
		repositorio_cliente.saveAll(client);
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
    // Elimina duplicados, actualiza referencias
    private void limpiarResultados(){
        //Map<String, Cliente> mapa_clientes = clientes.stream().collect(Collectors.toMap(Cliente::getClave, e -> e));

        //for (Cliente c: compras.iterator().){

		//}


    }
}
