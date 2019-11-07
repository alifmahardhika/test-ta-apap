package apap.tugas.situ.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.repository.JenisSuratDb;

@Service
@Transactional

public class JenisSuratServiceImpl implements JenisSuratService {

	@Autowired
    private JenisSuratDb jenisSuratDb;

    @Override
    public void addJenisSurat(JenisSuratModel jenisSurat) {
        jenisSuratDb.save(jenisSurat);
    }

    @Override
    public List<JenisSuratModel> getJenisSuratList() {
        return jenisSuratDb.findAllByOrderByNamaAsc();
    }

    @Override
    // Need check
    public boolean deleteJenisSurat(Long idJenisSurat) {
        jenisSuratDb.deleteById(idJenisSurat);
        return true;
    }
    
    @Override
    public boolean checkJenisSurat(String nama) {
    	List<JenisSuratModel> temp = jenisSuratDb.findAllByOrderByNamaAsc();
    	if (temp.size() == 0) {
    		return true;
    	}
    	
    	for (JenisSuratModel a : temp) {
	    	if (!nama.equals(a.getNama())) {
	    		return true;
	    	}
    	}
    	
	    return false;
    }
    
    @Override
    public Optional<JenisSuratModel> getJenisSuratByIdJenisSurat(Long idJenisSurat) {
        try {
            return jenisSuratDb.findByIdJenisSurat(idJenisSurat);
        } catch (NoSuchElementException x) {
            return null;
        }
    }
}