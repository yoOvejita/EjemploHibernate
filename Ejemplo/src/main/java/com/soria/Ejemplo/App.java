package com.soria.Ejemplo;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.soria.Ejemplo.Modelo.Empleado;
import com.soria.Ejemplo.Modelo.EmpleadoSimple;
import com.soria.Ejemplo.Modelo.EmpleadoVentaJoin;
import com.soria.Ejemplo.Modelo.Venta;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        //ejemploRegistro();
        //ejemploLecturaEmpleados();
        //ejemploLecturaProyeccion();
        //ejemploWhere();
        //ejemploOrderBy();
    	//ejemploOrderByCompuesto();
    	//ejemploOrderByCompuesto2();
    	//ejemploOrderByCompuesto3();
    	//ejemploGroupBy();
    	//ejemploParametros(90);
    	//ejemploUpdate(2, "Anna");
    	//ejemploDelete(92);
    	//ejemploPaginacion();
    	
    	//ejemploRelacion();
    	//ejemploJoin();
    	//ejemploFunciones();
    	//ejemploFuncionesConParametros(5, 77);
    	//ejemploCriteriaQuery();
    	ejemploCriteriaQueryConCondiciones(60);
    }

	private static void ejemploLecturaEmpleados() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		//List empleados = sesion.createQuery("FROM Empleado").list();//HQL
    		//List empleados = sesion.createQuery("FROM Empleado AS E").list();//Alias con AS
    		//List empleados = sesion.createQuery("FROM Empleado E").list();//Alias omitiendo AS
    		String hql = "FROM com.soria.Ejemplo.Modelo.Empleado";
    		Query consulta = sesion.createQuery(hql);
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() );
    			System.out.println( "nombre:" + emp.getNombre() );
    			System.out.println( "apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}

	private static void ejemploRegistro() {
		Empleado emp = new Empleado();
    	emp.setId(92);
    	emp.setNombre("Ricardo");
    	emp.setApellido("Sanchez");
    	
    	Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		//Insertar un nuevo empleado  a la DDBB
    		int numero = (Integer)sesion.save(emp);
    		//Realizamos el commit
    		tx.commit();
    		System.out.println( "Se registró con éxito, id:" + numero );
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemploLecturaProyeccion() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"SELECT new com.soria.Ejemplo.Modelo.EmpleadoSimple(E.id, E.apellido) FROM Empleado E");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			EmpleadoSimple emp = (EmpleadoSimple)iterador.next();
    			System.out.println( "id:" + emp.getCodigo() );
    			System.out.println( "apellido:" + emp.getApellido() );
    			System.out.println( "------------------------------");
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploWhere() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E WHERE E.id < 50");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() );
    			System.out.println( "apellido:" + emp.getApellido() );
    			System.out.println( "------------------------------");
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploOrderBy() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E ORDER BY E.apellido");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() + ", apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploOrderByCompuesto() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E ORDER BY E.apellido DESC, E.nombre ASC, E.id DESC");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() + ",\t nombre:" + emp.getNombre() + ",\t apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploOrderByCompuesto2() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E WHERE id < 50 ORDER BY E.apellido, E.id DESC");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() + ",\t nombre:" + emp.getNombre() + ",\t apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploOrderByCompuesto3() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E WHERE id < 50 ORDER BY E.apellido, E.id DESC");
    		List empleados = consulta.list();
    		
    		for(Iterator iterador = empleados.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() + ",\t nombre:" + emp.getNombre() + ",\t apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploGroupBy() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"SELECT SUM(id) FROM Empleado E GROUP BY E.apellido");
    		List valores = consulta.list();
    		
    		for(Iterator iterador = valores.iterator(); iterador.hasNext();) {
    			long emp = (Long) iterador.next();
    			System.out.println( "sum:" + emp );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploParametros(int valor) {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"FROM Empleado E WHERE E.id < :un_id");
    		consulta.setParameter("un_id", valor);
    		List valores = consulta.list();
    		
    		for(Iterator iterador = valores.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println( "id:" + emp.getId() + ",\t nombre:" + emp.getNombre() + ",\t apellido:" + emp.getApellido() );
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploUpdate(int id, String nombre) {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Empleado emp = sesion.get(Empleado.class, id);
    		emp.setNombre(nombre);
    		sesion.update(emp);
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemploDelete(int id) {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Empleado emp = (Empleado)sesion.get(Empleado.class, id);
    		sesion.delete(emp);
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemploCUD_HQL() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"INSERT INTO Empleado(id, nombre, apellido) SELECT un_id, un_nombre, un_apellido FROM otro_objeto");
    		int respuesta = consulta.executeUpdate();
    		
    		Query consulta2 = sesion.createQuery(
        			"UPDATE Empleado SET apellido = :ap WHERE id = :e_id");
    		consulta2.setParameter("ap", "Rocha");
    		consulta2.setParameter("e_id", 77);
        	int respuesta2 = consulta2.executeUpdate();
        	
        	Query consulta3 = sesion.createQuery(
        			"DELETE FROM Empleado WHERE id = :e_id");
        	consulta3.setParameter("e_id", 90);
        	int respuesta3 = consulta3.executeUpdate();
        		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemplo_funciones_agregacion_HQL() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		Query consulta = sesion.createQuery(
    			"SELECT SUM(id) FROM Empleado");
    		Long resultado1 = (Long)consulta.getResultList().get(0);
    		
    		Query consulta2 = sesion.createQuery(
        		"SELECT AVG(id) FROM Empleado");
    		Double resultado2 = (Double)consulta2.getResultList().get(0);
        	
    		Query consulta3 = sesion.createQuery(
        		"SELECT MAX(id) FROM Empleado");
        	Query consulta4 = sesion.createQuery(
            	"SELECT MIN(id) FROM Empleado");
            BigDecimal resultado34 = (BigDecimal)consulta4.getResultList().get(0);
            	
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploPaginacion() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		String hql = "FROM Empleado";
    		Query query = sesion.createQuery(hql);
    		query.setFirstResult(2);
    		query.setMaxResults(4);
    		List resultado = query.list();
    		System.out.println("Exito");
    		for(Iterator iterador = resultado.iterator(); iterador.hasNext();) {
    			Empleado emp = (Empleado)iterador.next();
    			System.out.println("Empleado " + emp.getApellido() + " id: " + emp.getId());
    		}
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemploRelacion() {
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		String hql = "FROM Empleado WHERE id = 5";
    		Query query = sesion.createQuery(hql);
    		Empleado emp = (Empleado) query.uniqueResult();
    		System.out.println("Exito recuperando al empleado");
    		System.out.println(emp.getNombre());
    		for(Iterator iterador = emp.getVentas().iterator(); iterador.hasNext();) {
    			Venta v = (Venta) iterador.next();
    			System.out.println("Cantidad: " + v.getCantidad() + ", Prod: " + v.getIdprod());
    		}
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	
	private static void ejemploJoin() {
		// SELECT nombre, idprod, cantidad FROM venta v INNER JOIN empleado e ON e.id = v.idemp;
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		String hql = "SELECT new com.soria.Ejemplo.Modelo.EmpleadoVentaJoin(E.nombre, V.idprod, V.cantidad) "
    				+ "FROM Venta V, Empleado E  WHERE V.empleado = E";
    		Query query = sesion.createQuery(hql);
    		List<EmpleadoVentaJoin>  empv= query.getResultList();
    		System.out.println("Exito");
    		for(EmpleadoVentaJoin em : empv) {
    			System.out.println("Nombre: " + em.getNombre() + ", Prod: " + em.getIdprod() + ", cantidad: " + em.getCantidad());
    		}
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
	}
	private static void ejemploFunciones() {
		//Procedimientos almacenados en MySQL
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		//Query query = sesion.createNativeQuery("{CALL prueba()}", Empleado.class);//MySQL
    		Query query = sesion.createNativeQuery("SELECT * FROM prueba()", Empleado.class);
    		List<Empleado> emps = query.getResultList();
    		for(Empleado em : emps) {
    			System.out.println("Nombre: " + em.getNombre() + ", Apellido: " + em.getApellido());
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
		
	}
	private static void ejemploFuncionesConParametros(int min, int max) {
		/*
PostgreSQL:
empresay=# CREATE OR REPLACE FUNCTION listar(menor INTEGER, mayor INTEGER)
empresay-# RETURNS TABLE (id INT, nombre VARCHAR, apellido VARCHAR)
empresay-# AS $$
empresay$# SELECT * FROM empleado WHERE id >= menor AND id <= mayor;
empresay$# $$
empresay-# LANGUAGE SQL;

MySQL:
DELIMITER $$
CREATE PROCEDURE listar(menor INTEGER, mayor INTEGER)
BEGIN
SELECT * FROM empleado WHERE id BETWEEN menor AND mayor;
END $$
DELIMITER;

		 * */
		
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		/*
    		//Con persistence:
    		StoredProcedureQuery spq = sesion.createStoredProcedureQuery("listar", Empleado.class);
    		spq.registerStoredProcedureParameter("menor", Integer.class, ParameterMode.IN);
    		spq.registerStoredProcedureParameter("mayor", Integer.class, ParameterMode.IN);
    		spq.setParameter("menor", min);
    		spq.setParameter("mayor", max);
    		List<Empleado> emps2 = spq.getResultList(); 
    		//...
    		*/
    		
    		//Query query = sesion.createNativeQuery("{CALL listar(?, ?)}", Empleado.class);//MySQL
    		Query query = sesion.createNativeQuery("SELECT * FROM listar(?,?)", Empleado.class);
    		query.setParameter(1, min);
    		query.setParameter(2, max);
    		List<Empleado> emps = query.getResultList();
    		for(Empleado em : emps) {
    			System.out.println("Nombre: " + em.getNombre() + ", Apellido: " + em.getApellido());
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
		
	}
	private static void ejemploCriteriaQuery() {
		//jakarta.persistence
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		CriteriaQuery<Empleado> cq = sesion.getCriteriaBuilder().createQuery(Empleado.class);
    		cq.from(Empleado.class);
    		List<Empleado> emps = sesion.createQuery(cq).getResultList();
    		for(Empleado em : emps) {
    			System.out.println("Nombre: " + em.getNombre() + ", Apellido: " + em.getApellido());
    		}
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
		
	}
	private static void ejemploCriteriaQueryConCondiciones(int val) {
		//jakarta.persistence
		Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		
    		CriteriaBuilder builder = sesion.getCriteriaBuilder();
    		CriteriaQuery<Empleado> consulta = builder.createQuery(Empleado.class);
    		Root<Empleado> raiz = consulta.from(Empleado.class);
    		consulta.select(raiz).where(builder.ge((Expression)raiz.get("id"), val));
    		
    		tx.commit();
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
		
	}
}
