package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisLowonganModel;
import apap.tugas.situ.model.LowonganModel;
import apap.tugas.situ.repository.JenisLowonganDb;
import apap.tugas.situ.repository.LowonganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenisLowonganServiceImpl implements JenisLowonganService{

    @Autowired
    JenisLowonganDb jenisLowonganDb;

    @Autowired
    LowonganDb lowonganDb;

    @Override
    public void addJenisLowongan(JenisLowonganModel jenisLowonganModel){
        jenisLowonganDb.save(jenisLowonganModel);
    }

    @Override
    public List<JenisLowonganModel> findAllJenis(){
        return jenisLowonganDb.findAll();
    }

    @Override
    public JenisLowonganModel findJenisById(Long idJenisLowongan){
        return jenisLowonganDb.findById(idJenisLowongan).get();
    }

    @Override
    public Boolean checkValid(String namaJenis){
        List<JenisLowonganModel> listJenis = jenisLowonganDb.findAll();
        if(listJenis.size() == 0){return true;}
        else{
            for(JenisLowonganModel jenis:listJenis){
                if (jenis.getNama().equalsIgnoreCase(namaJenis)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void deleteJenis(JenisLowonganModel jenisLowongan){
        jenisLowonganDb.delete(jenisLowongan);
    }

    @Override
    public Boolean checkDeletable(JenisLowonganModel jenisLowongan){
        List<LowonganModel> listLowongan = lowonganDb.findAll();
        for (LowonganModel lowongan:listLowongan){
            if (lowongan.getIdJenis() == jenisLowongan.getIdJenis()){
                return false;
            }
        }
        return true;
    }
}


