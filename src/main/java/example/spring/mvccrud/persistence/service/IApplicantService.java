package example.spring.mvccrud.persistence.service;

import example.spring.mvccrud.persistence.service.entity.ApplicantInfo;

public interface IApplicantService {
	
	Object getApplicantsInfo(Integer id);
	
	Object createApplicants(ApplicantInfo applicantInfo);
	
	Object deleteApplicantsById(final int id);
	
	Object deleteApplicants(ApplicantInfo applicantInfo);
	
	Object updateApplicants(ApplicantInfo applicantInfo);
	
	Object getAllApplicants();
}