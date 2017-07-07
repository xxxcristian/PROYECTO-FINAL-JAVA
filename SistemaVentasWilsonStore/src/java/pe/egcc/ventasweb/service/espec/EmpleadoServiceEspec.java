package pe.egcc.ventasweb.service.espec;

import pe.egcc.ventasweb.model.Empleado;


public interface EmpleadoServiceEspec 
  extends CrudServiceEspec<Empleado>{
  
  Empleado validar(String usuario, String clave);
  
  int getEstado(int idemp, int idrol);
  
  void cambiarPass(String claveact, String clavenew);
  
}
