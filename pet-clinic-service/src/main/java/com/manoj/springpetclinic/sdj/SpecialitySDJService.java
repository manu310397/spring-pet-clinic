package com.manoj.springpetclinic.sdj;

import com.manoj.springpetclinic.SpecialityService;
import com.manoj.springpetclinic.model.Speciality;
import com.manoj.springpetclinic.repositories.SpecialityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("sdj")
public class SpecialitySDJService implements SpecialityService {
    private final SpecialityRepository specialityRepository;

    public SpecialitySDJService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();

        specialityRepository.findAll().forEach(specialities::add);

        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
