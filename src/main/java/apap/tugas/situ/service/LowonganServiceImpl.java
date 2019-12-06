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

    @Override
    public void changeJumlahLowongan (Long idLowongan, Integer jumlah){
        LowonganModel target = findLowonganById(idLowongan);
        target.setJumlah(jumlah);
        System.out.println("==================================");
        System.out.println(jumlah);
        System.out.println(target.getJumlah());
        lowonganDb.save(target);
    }

    @Override
    public boolean foundSimilar(LowonganModel lowonganModel){
        List<LowonganModel> list = findAllLowongan();
        for (LowonganModel lowongan:list){
            if (lowongan.getIdJenis() == lowonganModel.getIdJenis() &&
                    lowongan.getJumlah() ==lowonganModel.getJumlah() &&
                    lowongan.getJudul().equalsIgnoreCase(lowonganModel.getJudul())
                    )
            {
               return true;
            }
        }
        return false;
    }
}
