package br.com.nozinho.ejb.service.impl;

import javax.ejb.Stateless;

import br.com.nozinho.ejb.service.DemandaStatusService;
import br.com.nozinho.model.DemandaStatus;

@Stateless
public class DemandaStatusServiceImpl extends GenericServiceImpl<DemandaStatus, Long> implements DemandaStatusService{

}
