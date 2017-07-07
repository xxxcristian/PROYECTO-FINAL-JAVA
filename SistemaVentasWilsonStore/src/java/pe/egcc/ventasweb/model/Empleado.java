package pe.egcc.ventasweb.model;


public class Empleado {

  private int idemp;
  private String nombre;
  private String apellido;
  private String email;
  private String telefono;
  private String usuario;
  private String dni;
  private String direccion;



  public Empleado() {
  }

  public int getIdemp() {
    return idemp;
  }

  public void setIdemp(int idemp) {
    this.idemp = idemp;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


  

}
