package br.com.nozinho.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.nozinho.dao.DashboardDAO;

@Stateless
public class DashboardDAOImpl implements DashboardDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Long getDemandasMenor15Dias() {
		Query query = em.createNativeQuery("select count(*) from demanda d inner join oficio o on d.idoficio = o.idoficio where situacao_atendimento in (0,1) and datediff(curdate(),o.data_oficio) < 15 and data_recebimento is null;");
		BigInteger number = (BigInteger) query.getSingleResult();
		return number.longValue();
	}

	@Override
	public Long getDemandasEntre15E30Dias() {
		Query query = em.createNativeQuery("select count(*) from demanda d inner join oficio o on d.idoficio = o.idoficio where situacao_atendimento in (0,1) and datediff(curdate(),o.data_oficio) >= 15 and datediff(curdate(),o.data_oficio) < 30 and data_recebimento is null;");
		BigInteger number = (BigInteger) query.getSingleResult();
		return number.longValue();
	}

	@Override
	public Long getDemandasEntre30E60Dias(){
		Query query = em.createNativeQuery("select count(*) from demanda d inner join oficio o on d.idoficio = o.idoficio where situacao_atendimento in (0,1) and datediff(curdate(),o.data_oficio) >= 30 and datediff(curdate(),o.data_oficio) < 60 and data_recebimento is null;");
		BigInteger number = (BigInteger) query.getSingleResult();
		return number.longValue();
	}
	
	@Override
	public Long getDemandasMais60Dias(){
		Query query = em.createNativeQuery("select count(*) from demanda d inner join oficio o on d.idoficio = o.idoficio where situacao_atendimento in (0,1) and datediff(curdate(),o.data_oficio) >= 60 and data_recebimento is null;");
		BigInteger number = (BigInteger) query.getSingleResult();
		return number.longValue();
	}

	@Override
	public Long getTotalDemandasPrazo(){
		Query query = em.createNativeQuery("select count(*) from demanda d inner join oficio o on d.idoficio = o.idoficio where situacao_atendimento in (0,1) and data_recebimento is null;");
		BigInteger number = (BigInteger) query.getSingleResult();
		return number.longValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNumDemandaMesCurrentYear(){
		Query query = em.createNativeQuery("select count(iddemanda) as num_demandas, MONTH(data) as mes FROM demanda WHERE YEAR(data) = YEAR(curdate()) GROUP BY mes;");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNumDemandasTipo(){
		Query query = em.createNativeQuery("select count(iddemanda) as num_demandas, nome_tipo_demanda FROM demanda d inner join tipo_demanda td on d.idtipo_demanda = td.idtipo_demanda WHERE situacao_atendimento IN (0,1) GROUP BY nome_tipo_demanda;");
		return query.getResultList();
	}
}
