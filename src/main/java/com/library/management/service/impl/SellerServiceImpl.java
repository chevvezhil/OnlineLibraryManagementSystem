package com.library.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.domain.Book;
import com.library.management.domain.Seller;
import com.library.management.repository.BooksRepository;
import com.library.management.repository.SellerRepository;
import com.library.management.service.SellerService;
import com.library.management.utils.VerificationStatus;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private BooksRepository booksRepository;

	
	@Override
	public List<Seller> getAllSellers() {
		System.out.println("Seller Exits for Admin page");
		var response = sellerRepository.findAll();
		return response;
	}
	
	@Override
	public void updateVerificationStatus(Long sellerId, String verifiedBy) {
		var updateRecord = sellerRepository.getReferenceById(sellerId);
		updateRecord.setVerificationStatus(VerificationStatus.VERIFIED);
		updateRecord.setVerifiedBy(verifiedBy);
		sellerRepository.save(updateRecord);
	}
	
	@Override
	public void removeSeller(Long sellerId) {
		//Delete Books uploaded by Seller and then delete the seller
		Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
		if (sellerOptional.isPresent()) {
            Seller seller = sellerOptional.get();
            String sellerName = seller.getSellerName();
            
            if (sellerName != null) {
                List<Book> books = booksRepository.findBySeller(sellerName);
                for (Book book : books) {
                	booksRepository.delete(book);
                }
            }
		}
		sellerRepository.deleteById(sellerId);
	}
	
	@Override
	public void updateSeller(Long sellerId, VerificationStatus status, String verifiedBy, Boolean addedByAdmin) {
		var updateRecord = sellerRepository.getReferenceById(sellerId);
		updateRecord.setVerificationStatus(status);
		updateRecord.setVerifiedBy(verifiedBy);
		updateRecord.setAddedByAdmin(addedByAdmin);
		sellerRepository.save(updateRecord);
	}
	
	@Override
	 public Seller getSellerByName(String sellerName) {
	        return sellerRepository.findBySellerName(sellerName);
	    }
}
