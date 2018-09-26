package com.zby.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.user")
public class User {

	private int age;
	private String name;
	// 这个注解不是必须的，不影响绑定
	// @NestedConfigurationProperty
	private Dog dog;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", dog=" + dog + "]";
	}

	public static class Dog {
		private String name;
		private String color;
		private boolean kavayi;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public boolean isKavayi() {
			return kavayi;
		}

		public void setKavayi(boolean kavayi) {
			this.kavayi = kavayi;
		}

		@Override
		public String toString() {
			return "Dog [name=" + name + ", color=" + color + ", kavayi=" + kavayi + "]";
		}
	}
}
