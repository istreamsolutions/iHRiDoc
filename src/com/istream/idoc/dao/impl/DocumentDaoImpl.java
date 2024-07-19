package com.istream.idoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.istream.idoc.dao.DocumentDao;
import com.istream.idoc.orm.Document;
import com.istream.idoc.orm.Folder;
import com.istream.idoc.orm.ImmigrationType;

public class DocumentDaoImpl implements DocumentDao {
	private SessionFactory sessionFactory;

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Folder> getFoldersByImmigrationType(int immiType) {
		Session session = getSessionFactory().openSession();
		String hql = "from Folder f where f.immi_type_id = :immi_type_id ";
		Query qry = session.createQuery(hql);
		qry.setParameter("immi_type_id", immiType);
		List<Folder> folders = qry.list(); 		
		return folders;	
	}

	@Override
	public List<ImmigrationType> getImmigrationTypes() {
		Session session = getSessionFactory().openSession();
		String hql = "from ImmigrationType i";
		Query qry = session.createQuery(hql);
		List<ImmigrationType> folders = qry.list(); 		
		return folders;	
	}

	@Override
	public List<Document> getDocumentByFolder(int immigrationTypeId,int folderId){
		Session session = getSessionFactory().openSession(); 		
		List<Document> doc = null;
		//String hql = "from Document d where d.folder.folder_id = :folderId  order by d.creation_ts desc";
		String hql = "from Document doc left join fetch doc.folder docFolder where docFolder.folder_id = :folderId and docFolder.immi_type_id = :immigrationTypeId"
				+ " order by doc.creationTs desc";
		Query qry = session.createQuery(hql);
		qry.setParameter("folderId", folderId);
		qry.setParameter("immigrationTypeId", immigrationTypeId);
		doc = qry.list(); 		
		return doc;	
	}

	@Override
	public List<Document> getDocumentWithoutFolder(){
		Session session = getSessionFactory().openSession(); 		
		List<Document> doc = null;
		String hql = "from Document d where d.folder is null order by d.creationTs desc";
		Query qry = session.createQuery(hql);
		doc = qry.list(); 		
		return doc;	
	}
	
	@Override
	public List<Document> getDocumentByEmployee(long employeeId){
		Integer empId=(int)employeeId; 
		System.out.println("Document Dao Impl -> Employee Id : "+empId);
		Session session = getSessionFactory().openSession(); 		
		List<Document> doc = null;
		String hql = "from Document doc left join fetch doc.folder docFolder left join fetch doc.employee docEmployee where docEmployee.employeeId = :empId order by doc.creationTs desc";
		Query qry = session.createQuery(hql);
		qry.setParameter("empId", empId);
		System.out.println("Document Dao Impl -> hql : "+hql);
		doc = qry.list(); 		
		return doc;	
	}

	@Override
	public void createDocument(Document newDocument){
		System.out.println("Inside Creat Document:"+newDocument);
		Session session = getSessionFactory().openSession();
		session.save(newDocument);
		session.flush();
		session.close();
	}
	
	@Override
	public void deleteDocument(String document_uuid){
		
		Session session = getSessionFactory().openSession();
		Document document = (Document ) session.createCriteria(Document.class).add(Restrictions.eq("documentUuid", document_uuid)).uniqueResult();
		session.delete(document);
		session.flush();
		session.close();
	}
}
