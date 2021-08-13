package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

//CLASE PARA ACCEDER Y MANIPULAR EL ARCHIVO DE TEXTO DE LOS CONSULTORIOS
public class FacadeConsultorios {

	//declarar variables de las rutas de los archivos de texto
	private String rutaConsultorios = "/home/marlon97/Documentos/freelance/netbeans/98 429 7362/output/consultorios.txt";
	private String rutaTemporal = "/home/marlon97/Documentos/freelance/netbeans/98 429 7362/output/temporal.txt";

	//metodo que nos devuelve una lista de todos las lineas del archivo de texto del consultorio
	public ArrayList<String> getConsultorios() {
		ArrayList<String> listaConsultorios = new ArrayList<>();
		String linea = "";
		BufferedReader lector = null;
		try {
			lector = new BufferedReader(new FileReader(rutaConsultorios));
			while ((linea = lector.readLine()) != null) {
				listaConsultorios.add(linea);
			}
		} catch (IOException e) {
			listaConsultorios = null;
		} finally {
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return listaConsultorios;
	}
	
	//metodo para liberar el consultorio del paciente seleccionado
	public void updateConsultorioPaciente(int idPaciente) throws IOException{
		String linea = "";
		File original = new File(rutaConsultorios);
		File temporal = new File(rutaTemporal);
		BufferedReader lector = new BufferedReader(new FileReader(original));
		BufferedWriter escritor = new BufferedWriter(new FileWriter(temporal));
		while((linea = lector.readLine()) != null){
			String [] datos = linea.split(",");
			if(Integer.parseInt(datos[2]) == idPaciente){
				escritor.write(datos[0] + "," + datos[1] + "," + "0\n");
			}else{
				escritor.write(linea + "\n");
			}
		}
		escritor.close();
		lector.close();
		original.delete();
		temporal.renameTo(original);
	}
}
