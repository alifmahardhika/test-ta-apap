package apap.tugas.situ.service;

import apap.tugas.situ.model.JenisSuratModel;

import java.util.Optional;

public interface JenisSuratRestService {
    JenisSuratModel getJenisSuratById(Long idJenisSurat);
}
