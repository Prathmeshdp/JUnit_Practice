package com.bikkadIt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bikkadIt.dao.ContactDao;
import com.bikkadIt.model.Contact;

class ContactServiceImplTest {

	public static ContactServiceImpl contactServiceImpl=null;
	

	@BeforeAll
	public static void init()
	{
		ContactDao daoproxy = EasyMock.createMock(ContactDao.class);

		List<String> list= new ArrayList<>();		
		list.add("prathmesh");
		list.add("Ram");
		list.add("Sham");
		EasyMock.expect(daoproxy.findNames()).andReturn(list);
		
		
 		Contact contact= new Contact();
 		contact.setContactId(101);
 		contact.setContactname("Prathmesh");
 		contact.setContactNumber(134564645);
 		EasyMock.expect(daoproxy.findById(101)).andReturn(contact);
 		
 		
 		EasyMock.expect(daoproxy.findNameById(101)).andReturn("Prathmesh");
 		
 		contactServiceImpl= new ContactServiceImpl();
 		contactServiceImpl.setContactDao(daoproxy);
 		EasyMock.replay(daoproxy);
		
	}
	
	
	@Test
	public void getAllContactNameTest()
	{
		List<String> listnames = contactServiceImpl.getAllContactName();
		System.out.println(listnames);
		assertNotNull(listnames);
		
	}
	
	
	@Test
	public void getContactByIdTest()
	{
		Contact contact = contactServiceImpl.getContactById(101);
		System.out.println(contact);
		Integer actualresult = contact.getContactId();
		
		Integer expectedresult=101;
		
		assertNotNull(contact);
		
		
	}
	
	
	@Test
	public void getNameByIdTest()
	{
		String name = contactServiceImpl.getNameById(101);
		System.out.println(name);
		assertNotNull(name);
	}
	
	
}
