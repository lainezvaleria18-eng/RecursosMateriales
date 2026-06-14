package esfe.presentacion;

import esfe.dominio.Recursos;
import esfe.persistencia.RecursosDAO;

import javax.swing.*;
import java.math.BigDecimal;

public class RegistrarEquipoForm extends JFrame {
    private boolean editar = false;

    private int filaEditar = -1;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextArea textArea1;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panelPrincipal;

    public RegistrarEquipoForm() {


        setTitle("Registrar Equipo");
        setContentPane(panelPrincipal);

        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        // CATEGORÍA

        comboBox1.addItem("Computadora");
        comboBox1.addItem("Laptop");
        comboBox1.addItem("Impresora");
        comboBox1.addItem("Proyector");
        comboBox1.addItem("Monitor");
        comboBox1.addItem("Servidor");

// UBICACIÓN

        comboBox2.addItem("Laboratorio 1");
        comboBox2.addItem("Laboratorio 2");
        comboBox2.addItem("Laboratorio 3");
        comboBox2.addItem("Laboratorio 4");
        comboBox2.addItem("Oficina");
        comboBox2.addItem("Bodega");
        comboBox2.addItem("Aula 1");
        comboBox2.addItem("Aula 2");
        comboBox2.addItem("Aula 3");
        comboBox2.addItem("Aula 4");
        comboBox2.addItem("Aula 5");

// ESTADO

        comboBox3.removeAllItems();

        comboBox3.addItem("Disponible");
        comboBox3.addItem("Prestado");
        comboBox3.addItem("Agotado");
        comboBox3.addItem("Mantenimiento");

// MODELO

        comboBox4.addItem("Dell");
        comboBox4.addItem("HP");
        comboBox4.addItem("Lenovo");
        comboBox4.addItem("Epson");
        comboBox4.addItem("Canon");
        comboBox4.addItem("Acer");

        cancelarButton.addActionListener(e -> dispose());

        guardarButton.addActionListener(e -> {

            String codigo = textField1.getText();
            String nombre = textField2.getText();

            String ubicacion =
                    comboBox2.getSelectedItem().toString();

            String estado =
                    comboBox3.getSelectedItem().toString();

            Recursos recurso = new Recursos();

            recurso.setCodigoRecurso(textField1.getText());

            recurso.setNombreRecurso(textField2.getText());

            recurso.setMarca(
                    comboBox1.getSelectedItem().toString()
            );

            recurso.setModelo(
                    comboBox4.getSelectedItem().toString()
            );

            recurso.setNumeroSerie(
                    textField3.getText()
            );

            recurso.setUbicacion(
                    comboBox2.getSelectedItem().toString()
            );

            recurso.setUnidadMedida("Unidad");

            recurso.setStock(1);

            recurso.setPrecio(
                    new BigDecimal("0.00")
            );

            recurso.setDescripcion(
                    textArea1.getText()
            );

            recurso.setIdCategoria(1);

            recurso.setIdTipoRecurso(1);

            String estadoSeleccionado =
                    comboBox3.getSelectedItem().toString();

            switch (estadoSeleccionado) {

                case "Disponible":
                    recurso.setIdEstadoRecurso(1);
                    break;

                case "Prestado":
                    recurso.setIdEstadoRecurso(2);
                    break;

                case "Agotado":
                    recurso.setIdEstadoRecurso(3);
                    break;

                case "Mantenimiento":
                    recurso.setIdEstadoRecurso(4);
                    break;

                default:
                    recurso.setIdEstadoRecurso(1);
            }

            RecursosDAO dao =
                    new RecursosDAO();

            boolean guardado;

            if (editar) {

                guardado = dao.actualizar(recurso);

            } else {

                guardado = dao.guardar(recurso);

            }

            if(editar){

                MovimientoInventarioForm.modelo.setValueAt(
                        codigo,
                        filaEditar,
                        0
                );

                MovimientoInventarioForm.modelo.setValueAt(
                        nombre,
                        filaEditar,
                        1
                );

                MovimientoInventarioForm.modelo.setValueAt(
                        estado,
                        filaEditar,
                        2
                );

                MovimientoInventarioForm.modelo.setValueAt(
                        ubicacion,
                        filaEditar,
                        3
                );

            }else{

                MovimientoInventarioForm.modelo.addRow(
                        new Object[]{
                                codigo,
                                nombre,
                                estado,
                                ubicacion,
                                "Detalles | Editar | Eliminar"
                        }
                );

            }

            if(guardado){

                JOptionPane.showMessageDialog(
                        this,
                        "Equipo guardado en la base de datos."
                );

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Error al guardar."
                );

            }

            dispose();

        });
    }
    public void cargarDatosEditar(
            String codigo,
            String nombre,
            String estado,
            String ubicacion,
            int fila){

        editar = true;

        filaEditar = fila;

        setTitle("Editar Equipo");

        textField1.setText(codigo);
        textField1.setEditable(false);

        textField2.setText(nombre);

        comboBox2.setSelectedItem(ubicacion);

        comboBox3.setSelectedItem(estado);

    }
}
