package com.jockers.shoping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jockers.shoping.entity.Invoice;
import com.jockers.shoping.service.Impl.InvoiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices(){
        return new ResponseEntity<>(invoiceService.findInvoiceAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id){
        log.info("Fetching Invoice with {}", id);
        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            log.error("Invoice with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(invoice, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Invoice> saveInvoice(@Valid @RequestBody Invoice invoice, BindingResult result){
        log.info("Creating invoice {}", invoice);
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));

        return new ResponseEntity<>(invoiceService.createInvoice(invoice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){
        log.info("Updating Invoice {} ", invoice);
        invoice.setId(id);

        Invoice invoiceDB = invoiceService.updateInvoice(invoice);
        if (invoiceDB == null){
            log.error("Unable to update Invoice with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(invoiceDB, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id, @RequestBody Invoice invoice){
        log.info("Deleting Invoice {}", invoice);
        invoice.setId(id);

        Invoice invoiceDB = invoiceService.deleteInvoice(invoice);
        if (invoiceDB == null){
            log.error("Unable to delete Invoice with id {} not foud", id);
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(invoiceDB, HttpStatus.OK);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
