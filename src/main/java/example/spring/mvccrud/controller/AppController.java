package example.spring.mvccrud.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.spring.mvccrud.config.AppValidator;
import example.spring.mvccrud.config.Utils;
import example.spring.mvccrud.persistence.service.IApplicantService;
import example.spring.mvccrud.persistence.service.entity.ApplicantInfo;

@Controller
public class AppController {

	@Autowired
	private IApplicantService applicantService;
	
	@Autowired
	private AppValidator appValidator;
	
	@RequestMapping("/")
	public String homePage(Model model) {
		return applicantList(model);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/applicantList")
	public String applicantList(Model model) {
		Object object = applicantService.getAllApplicants();
		//TODO:: ERROR HANDLING
		List<ApplicantInfo> list = (List<ApplicantInfo>) object;
		model.addAttribute("applicantInfos", list);
		
		return "applicantList";
	}
	
	@RequestMapping("/createApplicant")
	public String createApplicant(Model model) {
		ApplicantInfo applicantInfo = new ApplicantInfo();
		
		return this.formApplicant(model, applicantInfo);
	}
	
	@RequestMapping("/editApplicant") 
	public String editApplicant(Model model, @RequestParam("id") Integer id) {
		ApplicantInfo applicantInfo = null;
		if (Utils.isEmpty(id)) {
			return "redirect:/applicantList";
		}
		Object object = applicantService.getApplicantsInfo(id);
		//TODO:: ERROR HANDLING
		applicantInfo = (ApplicantInfo) object;
		
		return this.formApplicant(model, applicantInfo);
	}
	
	@RequestMapping("/deleteApplicant")
	public String deleteApplicant(Model model, @RequestParam("id") Integer id) {
		if (!Utils.isEmpty(id)) {
			Object object = applicantService.deleteApplicantsById(id);
			//TODO:: ERROR HANDLING
		}
		
		return "redirect:/applicantList";		
	}
	
	@RequestMapping(value = "/saveApplicant", method = RequestMethod.POST)
	public String saveApplicant(Model model, @ModelAttribute("applicantForm") @Validated ApplicantInfo applicantInfo,
								BindingResult result, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return this.formApplicant(model, applicantInfo);
		}
		if (!Utils.isEmpty(applicantInfo.getId())) {
			Object object = applicantService.updateApplicants(applicantInfo);
		} else {
			Object object = applicantService.createApplicants(applicantInfo);
		}
		//TODO:: ERROR HANDLING
		redirectAttributes.addFlashAttribute("message", "Save Applicant Successful");
		
		return "redirect:/applicantList";		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target =" + target);
		if (target.getClass() == ApplicantInfo.class) {
			dataBinder.setValidator(appValidator);
		}
	}
	
	@ModelAttribute("positions")
	private Map<String, String> dataForPositions() {
		Map<String, String> positions = new LinkedHashMap<String, String>();
		positions.put("Dev", "Developer");
		positions.put("Lead", "Leader");
		positions.put("QA", "SQA");
		
		return positions;
	}
	
	@ModelAttribute("skills")
	private List<String> dataForSkills() {
		List<String> skills = new ArrayList<String>();
		skills.add("Java");
		skills.add("Python");
		skills.add("C/C++");
		
		return skills;
	}
	
	private String formApplicant(Model model, ApplicantInfo applicantInfo) {
		model.addAttribute("applicantForm", applicantInfo);		
		String formTitle = "Create Applicant";		
		if (applicantInfo.getId() != null) {
			formTitle = "Edit Applicant";
		}
		model.addAttribute("formTitle", formTitle);
		
		return "formApplicant";
	}
}
