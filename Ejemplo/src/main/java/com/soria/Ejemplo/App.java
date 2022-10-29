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
    	ejemploDelete(92);
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
    			long emp = (long)iterador.next();
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
}
