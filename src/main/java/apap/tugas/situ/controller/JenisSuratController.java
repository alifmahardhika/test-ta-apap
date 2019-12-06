package apap.tugas.situ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.situ.model.*;
import apap.tugas.situ.service.*;

@Controller
public class JenisSuratController {
    @Qualifier("jenisSuratServiceImpl")
    @Autowired
    private JenisSuratService jenisSuratService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/jenis-surat/view-all", method = RequestMethod.GET)
    public String viewAllJenisSurat(Authentication authentication, Model model) {
    	List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();
        UserModel user = userService.getUser(authentication.getName());
    	model.addAttribute("jenisSuratList", jenisSuratList);
        model.addAttribute("user", user);
        return "jenis-surat/viewall-jenisSurat";
    }
    
    @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.GET)
    public String addJenisSuratFormPage(Authentication authentication, Model model) {
        JenisSuratModel jenisSurat = new JenisSuratModel();
        UserModel user = userService.getUser(authentication.getName());
        model.addAttribute("jenisSurat", jenisSurat);
        model.addAttribute("user", user);
        return "jenis-surat/form-add-jenisSurat";
    }
    
    @RequestMapping(value = "/jenis-surat/add", method = RequestMethod.POST)
    public String addJenisSuratFormPageSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model) {
    	if (jenisSuratService.checkJenisSurat(jenisSurat.getNama()) == true) {
	    	jenisSuratService.addJenisSurat(jenisSurat);
	        model.addAttribute("jenisSurat", jenisSurat);
	        return "jenis-surat/add-jenisSurat";
    	} else {
    	    model.addAttribute("alasan", "Error! Terdapat nama Jenis Surat yang sama pada Database!");
    		return "error";
    	}
    }
    
    @RequestMapping(value = "/jenis-surat/delete/{idJenisSurat}", method = RequestMethod.GET)
    public String deleteJenisSurat(@PathVariable Long idJenisSurat,
                                Model model) {
    	JenisSuratModel jenisSurat = jenisSuratService.getJenisSuratByIdJenisSurat(idJenisSurat).get();
        jenisSuratService.deleteJenisSurat(idJenisSurat);
        model.addAttribute("namaSurat", jenisSurat.getNama());
        return "jenis-surat/hapus-jenisSurat";
    }

}