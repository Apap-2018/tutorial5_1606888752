package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial4.model.DealerModel;

/**
 * DealerService
 * @author Amira Taliya
 *
 */
public interface DealerService {
	Optional<DealerModel> getDealerDetailById(long id);
	
	List<DealerModel> getAllDealer();
	
	void addDealer(DealerModel dealer);
	void updateDealer(Long dealerId, Optional<DealerModel> newDealer);
	void deleteDealerById(Long dealerId);
}
