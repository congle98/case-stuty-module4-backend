package com.app.service.evaluateservice;

import com.app.entity.Evaluate;
import com.app.repository.EvaluateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EvaluateService implements IEvaluateService{
    @Autowired
    EvaluateRepository evaluateRepository;
    @Override
    public Iterable<Evaluate> findAll() {
        return evaluateRepository.findAll();
    }

    @Override
    public Optional<Evaluate> findById(Long id) {
        return evaluateRepository.findById(id);
    }

    @Override
    public void save(Evaluate evaluate) {
        evaluateRepository.save(evaluate);
    }

    @Override
    public void remove(Long id) {
        evaluateRepository.deleteById(id);
    }
}
