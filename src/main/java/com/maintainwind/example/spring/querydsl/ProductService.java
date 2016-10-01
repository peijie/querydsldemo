package com.maintainwind.example.spring.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by peijie on 10/1/2016.
 */


@Service
public class ProductService {

    @Autowired
    private EntityManager entityManager;

    public List<ProductDetail> findProducts() {
        QProductDetail productDetail = QProductDetail.productDetail;

        List<ProductDetail> products = new JPAQuery<ProductDetail>(entityManager)
                .select(productDetail)
                .from(productDetail)
                .fetch();

        return products;
    }
}
