package com.vanaras.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public @interface Transaction {
}
