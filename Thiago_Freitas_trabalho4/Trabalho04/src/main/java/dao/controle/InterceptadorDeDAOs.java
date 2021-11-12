package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import anotacao.PersistenceContext;
import excecao.InfraestruturaException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAOs implements MethodInterceptor {
	public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoDoProxy) throws Throwable{
		try {
			System.out.println("Interceptador de DAOs foi acionado");
			Field[] campos = metodo.getDeclaringClass().getDeclaredFields();
			for(Field campo : campos) {
				if(campo.isAnnotationPresent(PersistenceContext.class)) {
					campo.setAccessible(true);
					campo.set(objeto, JPAUtil.getEntityManager());
					Object objresp = metodoDoProxy.invokeSuper(objeto, args);
					return objresp;
				}
			}
			return null;
		}
		catch(RuntimeException e){
			throw new InfraestruturaException(e);
		}
	}
}
