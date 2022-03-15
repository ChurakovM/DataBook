package com.example.bookservice.persistence;

import com.example.bookservice.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<BookModel, Long>, JpaSpecificationExecutor<BookModel> {

}
