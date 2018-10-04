package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

/**
 * DealerServiceImpl
 * @author Amira Taliya
 *
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(long id){
		return dealerDb.findById(id);
	}
	
	@Override
	public List<DealerModel> getAllDealer(){
		return dealerDb.findAll();
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public void deleteDealerById(Long dealerId) {
		dealerDb.delete(this.getDealerDetailById(dealerId).get());
	}

	@Override
	public void updateDealer(Long dealerId, Optional<DealerModel> newDealer) {
		DealerModel dealerUpdated = dealerDb.getOne(dealerId);
		dealerUpdated.setAlamat(newDealer.get().getAlamat());
		dealerUpdated.setNoTelp(newDealer.get().getNoTelp());
		dealerDb.save(dealerUpdated);
	}
}
