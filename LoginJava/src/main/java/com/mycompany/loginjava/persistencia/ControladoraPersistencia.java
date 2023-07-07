
package com.mycompany.loginjava.persistencia;

import com.mycompany.loginjava.logica.Rol;
import com.mycompany.loginjava.logica.Usuario;
import com.mycompany.loginjava.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();

    public List<Usuario> traerUsuarios() {
        //le pedimos que nos seleccione los usuarios y lo guarde en una lista (List<Usuario> seria en este caso) y este le va a responder a la logica
        List <Usuario> listaUsuario = usuJpa.findUsuarioEntities(); //esto es igual a SELECT * FROM USUARIOS
        return listaUsuario;  
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }

    public void crearUsuario(Usuario usu) {
        //le pedimos a la bd que cree el usuario que recibimos de la interfaz
        usuJpa.create(usu);
    }

    public void borrarUsuario(int id_usuario) {
        try {
            usuJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
