package dao.controle;

import java.util.Set;

import org.reflections.Reflections;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
	
	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) {
		
		Reflections reflections = new Reflections("dao.impl");

		Set<Class<? extends T>> classes = reflections.getSubTypesOf(tipo);

		if (classes.size() > 1)
			throw new RuntimeException("Somente uma classe pode implementar " + tipo.getName());

		Class<?> classe = classes.iterator().next();
		
		T proxy = (T)Enhancer.create(classe, new InterceptadorDeDAOs());
		
		return proxy;
	}
}
