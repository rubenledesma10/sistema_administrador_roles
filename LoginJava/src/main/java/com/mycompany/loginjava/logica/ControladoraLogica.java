package com.mycompany.loginjava.logica;

import com.mycompany.loginjava.persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraLogica {

    ControladoraPersistencia controlPersis;

    public ControladoraLogica() {
        //esto va asi asi la persitencia actua cuando llamo a la logica
        controlPersis = new ControladoraPersistencia();
    }

    //recibimos el usuario y la contraseña y hace este proceso
    public Usuario validarUsuario(String usuario, String contrasenia) {
        //String mensaje = "";
        Usuario usr=null;
        //la lista que recibimos de la controladora de la persistencia la guardamos en esta lista
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        //recorremos la lista
        for (Usuario usu : listaUsuarios) {
            if (usu.getUsuario().equals(usuario)) {
                if (usu.getContrasenia().equals(contrasenia)) {
                    //mensaje = "Usuario y contraseña correcta. Bienvenido";
                    usr = usu;
                    //va a retornar un mensaje que va a ser recibido en el igu
                    //con este return tambien se corta automaticamente la ejecucion de lo que estoy haciendo dentro de la funcion
                    return usr;
                } else {
                    //mensaje = "Contraseña incorrecta";
                    usr = null;
                    //va a retornar un mensaje que va a ser recibido en el igu
                    //con este return tambien se corta automaticamente la ejecucion de lo que estoy haciendo dentro de la funcion
                    return usr;
                }
            } else {
                //mensaje = "Usuario incorrecto";
                usr = null;
                //cuando no encuentra ni el usuario ni la contraseña retomamos solamente este mensaje, por eso dejamos el return ese al final y no va aca
                //return usr; -> aca no tiene que estar este return porque si no encuentra el usuario y queire seguir buscando ya lo va a toamr como esta mal, porque dejamos un return al final
            }
            
        }
        
        return usr;

    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
        
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    public void crearUsuario(String usuario, String contrasenia, String rolRecibido) {
        //creamos una instancia usuario
        Usuario usu = new Usuario ();
        //traemos los datos de la interfaz grafica
        usu.setUsuario(usuario);
        usu.setContrasenia(contrasenia);
        
        //como el rol es un objeto tenemos que buscar el rol para asignarlo
        Rol rolEncontrado = new Rol();
        //creamos el metodo aca mismo
        rolEncontrado= this.traerRol(rolRecibido);
        //cuando encuentro el rol
        if(rolEncontrado!=null){
            usu.setUnRol(rolEncontrado);
        }
        
        //obtenemos el ultimo id y le sumamos uno (esto es porque ya estamso trabajando con una bd que trae datos ya insertados y tenemos que agregar el id manualemnte)
        int id = this.buscarUltimaId();
        usu.setId(id+1);
        //guardamos el rol en la bd 
        controlPersis.crearUsuario(usu);
        
    }

    private Rol traerRol(String rolRecibido) {
        //le pedimos a la persistencia que traiga todos lso roles y los que encuentre los va a devolver
        List<Rol> listaRoles = controlPersis.traerRoles();
        for (Rol rol:listaRoles){
            if(rol.getNombreRol().equals(rolRecibido)){
                //que me devuelva el rol que encontro
                return rol;
            }
        }
        //en caso de que no encuentre nada
        return null;
    }

    private int buscarUltimaId() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        //para traer el ultimo usuario
        Usuario usu = listaUsuarios.get(listaUsuarios.size()-1);
        //returno el ultimo usuario
        return usu.getId();
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }

    public Usuario traerUsuario(int id_usuario) {
        
        return controlPersis.traerUsuario(id_usuario);

    }

    public void editarUsuario(Usuario usu, String usuario, String contrasenia, String rolRecibido) {
       //seteamos lso valoers que traemos de la interfaz grafica, la id no se toca en este caso porque estoy editando
        usu.setUsuario(usuario);
        usu.setContrasenia(contrasenia);
        //como el rol es un objeto tenemos que buscar el rol para asignarlo
        Rol rolEncontrado = new Rol();
        //creamos el metodo aca mismo
        rolEncontrado= this.traerRol(rolRecibido);
        //cuando encuentro el rol
        if(rolEncontrado!=null){
            usu.setUnRol(rolEncontrado);
        }
        controlPersis.editarUsuario(usu);
    }

}
