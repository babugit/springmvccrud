package example.spring.mvccrud.persistence.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import example.spring.mvccrud.config.Utils;
import example.spring.mvccrud.persistence.dao.IApplicantDao;
import example.spring.mvccrud.persistence.dao.common.IOperations;
import example.spring.mvccrud.persistence.model.Applicant;
import example.spring.mvccrud.persistence.service.IApplicantService;
import example.spring.mvccrud.persistence.service.common.AbstractService;
import example.spring.mvccrud.persistence.service.entity.ApplicantInfo;

public class ApplicantService extends AbstractService<Applicant> implements IApplicantService {
	
	@Autowired
	private IApplicantDao dao;
	
	public ApplicantService() {
		super();
	}
	
	@Override
	protected IOperations<Applicant> getDao() {
		return dao;
	}

	@Override
	public Object createApplicants(ApplicantInfo applicantInfo) {
		if (applicantInfo == null) {
			//TODO HANDLE ERROR
		}
		
		try {
			create(prepareApplicantsEO(applicantInfo, null));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object deleteApplicantsById(int id) {
		// TODO HANDLE ERROR
		try {
			deleteById(id);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object deleteApplicants(ApplicantInfo applicantInfo) {
		// TODO HANDLE ERROR
		try {
			delete(prepareApplicantsEO(applicantInfo, null));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object updateApplicants(ApplicantInfo applicantInfo) {
		// TODO HANDLE ERROR
		try {
			Object object = findOneById(applicantInfo.getId());
			example.spring.mvccrud.persistence.model.Applicant applicantEO = (example.spring.mvccrud.persistence.model.Applicant) object;
			update(prepareApplicantsEO(applicantInfo, applicantEO));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object getAllApplicants() {
		// TODO HANDLE ERROR
		List<ApplicantInfo> applicantInfos = new ArrayList<ApplicantInfo>();
		try {
			List<example.spring.mvccrud.persistence.model.Applicant> applicantEO = findAll();
			applicantEO.stream().forEach((applicant) -> {
				if (applicant != null) {
					applicantInfos.add(new ApplicantInfo(applicant.getId(), applicant.getName(), applicant.getEmail(), 
														applicant.getGender(), applicant.getPosition(), applicant.getSkills()));
				}
			});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return applicantInfos;
	}

	@Override
	public Object getApplicantsInfo(Integer id) {
		// TODO HANDLE ERROR
		ApplicantInfo applicantInfo = new ApplicantInfo();
		try {
			example.spring.mvccrud.persistence.model.Applicant applicant = findOneById(id);
			if (applicant != null) {
				applicantInfo = new ApplicantInfo(applicant.getId(), applicant.getName(), applicant.getEmail(), 
													applicant.getGender(), applicant.getPosition(), applicant.getSkills());
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicantInfo;
	}
	
	private example.spring.mvccrud.persistence.model.Applicant prepareApplicantsEO(ApplicantInfo applicantInfo, 
																					example.spring.mvccrud.persistence.model.Applicant applicantEO) {
		if (applicantEO == null) {
			applicantEO = new example.spring.mvccrud.persistence.model.Applicant();	
		}		
		applicantEO.setName(applicantInfo.getName());
		applicantEO.setGender(applicantInfo.getGender());
		applicantEO.setPosition(applicantInfo.getPosition());
		applicantEO.setSkills(Utils.arrayToString(applicantInfo.getSkills()));
		applicantEO.setEmail(applicantInfo.getEmail());
		
		return applicantEO;
	}
}
