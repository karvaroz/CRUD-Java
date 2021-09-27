package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM tblusuarios";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt(1));
                us.setUsername(rs.getString(2));
                us.setNombres(rs.getString(3));
                us.setApellidos(rs.getString(4));
                us.setEmail(rs.getString(5));
                us.setCelular(rs.getString(6));
                datos.add(us);
            }
        } catch (Exception e) {

        }
        return datos;
    }

    public int agregar(Usuario us) {
        String sql = "INSERT INTO tblusuarios (username, nombres, apellidos, email, celular) VALUES (?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getNombres());
            ps.setString(3, us.getApellidos());
            ps.setString(4, us.getEmail());
            ps.setString(5, us.getCelular());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario agregado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el usuario");
        }
        return 1;
    }

    public int actualizar(Usuario us) {
        String sql = "UPDATE tblusuarios SET username=?, nombres=?, apellidos=?, email=?, celular=? WHERE id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getNombres());
            ps.setString(3, us.getApellidos());
            ps.setString(4, us.getEmail());
            ps.setString(5, us.getCelular());
            ps.setInt(6, us.getId());
            ps.executeUpdate(); 
            JOptionPane.showMessageDialog(null, "Usuario editado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo editar el usuario");
        }
        return 1;
    }
    
    public void eliminar(int id){
        String sql = "DELETE FROM tblusuarios WHERE id="+id;
        try {
            con=conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(Exception e){
            
        }
    }
    

}
