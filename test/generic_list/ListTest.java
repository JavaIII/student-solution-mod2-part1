/*
 * Copyright 2018 the original author or authors.
 */
package generic_list;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class ListTest {
	
	@Test
	void testAddNames() {
		CustomList<String>nameList = new CustomList<>();
		nameList.add("Keith");
		assertEquals("Keith", nameList.get(0));
		nameList.add("Dave");
		assertEquals("Keith", nameList.get(0));
		assertEquals("Dave", nameList.get(1));
		String [] names = {"Bill", "Sarah", "Paula", "Kevin", "Jenna"};
		for(int i = 0; i < names.length; i++) {
			nameList.add(names[i]);
		}
	}
	
	@Test
	void testAddAges() {
		CustomList<Integer>ageList = new CustomList<Integer>();
		ageList.add(18);
		assertTrue(18 == ageList.get(0));
		ageList.add(29);
		ageList.add(32);
		assertTrue(29 == ageList.get(1));
		assertTrue(32 == ageList.get(2));		
	}
	
	
	@Test
	void testRemove() {
		CustomList<String>nameList = new CustomList<>();
		String [] names = {"Bill", "Sarah", "Paula", "Kevin", "Jenna"};
		for(int i = 0; i< names.length; i++) {
			nameList.add(names[i]);
		}
		nameList.remove(0);
		assertTrue(nameList.size() == 4);
		assertEquals("Sarah", nameList.get(0));
		assertEquals("Jenna", nameList.get(nameList.size()-1));
		
	}
	
	@Test
	public void testSize() {
		CustomList<String>nameList = new CustomList<>();
		String [] names = {"Bill", "Sarah", "Paula", "Kevin", "Jenna"};
		for(int i = 0; i< names.length; i++) {
			nameList.add(names[i]);
		}
		assertTrue(nameList.size() == 5);
		CustomList<Integer>ageList = new CustomList<>();
		assertTrue(ageList.size() == 0);
	}

	@Test
	public void testIsEmpty() {
		CustomList<String>nameList = new CustomList<>();
		assertTrue(nameList.isEmpty());
		String [] names = {"Bill", "Sarah", "Paula", "Kevin", "Jenna"};
		for(int i = 0; i< names.length; i++) {
			nameList.add(names[i]);
		}
		
	}

	@Test
	public void testAddAndGet() {
		CustomList<Integer> list = new CustomList<Integer>();
		list.add(0);
		list.add(1);
		list.add(206);
	
		if(list.get(0) != 0) fail("list.get(0) returning improper value.");
		if(list.get(1) != 1) fail("list.get(1) returning improper value.");
		if(list.get(2) != 206) fail("list.get(2) returning improper value.");
	}
	
	@Test
	public void testSet2() {
		CustomList<String> list = new CustomList<String>();
		list.add("TESTER");
		list.add("ONE");
		list.add("TWO");
		list.add("THREE");
		
		list.set(2, "PICKLES");
		
		if(!list.get(0).equals("TESTER")) fail("list.get(0) returning improper value.");
		if(!list.get(1).equals("ONE"))  fail("list.get(1) returning improper value.");
		if(!list.get(2).equals("PICKLES"))  fail("list.get(2) returning improper value.");
		if(!list.get(3).equals("THREE"))  fail("list.get(3) returning improper value.");
	}
	
	@Test
	public void testAddAt() {
		CustomList<Character> list = new CustomList<Character>();
		list.add('D');
		list.add('I');
		list.add('G');
		list.add('O');
		
		list.add(2, 'E');
		
		if(list.get(0) != 'D') fail("list.get(0) returning improper value.");
		if(list.get(1) != 'I') fail("list.get(1) returning improper value.");
		if(list.get(2) != 'E') fail("list.get(2) returning improper value.");
		if(list.get(3) != 'G') fail("list.get(3) returning improper value.");
		if(list.get(4) != 'O') fail("list.get(4) returning improper value.");
	}
	
	@Test
	public void testRemove2() {
		class Student { 
			public String name; 
			public Student(String s) {
				name = s;
			}
		}
		CustomList<Student> list = new CustomList<Student>();
		list.add(new Student("Tim"));
		list.add(new Student("Sara"));
		list.add(new Student("Kamara"));
		list.add(new Student("Devaugn"));
		
		list.remove(0);
		
		if(!list.get(0).name.equals("Sara")) {
			fail("list.get(0) returning improper value.");
		}
		if(!list.get(1).name.equals("Kamara")) {
			fail("list.get(1) returning improper value.");
		}
		if(!list.get(2).name.equals("Devaugn")) {
			fail("list.get(2) returning improper value.");
		}
		
		list.remove(2);
		if(!list.get(0).name.equals("Sara")) {
			fail("list.get(0) returning improper value.");
		}
		if(!list.get(1).name.equals("Kamara")) {
			fail("list.get(1) returning improper value.");
		}
	}
	
	@Test
	public void testContains() {
		CustomList<String> list = new CustomList<String>();
		list.add("This");
		list.add("is");
		list.add("only");
		list.add("a");
		list.add("test");
		
		assertTrue(list.contains("This"));
		assertTrue(list.contains("is"));
		assertTrue(list.contains("only"));
		assertFalse(list.contains("the"));
		assertFalse(list.contains("best"));
	}
	
	@Test
	public void testClear() {
		CustomList<String> list = new CustomList<String>();
		list.add("This");
		list.add("is");
		list.add("only");
		list.add("a");
		list.add("test");
		list.clear();
		assertTrue(list.isEmpty());
	}
}
