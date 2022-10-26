package com.soria.Ejemplo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.soria.Ejemplo.Modelo.Empleado;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Empleado emp = new Empleado();
    	emp.setId(77);
    	emp.setNombre("Samanta");
    	emp.setApellido("Lima");
    	
    	Transaction tx = null;
    	Session sesion = HibernateUtil.getSessionfactory().openSession();
    	try {
    		//Iniciar la sesión hibernate
    		tx = sesion.beginTransaction();
    		//Insertar un nuevo empleado  a la DDBB
    		sesion.save(emp);
    		//Realizamos el commit
    		tx.commit();
    		System.out.println( "Se registró con éxito." );
    	}catch(HibernateException he) {
    		if(tx!=null)
    			tx.rollback();
    		he.printStackTrace();
    	}finally {
    		sesion.close();
    	}
        
    }
}
