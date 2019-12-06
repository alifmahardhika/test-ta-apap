package apap.tugas.situ.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import apap.tugas.situ.model.*;

public interface JenisSuratService {
    // Method untuk menambah jenis surat
    void addJenisSurat(JenisSuratModel jenisSurat);

    // Method untuk mendapatkan semua data JenisSurat yang tersimpan
    List<JenisSuratModel> getJenisSuratList();

    // Method untuk menghapus data sebuah JenisSurat berdasarkan idJenisSurat
    boolean deleteJenisSurat(Long idJenisSurat);
    
    // Method untuk mendapatkan jenis surat berdasarkan idnya
    Optional<JenisSuratModel> getJenisSuratByIdJenisSurat(Long idJenisSurat);
    
    // Method untuk mengecek apakah jenis surat sudah terdaftar dalam database
    boolean checkJenisSurat(String nama);
}