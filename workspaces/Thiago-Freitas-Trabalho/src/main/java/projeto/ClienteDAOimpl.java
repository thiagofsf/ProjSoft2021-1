package projeto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class ClienteDAOimpl implements ClienteDAO
{	
	public long inclui(Cliente umCliente) {	
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
			em.persist(umCliente);
			tx.commit();
			
			return umCliente.getId();
			
		} 
		catch(RuntimeException e){
			if (tx != null){	
				try{
					tx.rollback();
				}
				catch(RuntimeException he){ }
			}
			throw e;
		}
		finally{	
			em.close();
		}
	}

	public void altera(Cliente umCliente) throws ClienteNaoEncontradoException{
		
		EntityManager em = null;
		EntityTransaction tx = null;
		Cliente cliente = null;
		
		try{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
			cliente = em.find(Cliente.class, umCliente.getId(), LockModeType.PESSIMISTIC_WRITE);
			
			if(cliente == null){
				tx.rollback();
				throw new ClienteNaoEncontradoException("Cliente Não Encontrado");
			}
			em.merge(umCliente);
			tx.commit();
		} 
		catch(OptimisticLockException e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw new EstadoDeObjetoObsoletoException();
		}
		catch(RuntimeException e){ 
			if (tx != null){   
				try{
					tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally{
			em.close();
		}
	}

	public void exclui(long numero) throws ClienteNaoEncontradoException {
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();

			Cliente cliente = em.find(Cliente.class, new Long(numero));
			
			if(cliente == null){
				tx.rollback();
				throw new ClienteNaoEncontradoException("Cliente não encontrado");
			}
			em.remove(cliente);
			tx.commit();
		} 
		catch(RuntimeException e){   
			if (tx != null){   
				try{
					tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally{
			em.close();
		}
	}

	public Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException{
		
		EntityManager em = null;
		
		try{	
			
			em = FabricaDeEntityManager.criarSessao();
			Cliente umCliente = em.find(Cliente.class, numero);
			
			if(umCliente == null){
				throw new ClienteNaoEncontradoException("Cliente não encontrado");
			}
			return umCliente;
		} 
		finally{
			em.close();
		}
	}

	public List<Cliente> recuperaClientes(){
		
		EntityManager em = null;
		
		try{
			
			em = FabricaDeEntityManager.criarSessao();
			
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = em.createQuery("select c from Cliente c order by c.id").getResultList();

			// Retorna um List vazio caso a tabela correspondente esteja vazia.
			return clientes;
			
		} 
		finally{  
			em.close();
		}
	}
}
