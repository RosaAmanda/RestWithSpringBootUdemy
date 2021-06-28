package br.com.erudio.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.model.Person;

public class DozerConverterTest {
	
	
	MockPerson inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name 0", output.getFirstName());
		Assert.assertEquals("Last Name 0", output.getLastName());
		Assert.assertEquals("Address 0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());		
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name 0", outputZero.getLastName());
		Assert.assertEquals("Address 0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name 7", outputSeven.getLastName());
		Assert.assertEquals("Address 7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());	
	}
	
	@Test
	public void parseVoToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name 0", output.getFirstName());
		Assert.assertEquals("Last Name 0", output.getLastName());
		Assert.assertEquals("Address 0", output.getAddress());
		Assert.assertEquals("Male", output.getGender());		
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);
		
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name 0", outputZero.getLastName());
		Assert.assertEquals("Address 0", outputZero.getAddress());
		Assert.assertEquals("Male", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);
		
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name 7", outputSeven.getLastName());
		Assert.assertEquals("Address 7", outputSeven.getAddress());
		Assert.assertEquals("Female", outputSeven.getGender());	
	}
}
