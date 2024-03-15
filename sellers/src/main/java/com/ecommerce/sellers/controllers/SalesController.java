package com.ecommerce.sellers.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.SalesDTO;
import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.models.Publication;
import com.ecommerce.sellers.models.Sales;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.PublicationRepository;
import com.ecommerce.sellers.repositories.SalesRepository;
import com.ecommerce.sellers.repositories.SellerRepository;
import com.ecommerce.sellers.repositories.StoreRepository;

@RepositoryRestController
@RequestMapping("/sales")
public class SalesController  {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SellerRepository sellerRepository;


    



    


}
