package com.soria.Ejemplo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.soria.Ejemplo.Modelo.Empleado;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        //ejemploRegistro();
        ejemploLecturaEmpleados();
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
}
