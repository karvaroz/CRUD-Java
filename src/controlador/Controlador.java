package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Vista;

public class Controlador implements ActionListener {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario us = new Usuario();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
//        listar(vista.tabla);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);

        }
        if (ae.getSource() == vista.btnGuardar) {
            agregar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if (ae.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar la fila del usuario que desea editar");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String username = (String) vista.tabla.getValueAt(fila, 1);
                String nombres = (String) vista.tabla.getValueAt(fila, 2);
                String apellidos = (String) vista.tabla.getValueAt(fila, 3);
                String email = (String) vista.tabla.getValueAt(fila, 4);
                String celular = (String) vista.tabla.getValueAt(fila, 5);
                vista.txtId.setText(""+id);
                vista.txtUsername.setText(username);
                vista.txtNombres.setText(nombres);
                vista.txtApellidos.setText(apellidos);
                vista.txtEmail.setText(email);
                vista.txtCelular.setText(celular);

            }
        }
        if (ae.getSource() == vista.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if(ae.getSource()==vista.btnEliminar){
            int fila=vista.tabla.getSelectedRow();
            
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar la fila del usuario que desea eliminar");
            } else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(vista, "Usuario Eliminado");
            }
            limpiarTabla();
            listar(vista.tabla);            
            

        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void actualizar() {
        int id = Integer.parseInt(vista.txtId.getText());
        String username = vista.txtUsername.getText();
        String nombres = vista.txtNombres.getText();
        String apellidos = vista.txtApellidos.getText();
        String email = vista.txtEmail.getText();
        String celular = vista.txtCelular.getText();
        us.setId(id);
        us.setUsername(username);
        us.setNombres(nombres);
        us.setApellidos(apellidos);
        us.setEmail(email);
        us.setCelular(celular);
        dao.actualizar(us);
    }

    public void agregar() {
        String username = vista.txtUsername.getText();
        String nombres = vista.txtNombres.getText();
        String apellidos = vista.txtApellidos.getText();
        String email = vista.txtEmail.getText();
        String celular = vista.txtCelular.getText();
        us.setUsername(username);
        us.setNombres(nombres);
        us.setApellidos(apellidos);
        us.setEmail(email);
        us.setCelular(celular);
        dao.agregar(us);

    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Usuario> lista = dao.listar();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getUsername();
            object[2] = lista.get(i).getNombres();
            object[3] = lista.get(i).getApellidos();
            object[4] = lista.get(i).getEmail();
            object[5] = lista.get(i).getCelular();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);

    }

}
