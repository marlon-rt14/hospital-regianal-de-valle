package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//CLASE PARA ACCEDER Y MANIPULAR EL ARCHIVO DE TEXTO DE LAS CITAS

public class FacadeCitas {

	//declarar variables con la ruta de los archivos de texto
	public String rutaCitas = "/home/marlon97/Documentos/freelance/netbeans/98 429 7362/output/citas.txt";
	private String rutaTemp = "/home/marlon97/Documentos/freelance/netbeans/98 429 7362/output/temp.txt";
	
	//instanciar la clase 
	FacadeConsultorios facadeConsultorios = new FacadeConsultorios();

	//obtener el ultimo indice del arhivo de texto 
	private int getCont() {
		int cont = 1;
		String linea = "";
		//declarar una varibale "lecto" de la clase buffer
		BufferedReader lector = null;
		try {
			// inicializar un nuevo lector con un nuevo lector de archivos
			// con la ruta del archivo de texto de las citas
			lector = new BufferedReader(new FileReader(rutaCitas));
			//recorrer cada linea del buffer
			while ((linea = lector.readLine()) != null) {
				//convertir a una array la linea actual separando los elementos por una "coma"
				String[] datos = linea.split(",");
				cont = Integer.parseInt(datos[0]);
			}
			cont++;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
				"No se puede leer el archivo de citas para obtener el indice.",
				"Advertencia",
				JOptionPane.WARNING_MESSAGE);
			cont = 1;
		} finally {
			//cerrar el lector
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return cont;
	}

	//metodo ejecutable para eliminar las lineas vacias del archivo de texto
	public void removeSeparator() throws IOException {
		String linea = "";
		File original = new File(rutaCitas);
		File temp = new File(rutaTemp);
		BufferedReader lector = lector = new BufferedReader(new FileReader(original));
		BufferedWriter escritor = escritor = new BufferedWriter(new FileWriter(temp));
		while ((linea = lector.readLine()) != null) {
			if (!linea.trim().equals("")) {
				escritor.write(linea + "\n");
			}
		}
		escritor.close();
		lector.close();
		original.delete();
		temp.renameTo(original);
	}

	//metodo que devuleve una lista de todas las lineas del archivo de texto
	public ArrayList<String> getCitas() {
		ArrayList<String> listaCitas = new ArrayList<>();
		String linea = "";
		BufferedReader lector = null;
		try {
			lector = new BufferedReader(new FileReader(rutaCitas));
			while ((linea = lector.readLine()) != null) {
				listaCitas.add(linea);
			}
		} catch (IOException e) {
			listaCitas = null;
		} finally {
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return listaCitas;

	}

	//metodo ejecutabla para guardar un nuevo paciente
	public void setPaciente(String nombre, String apellidoP, String apellidoM,
		String fecha, String hora, String consultorio, String descripcion) {
		//declarar una varible lector de la clase FileWriter
		FileWriter lector = null;
		try {
			//inicializar el lector con el archivo de texto actual
			//indicando como parametro true par agregar nuevos datos
			lector = new FileWriter(rutaCitas, true);
			BufferedWriter escritor = new BufferedWriter(lector);
			escritor.write(getCont() + "," + nombre
				+ "," + apellidoP + "," + apellidoM
				+ "," + fecha + "," + hora
				+ "," + consultorio + "," + descripcion
				+ "\n");
			//cerrar el escritor
			escritor.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			//cerrar el lector
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	//metodo para actualizar los datos del paciente seleccioando
	public void updatePaciente(int id, String nombre, String apellidoP, String apellidoM,
		String fecha, String hora, String consultorio, String descripcion) throws IOException {
		String linea = "";
		File original = new File(rutaCitas);
		File temporal = new File(rutaTemp);
		BufferedReader lector = new BufferedReader(new FileReader(original));;
		BufferedWriter escritor = new BufferedWriter(new FileWriter(temporal));

		while ((linea = lector.readLine()) != null) {
			String[] datos = linea.split(",");
			if (Integer.parseInt(datos[0]) == id) {
				escritor.write(id + "," + nombre
					+ "," + apellidoP + "," + apellidoM
					+ "," + fecha + "," + hora
					+ "," + consultorio + "," + descripcion
					+ "\n");
			}else{
				escritor.write(linea + "\n");
			}
		}
		escritor.close();
		lector.close();
		original.delete();
		temporal.renameTo(original);
	}

	//metodo ejecutable para eliminar el paciente seleccionado
	public void deletePaciente(int id) throws IOException {
		String linea = "";
		File original = new File(rutaCitas);
		File temporal = new File(rutaTemp);
		BufferedReader lector = new BufferedReader(new FileReader(original));
		BufferedWriter escritor = new BufferedWriter(new FileWriter(temporal));
		while ((linea = lector.readLine()) != null) {
			String[] datos = linea.split(",");
			if (Integer.parseInt(datos[0]) != id) {
				escritor.write(linea + "\n");
			}
		}
		escritor.close();
		lector.close();
		original.delete();
		temporal.renameTo(original);
		
		//liberar el consultorio del paciente seleccionado
		facadeConsultorios.updateConsultorioPaciente(id);
	}
}
