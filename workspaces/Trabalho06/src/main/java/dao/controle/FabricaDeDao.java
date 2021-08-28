package dao.controle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.impl.ClienteDAOImpl;
import dao.impl.FuncionarioDAOImpl;
import dao.impl.ProdutoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import dao.impl.TarefaDAOImpl;
import net.sf.cglib.proxy.Enhancer;

// A anotação @Configuration indica que esta classe possui um ou mais métodos anotados com @Bean.
@Configuration
public class FabricaDeDao {
	
	// @Bean diz ao Spring:
	// Aqui está uma instância da classe ProdutoDAOImpl, por favor, 
	// guarde esta instância e me devolva quando eu a pedir.

	// @Autowired em ProdutoAppServiceImpl diz:
	// Por favor, me retorne uma instância do tipo ProdutoDAO, isto é,  
	// aquela que foi criada mais cedo com a anotação @Bean.
	@Bean
	public static ProdutoDAOImpl getProdutoDao() throws Exception {
		return getDao(dao.impl.ProdutoDAOImpl.class);
	}

	@Bean
	public static ProjetoDAOImpl getProjetoDao() throws Exception {
		return getDao(dao.impl.ProjetoDAOImpl.class);
	}
	
	@Bean
	public static ClienteDAOImpl getClienteDao() throws Exception {
		return getDao(dao.impl.ClienteDAOImpl.class);
	}
	
	@Bean
	public static FuncionarioDAOImpl getFuncionarioDao() throws Exception {
		return getDao(dao.impl.FuncionarioDAOImpl.class);
	}
	
	@Bean
	public static TarefaDAOImpl getTarefaDao() throws Exception {
		return getDao(dao.impl.TarefaDAOImpl.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> classeDoDao) throws Exception {
		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());
	}
}