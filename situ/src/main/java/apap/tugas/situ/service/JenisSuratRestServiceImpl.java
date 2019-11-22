package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisSuratModel;
import apap.tugas.situ.repository.JenisSuratDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class JenisSuratRestServiceImpl implements JenisSuratRestService{
    @Autowired
    private JenisSuratDb jenisSuratDb;

    @Override
    public JenisSuratModel getJenisSuratById(Long idJenisSurat) {
        Optional<JenisSuratModel> jenisSurat = jenisSuratDb.findByIdJenisSurat(idJenisSurat);
        if (jenisSurat.isPresent()) {
            return jenisSurat.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
