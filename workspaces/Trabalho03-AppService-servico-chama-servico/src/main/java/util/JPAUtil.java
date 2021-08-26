package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import excecao.InfraestruturaException;

public class JPAUtil {
	private static EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	private static final ThreadLocal<EntityTransaction> threadTransaction = new ThreadLocal<EntityTransaction>();
	//Criar variavel thread local para contador
	private static ThreadLocal<Integer> contador = new ThreadLocal<>();

	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicio");
			//Inicializar contador em 0
			if(contador.get()==null) {
				contador.set(0);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(">>>>>>>>>> Mensagem de erro: " + e.getMessage());
			throw e;
		}
	}

	public static void beginTransaction() { // System.out.println("Vai criar transacao");
		//Para cada transação iniciada, contador será Incrementado
		int valor = contador.get();
		valor++;
		contador.set(valor);
		EntityTransaction tx = threadTransaction.get();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entrou em beginTransaction()");
		System.out.println(">>>Valor do Contador:" + contador.get());
		try {
			if (tx == null) {
				tx = getEntityManager().getTransaction();
				tx.begin();
				threadTransaction.set(tx);
				// System.out.println("Criou transacao");
			} else { // System.out.println("Nao criou transacao");
			}
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		}
	}

	public static EntityManager getEntityManager() { // System.out.println("Abriu ou recuperou sessão");

		EntityManager s = threadEntityManager.get();
		// Abre uma nova Sessão, se a thread ainda não possui uma.
		try {
			if (s == null) {
				s = emf.createEntityManager();
				threadEntityManager.set(s);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Criou o entity manager");
			}
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		}
		return s;
	}

	public static void commitTransaction() {
		EntityTransaction tx = threadTransaction.get();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entrou em commitTransaction");
		int valor = contador.get(); //Obter Valor do Contador
		System.out.println(">>>Valor do Contador: "+ contador.get());
		//A transação só será comitada se contador for 1
		if(valor==1) {
			try {
				if (tx != null && tx.isActive()) {
					tx.commit();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Comitou transacao");
					//Ao terminar commit zerar novamente o contador
					contador.set(0);
				}
				threadTransaction.set(null);
			} catch (RuntimeException ex) {
				try {
					rollbackTransaction();
				} catch (RuntimeException e) {
				}

				throw new InfraestruturaException(ex);
			}
			
		}
		else {
			valor --;
			contador.set(valor);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONTADOR NÃO VALE 1, NAO COMITAR");
		}
	}

	public static void rollbackTransaction() {
		System.out.println("Vai efetuar rollback de transacao");

		EntityTransaction tx = threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		} finally {
			closeEntityManager();
		}
	}

	public static void closeEntityManager() { // System.out.println("Vai fechar sessão");
		//O entity Manager só poderá ser fechado quando não houver transações abertas, para isso checar se contador está zerado
		if(contador.get()==0) {
			try {
				EntityManager s = threadEntityManager.get();
				threadEntityManager.set(null);
				if (s != null && s.isOpen()) {
					s.close();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Fechou o entity manager");
				}

				EntityTransaction tx = threadTransaction.get();
				if (tx != null && tx.isActive()) {
					rollbackTransaction();
					throw new RuntimeException("EntityManager sendo fechado " + "com transação ativa.");
				}
			} catch (RuntimeException ex) {
				throw new InfraestruturaException(ex);
			}
		}else {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Ainda há transações, não Fechou o entity manager");
		}
	}
}
