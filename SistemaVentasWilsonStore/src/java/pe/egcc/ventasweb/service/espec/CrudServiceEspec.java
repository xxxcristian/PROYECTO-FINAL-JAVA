package pe.egcc.ventasweb.service.espec;

import java.util.List;


public interface CrudServiceEspec<T> {
  
  /**
   * Crea una fila en la base de datos.
   * @param bean 
   */
  void crear(T bean);
  
  /**
   * Modifica los datos de un fila.
   * @param bean 
   */
  void modificar(T bean);
  
  /**
   * 
   * @param id 
   */
  void eliminar(int id);
  
  /**
   * Leer un objeto en base al id.
   * 
   * @param id
   * @return 
   */
  T leer(int id);
  
  /**
   * Leer todos los datos de la tabla.
   * 
   * @return 
   */
  List<T> leer();
  
  /**
   * Leer datos en base al apellido y nombre.
   * 
   * @param bean
   * @return 
   */
  List<T> leer(T bean);
  
    List<T> leerTodos();
  
}
