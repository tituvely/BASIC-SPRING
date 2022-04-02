package com.example.springapplicationcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BookService {

    @Autowired
    List<BookRepository> bookRepositories;

    @PostConstruct
    public void setup() {
        this.bookRepositories.forEach(System.out::println);
    }
}

