package cn.txzh.calcuateworkage;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ChangeAge {
	public static List<Employee> emplpoyees = new ArrayList<Employee>();
	static {
		
		Employee employee100 = new Employee();
		employee100.setEntryDate(LocalDate.parse("2017-02-27"));
		employee100.setName("张三00");
		emplpoyees.add(employee100);
		
		Employee employee11 = new Employee();
		employee11.setEntryDate(LocalDate.parse("2017-02-28"));
		employee11.setName("张三11");
		emplpoyees.add(employee11);
		
		Employee employee22 = new Employee();
		employee22.setEntryDate(LocalDate.parse("2017-03-01"));
		employee22.setName("李四22");
		emplpoyees.add(employee22);
		
		Employee employee10 = new Employee();
		employee10.setEntryDate(LocalDate.parse("2018-01-25"));
		employee10.setName("张三0");
		emplpoyees.add(employee10);
		
		Employee employee1 = new Employee();
		employee1.setEntryDate(LocalDate.parse("2018-02-25"));
		employee1.setName("张三");
		emplpoyees.add(employee1);
		
		Employee employee2 = new Employee();
		employee2.setEntryDate(LocalDate.parse("2018-02-24"));
		employee2.setName("李四");
		emplpoyees.add(employee2);
		
		Employee employee20 = new Employee();
		employee20.setEntryDate(LocalDate.parse("2018-02-27"));
		employee20.setName("李四0");
		emplpoyees.add(employee20);
		
		Employee employee21 = new Employee();
		employee21.setEntryDate(LocalDate.parse("2018-02-28"));
		employee21.setName("李四1");
		emplpoyees.add(employee21);
		
		Employee employee3 = new Employee();
		employee3.setEntryDate(LocalDate.parse("2018-03-02"));
		employee3.setName("王五");
		emplpoyees.add(employee3);
		
		Employee employee4 = new Employee();
		employee4.setEntryDate(LocalDate.parse("2018-03-01"));
		employee4.setName("赵六");
		emplpoyees.add(employee4);
		
		Employee employee5 = new Employee();
		employee5.setEntryDate(LocalDate.parse("2018-03-03"));
		employee5.setName("周七");
		emplpoyees.add(employee5);
		
		Employee employee50 = new Employee();
		employee50.setEntryDate(LocalDate.parse("2019-02-03"));
		employee50.setName("周七0");
		emplpoyees.add(employee50);
		
		Employee employee51 = new Employee();
		employee51.setEntryDate(LocalDate.parse("2019-02-27"));
		employee51.setName("周七1");
		emplpoyees.add(employee51);
	}
	
	public static void main(String[] args) {
		LocalDate now  = LocalDate.now();
		for(Employee employee : emplpoyees){
			addAge(employee, now);
		}
	}
	
	public static void addAge(Employee employee, LocalDate now) {
		LocalDate entryDate = employee.getEntryDate();
		if (entryDate.equals(now)) {
			return;
		}
		
		int nowYear = now.getYear();
		int entryDateYear = entryDate.getYear();
		
		int diff = nowYear - entryDateYear;
		LocalDate entryDatePlusYear;
		if (diff > 0) {
			entryDatePlusYear = entryDate.plusYears(diff);
		} else {
			entryDatePlusYear = entryDate;
		}
		
		LocalDate minus1Days = entryDatePlusYear.minusDays(1);
		LocalDate minus2Days = entryDatePlusYear.minusDays(2);
		
		if (now.equals(entryDatePlusYear) || now.equals(minus1Days) || now.equals(minus2Days)) {
			System.out.println(employee + "工龄即将变为：" + (diff));
		}
		
	}
}
