package com.springbootcacheable.springbootcacheable.resource;

import com.springbootcacheable.springbootcacheable.entity.PaymentEntity;
import com.springbootcacheable.springbootcacheable.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentResource {

    @Autowired
    public PaymentService paymentService;

    @PostMapping(value = "/save")
    public ResponseEntity<PaymentEntity> save(@RequestBody PaymentEntity paymentDetails) {
        log.info("PaymentResource save() invoked {} ", paymentDetails.getPaymentId());
        try {
            PaymentEntity save = paymentService.save(paymentDetails);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<PaymentEntity> update(@RequestBody PaymentEntity paymentDetails) {
        log.info("PaymentResource update() invoked {} ", paymentDetails.getPaymentId());

        try {
            PaymentEntity save = paymentService.update(paymentDetails);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentEntity> findByPaymentId(@PathVariable("paymentId") String id) {
        log.info("PaymentResource findByPaymentId() invoked {} ", id);
        PaymentEntity paymentById = null;
        try {
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentService.findByPaymentId(id);
            }
            return new ResponseEntity<>(paymentById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentEntity>> findAll() {
        log.info("PaymentResource findAll() invoked {} ");
        List<PaymentEntity> paymentById = new ArrayList<>();
        try {
            paymentById = paymentService.findAll();
            return new ResponseEntity<>(paymentById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentEntity>> findByTransactionId(@PathVariable("transactionId") String id) {
        log.info("PaymentResource findByTransactionId() invoked {} ", id);
        List<PaymentEntity> paymentById = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentService.findByTransactionId(id);
            }
            if (paymentById.size() > 0) {
                return new ResponseEntity<>(paymentById, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/auth/{authStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentEntity>> fi(@PathVariable("authStatus") String id) {
        log.info("PaymentResource findByAuthStatus() invoked {} ", id);
        List<PaymentEntity> paymentById = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentService.findByAuthStatus(id);
            }
            if (paymentById.size() > 0) {
                return new ResponseEntity<>(paymentById, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
