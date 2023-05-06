package io.toprate.si.service;

import io.toprate.si.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class cartService {
    private final CartRepository cartRepository;

}
