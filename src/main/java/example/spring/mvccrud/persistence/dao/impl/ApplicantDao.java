package example.spring.mvccrud.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example.spring.mvccrud.persistence.dao.IApplicantDao;
import example.spring.mvccrud.persistence.dao.common.AbstractHibernateDao;
import example.spring.mvccrud.persistence.model.Applicant;


@Repository
@Transactional
public class ApplicantDao extends AbstractHibernateDao<Applicant> implements IApplicantDao {

	public ApplicantDao() {
		super();
		
		setClazz(Applicant.class);
	}

}
