package apap.tugas.situ.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.situ.model.*;
import apap.tugas.situ.repository.JenisSuratDb;
import apap.tugas.situ.service.*;

@Controller
public class JenisSuratController {
    @Qualifier("jenisSuratServiceImpl")
    @Autowired
    private JenisSuratService jenisSuratService;

    @RequestMapping(value = "/jenisSurat", method = RequestMethod.GET) 
    public String home(Model model) {
    	List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();
    	model.addAttribute("jenisSuratList", jenisSuratList);
        return "viewall-jenisSurat";
    }
    
    @RequestMapping(value = "/jenisSurat/tambah", method = RequestMethod.GET)
    public String addJenisSuratFormPage(Model model) {
        JenisSuratModel jenisSurat = new JenisSuratModel();
        model.addAttribute("jenisSurat", jenisSurat);
        return "form-add-jenisSurat";
    }
    
    @RequestMapping(value = "/jenisSurat/tambah", method = RequestMethod.POST)
    public String addJenisSuratFormPageSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model) {
    	if (jenisSuratService.checkJenisSurat(jenisSurat.getNama()) == true) {
	    	jenisSuratService.addJenisSurat(jenisSurat);
	        model.addAttribute("jenisSurat", jenisSurat);
	        return "add-jenisSurat";
    	} else {
    		return "failed-add-jenisSurat";
    	}
    }
    
    @RequestMapping(value = "/jenisSurat/hapus/{idJenisSurat}", method = RequestMethod.GET)
    public String deleteJenisSurat(@PathVariable Long idJenisSurat,
                                Model model) {
    	JenisSuratModel jenisSurat = jenisSuratService.getJenisSuratByIdJenisSurat(idJenisSurat).get();
        jenisSuratService.deleteJenisSurat(idJenisSurat);
        return "hapus-jenisSurat";
    }   
}