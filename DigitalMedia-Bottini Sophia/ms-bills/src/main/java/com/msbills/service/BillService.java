package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.models.Dto.BillUser;
import com.msbills.models.Dto.User;
import com.msbills.repositories.BillRepository;
import com.msbills.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

  private final BillRepository repository;

  private final UserRepository userRepository;

  public List<Bill> getAllBill() {
    return repository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public Bill findByCustomer(String customer) {
    return repository.findByCustomerBill(customer).orElse(null);
  }
  public BillUser findByUsername(String username) {
    Bill bill = repository.findByCustomerBill(username).orElse(null);
    if (bill != null) {
      User user = userRepository.findByUsername(username);
      BillUser billUser = new BillUser(bill.getIdBill(), bill.getBillingDate(), bill.getTotalPrice(), user);
      return billUser;
    }
    return null;
  }
}
