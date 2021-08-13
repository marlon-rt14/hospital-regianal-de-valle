package views;

import app.FacadeCitas;
import app.FacadeConsultorios;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmCitas extends javax.swing.JFrame {

	//declaracion de varibales de tipo clase
	private FacadeCitas facadeCitas;
	private FacadeConsultorios facadeConsultorios;
	
	private boolean estaSeleccionado = false;

	public FrmCitas() {
		initComponents();
		
		//llamar al metodo para cambiar la apariencia por defecto de netbeans
		iniciarDisenio();
		
		//incializar las variables instanciando las clases
		facadeCitas = new FacadeCitas();
		facadeConsultorios = new FacadeConsultorios();
		
		//llamar al metodo para remover lineas vacias del archivo de texto
		removerLineasVacias();
		
		//llamar al metodo para llenar la tabla con los datos del archivo de texto
		llenarTabla();
		
		//llamar al metodo para llenar el combobox con la lista de consultorios disponibles
		cargarConsultorios();
	}

	public void removerLineasVacias() {
		try {
			facadeCitas.removeSeparator();
		} catch (IOException ex) {
//			JOptionPane.showMessageDialog(this,
//				"Error al leer el archivo citas.txt para remover lineas vacias",
//				"Error", JOptionPane.ERROR_MESSAGE);
			//Logger.getLogger(FrmMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void llenarTabla() {
		//recuperar una lista de todas las lineas del archivo de texto de citas
		ArrayList<String> listaCitas = facadeCitas.getCitas();
		
		//validar que la lista no este vacia
		if (listaCitas != null) {
			//obtener el modelo de la tabla para manipular sus propiedades
			DefaultTableModel dtm = (DefaultTableModel) tblCitas.getModel();
			dtm.setRowCount(0);
			
			//bucle para recorrer la lista 
			for (String linea : listaCitas) {
				//dividir la linea de texto separando por una "coma" obteniendo un array
				String[] datos = linea.split(",");
				
				//declarar una variable de tipo objeto que sera la nueva fila de la tabla
				Object[] fila = new Object[datos.length];
				
				//bucle para llenar la fila con los datos de array
				for (int i = 0; i < datos.length; i++) {
					fila[i] = datos[i];
				}
				
				//agregar una nueva fila a la tabla
				dtm.addRow(fila);
			}
		} else { //validar cuando la lista este vecia
//			JOptionPane.showMessageDialog(null,
//				"No se puede leer el archivo de citas.",
//				"Advertencia",
//				JOptionPane.WARNING_MESSAGE);
		}

	}

	public void cargarConsultorios() {
		//recuperar una lista de todas las lineas de texto del archivo de consultorios
		ArrayList<String> listaConsultorios = facadeConsultorios.getConsultorios();
		
		//validar que la lista no este vacia
		if (listaConsultorios != null) {
			//eliminar todos los items del combobox
			cmbConsultorio.removeAllItems();
			
			//bucle para recorrer la lista
			for (String linea : listaConsultorios) {
				//dividir la linea de texto separando por una "coma" obteniendo un array
				String[] datos = linea.split(",");
				//validar solo los consultorios que esten disponibles
				if (Integer.parseInt(datos[2]) == 0) {
					cmbConsultorio.addItem(datos[1]);
				}
			}
		} else { // validar cuando la lista este vacia
			JOptionPane.showMessageDialog(null,
				"No se puede leer el archivo de consultorios para cargar en las citas.",
				"Advertencia",
				JOptionPane.WARNING_MESSAGE);
		}
	}

	public void limpiar() {
		//eliminar el texto de todos los componentes que conforman el formulario
		txtId.setText("");
		txtNombre.setText("");
		txtApellidoPaterno.setText("");
		txtApellidoMaterno.setText("");
		((JTextFieldDateEditor) txtFechaNac.getDateEditor()).setText("");
		cmbHoraCita.setSelectedIndex(-1);
		cmbConsultorio.setSelectedIndex(-1);
		txtDescripcion.setText("");
	}

	private void iniciarDisenio() {
		//cambiar la apariencia del formularios
		this.getContentPane().setBackground(Color.white);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		//cambiar la apariencia de los componentes del formularios
		//this.txtId.setBorder(new EmptyBorder(5, 5, 5, 5));
		//this.txtId.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		this.txtNombre.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		this.txtApellidoPaterno.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		this.txtApellidoMaterno.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		((JTextFieldDateEditor) txtFechaNac.getDateEditor()).setEnabled(false);
//		((JTextFieldDateEditor) txtFechaNac.getDateEditor())
//			.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		//this.cmbHoraCita.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		//this.cmbConsultorio.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		this.txtDescripcion.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(0, 204, 0)));
		//cmbConsultorio.setSelectedIndex(-1);
		//cmbHoraCita.setSelectedIndex(-1);
		try {
			cmbHoraCita.setSelectedIndex(-1);
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                pnlPaciente = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                txtId = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                txtApellidoPaterno = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                txtApellidoMaterno = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                txtFechaNac = new com.toedter.calendar.JDateChooser();
                jLabel5 = new javax.swing.JLabel();
                cmbHoraCita = new javax.swing.JComboBox<>();
                jLabel6 = new javax.swing.JLabel();
                cmbConsultorio = new javax.swing.JComboBox<>();
                jLabel7 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                txtDescripcion = new javax.swing.JTextArea();
                btnLimpiar = new javax.swing.JButton();
                jLabel8 = new javax.swing.JLabel();
                txtNombre = new javax.swing.JTextField();
                jScrollPane2 = new javax.swing.JScrollPane();
                tblCitas = new javax.swing.JTable();
                btnGuardar = new javax.swing.JButton();
                btnEliminar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Agendar Citas");
                setMinimumSize(new java.awt.Dimension(1008, 666));

                pnlPaciente.setBackground(new java.awt.Color(255, 255, 255));
                pnlPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 255, 102), 2, true), "PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("sansserif", 1, 14), new java.awt.Color(0, 204, 0))); // NOI18N

                jLabel1.setText("ID:");

                txtId.setEnabled(false);

                jLabel2.setText("Apellido paterno:");

                jLabel3.setText("Apellido materno:");

                jLabel4.setText("Fecha nac: (MM-DD-AAAA)");

                txtFechaNac.setBackground(new java.awt.Color(255, 255, 255));

                jLabel5.setText("Hora de la cita:");

                cmbHoraCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));
                cmbHoraCita.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
                cmbHoraCita.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                cmbHoraCitaFocusLost(evt);
                        }
                });

                jLabel6.setText("Constultorio disponible:");

                jLabel7.setText("Descripción del motivo:");

                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                txtDescripcion.setColumns(20);
                txtDescripcion.setRows(5);
                jScrollPane1.setViewportView(txtDescripcion);

                btnLimpiar.setText("Limpiar");
                btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLimpiarActionPerformed(evt);
                        }
                });

                jLabel8.setText("Nombre del paciente");

                javax.swing.GroupLayout pnlPacienteLayout = new javax.swing.GroupLayout(pnlPaciente);
                pnlPaciente.setLayout(pnlPacienteLayout);
                pnlPacienteLayout.setHorizontalGroup(
                        pnlPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPacienteLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtApellidoMaterno)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1)
                                        .addComponent(cmbConsultorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbHoraCita, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtApellidoPaterno)
                                        .addGroup(pnlPacienteLayout.createSequentialGroup()
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLimpiar))
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel8)
                                        .addComponent(txtNombre))
                                .addContainerGap(20, Short.MAX_VALUE))
                );
                pnlPacienteLayout.setVerticalGroup(
                        pnlPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPacienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLimpiar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(19, Short.MAX_VALUE))
                );

                tblCitas.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "Nombre", "Apellido P", "Apellido M", "Fecha nac", "Hora cita", "Consultorio", "Descripcion"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tblCitas.setSelectionBackground(new java.awt.Color(153, 255, 153));
                tblCitas.setSelectionForeground(new java.awt.Color(0, 0, 0));
                tblCitas.setShowGrid(false);
                tblCitas.getTableHeader().setReorderingAllowed(false);
                tblCitas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tblCitasMouseClicked(evt);
                        }
                });
                jScrollPane2.setViewportView(tblCitas);
                if (tblCitas.getColumnModel().getColumnCount() > 0) {
                        tblCitas.getColumnModel().getColumn(0).setPreferredWidth(50);
                }

                btnGuardar.setText("Guardar");
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGuardarActionPerformed(evt);
                        }
                });

                btnEliminar.setText("Eliminar");
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(pnlPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnEliminar)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnGuardar))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                                .addGap(23, 23, 23))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(pnlPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnGuardar)
                                                        .addComponent(btnEliminar))))
                                .addGap(23, 23, 23))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
		
		// validar que el ID este vacio para guardar un nuevo paciene
		if (txtId.getText().trim().length() == 0) {
			String nombre = txtNombre.getText();
			String apellidoP = txtApellidoPaterno.getText();
			String apellidoM = txtApellidoMaterno.getText();
			Date fechaActual = txtFechaNac.getDate();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String fecha = df.format(fechaActual);
			String hora = cmbHoraCita.getSelectedItem().toString();
			String consultorio = cmbConsultorio.getSelectedItem().toString();
			String descripcion = txtDescripcion.getText();

			//envar los datos llmando al metodo para guardar un nuevo paciente
			facadeCitas.setPaciente(nombre, apellidoP, apellidoM, fecha, hora, consultorio, descripcion);

			//limpiar();
			//volver a llenar la tabla con el nuevo paciente
			llenarTabla();
		} else { // si el ID no esta vacio va a actualizar los datos actuales del paciente
			
			//pedir confirmacion para actualizar los datos
			int op = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea acutalizar los datos?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
			
			//validar confirmacion por verdadero
			if (op == JOptionPane.YES_OPTION) {
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String apellidoP = txtApellidoPaterno.getText();
				String apellidoM = txtApellidoMaterno.getText();
				Date fechaActual = txtFechaNac.getDate();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String fecha = df.format(fechaActual);
				String hora = cmbHoraCita.getSelectedItem().toString();
				String consultorio = cmbConsultorio.getSelectedItem().toString();
				String descripcion = txtDescripcion.getText();

				//enviar los datos llmaando al metodo para actualizar el paciente actual
				try {
					facadeCitas.updatePaciente(id, nombre, apellidoP, apellidoM,
						fecha, hora, consultorio, descripcion);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this,
						"Error al leer el archivo citas para actualizar datos.txt",
						"Error", JOptionPane.ERROR_MESSAGE);
					//Logger.getLogger(FrmMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
				}

				//volver a llenar la tabla con los datos actualizados
				llenarTabla();
			}
		}
        }//GEN-LAST:event_btnGuardarActionPerformed


        private void tblCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasMouseClicked
		// TODO add your handling code here:
		
		if (estaSeleccionado) {
			int index = cmbConsultorio.getItemCount();
			cmbConsultorio.removeItemAt(index - 1);
		}
		
		//recuperar el ID seleccionado del paciente
		String id = tblCitas.getValueAt(tblCitas.getSelectedRow(), 0).toString();
		
		String linea = "";
		
		//declarar un lector de la clse Buffer
		BufferedReader lector = null;
		try {
			//inicar el lector de Buffer con un lector de archivos
			//de la rua del archivo de texto de las citas
			lector = new BufferedReader(new FileReader(facadeCitas.rutaCitas));
			
			//recorrer cada linea almacenada en el buffer
			while ((linea = lector.readLine()) != null) {
				//dividir la linea de texto separando por una "coma" obteniendo un array
				String[] datos = linea.split(",");
				
				//validar que el ID la linea sea igual al ID del paciente que estamos buscando
				if (datos[0].equals(id)) {
					//llenar los componenetes del formulario con los datos del vector de la linea
					txtId.setText(datos[0]);
					txtNombre.setText(datos[1]);
					txtApellidoPaterno.setText(datos[2]);
					txtApellidoMaterno.setText(datos[3]);
					String fechaCadena = datos[4];
					DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date fechaParse = sdf.parse(fechaCadena);
					((JTextFieldDateEditor) txtFechaNac.getDateEditor()).setDate(fechaParse);
					cmbHoraCita.setSelectedItem(datos[5]);
					cmbConsultorio.addItem(datos[6]);
					cmbConsultorio.setSelectedItem(datos[6]);
					txtDescripcion.setText(datos[7]);
					
					estaSeleccionado = true;
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
				"No se puede leer el archivo de citas.",
				"Advertencia",
				JOptionPane.WARNING_MESSAGE);
		} catch (ParseException ex) {
			Logger.getLogger(FrmCitas.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(this,
				"Error al convertir la fecha.",
				"Error",
				JOptionPane.ERROR_MESSAGE);
		} finally {
			if (lector != null) {
				try {
					lector.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
        }//GEN-LAST:event_tblCitasMouseClicked

        private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
		// validar que hayamos seleccionado un paciente en la tabla
		if (txtId.getText().trim().length() > 0) {
			//pedir confirmacion para eliminar un paciente
			int op = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar este paciente?",
				"Confirmación", JOptionPane.YES_NO_OPTION);
			
			//validar que la confirmacion sea verdadero
			if (op == JOptionPane.YES_OPTION) {
				//llamar al metodo para eliminar el paciente de las citas 
				try {
					facadeCitas.deletePaciente(Integer.parseInt(txtId.getText()));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this,
						"Error al leer el archivo citas.txt para eliminar un paciente",
						"Error", JOptionPane.ERROR_MESSAGE);
					//Logger.getLogger(FrmMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
				}
				//limpiar el texto de los componentes del forulario
				limpiar();
				//volver a llenar la tabla con los datos almacenados
				llenarTabla();
			}
		} else {
			JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningun paciente",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
        }//GEN-LAST:event_btnEliminarActionPerformed

        private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
		// TODO add your handling code here:
		//llamar al metodo para limpiar el texto de los componenetes de formulario
		limpiar();
        }//GEN-LAST:event_btnLimpiarActionPerformed

        private void cmbHoraCitaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbHoraCitaFocusLost
                // TODO add your handling code here:
		try {
			String linea = "";
			//declarar un lector de la clase buffer
			BufferedReader lector = null;
			try {
				//inicializar el lector con un lector de archivos del archivo de texto de las citas
				lector = new BufferedReader(new FileReader(facadeCitas.rutaCitas));
				while ((linea = lector.readLine()) != null) {
					//dividir la linea de texto separando por una "coma" obteniendo un array
					String[] datos = linea.split(",");
					//validar la hora de las que ya estan usadas por otro paciente
					if (cmbHoraCita.getSelectedItem().toString().equals(datos[5])) {
						JOptionPane.showMessageDialog(this,
							"La hora " + cmbHoraCita.getSelectedItem().toString()
							+ " ya ha sido seleccionada por otro paciente. Intente una diferente.",
							"Advertencia",
							JOptionPane.WARNING_MESSAGE);
						try {
							cmbHoraCita.setSelectedIndex(-1);
						} catch (Exception e) {

						}
					}
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error al abrir el archivo de citas",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			} finally {
				//cerrar el lector buffer
				if (lector != null) {
					try {
						lector.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {

		}

        }//GEN-LAST:event_cmbHoraCitaFocusLost

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmCitas().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnEliminar;
        private javax.swing.JButton btnGuardar;
        private javax.swing.JButton btnLimpiar;
        private javax.swing.JComboBox<String> cmbConsultorio;
        private javax.swing.JComboBox<String> cmbHoraCita;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JPanel pnlPaciente;
        private javax.swing.JTable tblCitas;
        private javax.swing.JTextField txtApellidoMaterno;
        private javax.swing.JTextField txtApellidoPaterno;
        private javax.swing.JTextArea txtDescripcion;
        private com.toedter.calendar.JDateChooser txtFechaNac;
        private javax.swing.JTextField txtId;
        private javax.swing.JTextField txtNombre;
        // End of variables declaration//GEN-END:variables
}
