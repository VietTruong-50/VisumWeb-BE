package com.hust.visum.service.implement;

import com.hust.visum.model.Composer;
import com.hust.visum.repository.ComposerRepository;
import com.hust.visum.service.ComposerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComposerServiceImpl implements ComposerService {

    @Autowired
    private ComposerRepository composerRepository;

    @Override
    public Page<Composer> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return composerRepository.findAll(pageable);
    }

    @Override
    public Composer findById(Long id) {
        return composerRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Composer> findAllByName(String name, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return composerRepository.findAllByComposerName(name, pageable);
    }
}
