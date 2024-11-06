package com.example.demo.controllers;


import com.example.demo.entities.InvoiceItem;
import com.example.demo.services.InvoiceItemsService;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceItemsController {
    @Autowired
    private InvoiceItemsService invoiceItemsService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get items by invoice id" , description = "Get items by invoice id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @GetMapping(value = "/{invoiceId}/items", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<InvoiceItem>> getItemsByInvoiceId(@PathVariable UUID invoiceId) {
        List<InvoiceItem> items = invoiceItemsService.findByInvoiceId(invoiceId);
        return ResponseEntity.ok(items);
    }

}