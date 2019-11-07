package apap.tugas.situ.service;

import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.repository.LowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LowonganServiceImpl implements LowonganService {
    @Autowired
    LowonganDb lowonganDb;

    @Override
    public void addLowongan(LowonganModel lowonganModel){
        lowonganDb.save(lowonganModel);
    }

    @Override
    public List<LowonganModel> findAllLowongan(){
        return lowonganDb.findAll();
    }

    @Override
    public LowonganModel findLowonganById(Long idLowongan){
        return lowonganDb.findById(idLowongan).get();
    }
}
