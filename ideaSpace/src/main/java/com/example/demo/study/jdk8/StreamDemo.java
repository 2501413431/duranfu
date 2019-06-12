package com.example.demo.study.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by admin on 2018/8/7.
 */
public class StreamDemo {
	public static void main(String[] args) {
		init();
	}


	public static void test1() {
		List<Student> students = init();

		List<Integer> a = students.stream()
				.filter(x->x.getScore()>85)
				.sorted(Comparator.comparing(Student::getScore).reversed())
				.map(Student::getScore)
				.collect(Collectors.toList());
		System.out.println(a);
	}

	public static List<Student> init() {
		Random random = new Random();
		ArrayList<Student> students = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			students.add(new Student("student" + i, random.nextInt(50) + 50));
		}
		return students;
	}




	public boolean filter(Student s) {
		return s.getScore() > 85;
	}




}

class Student {
private String name;
private Integer score;

	public Student(String name, Integer score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
