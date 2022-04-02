package com.example.springapplicationcontext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class DaisyBookRepository implements BookRepository {
}
