package beans;

/**
 * Базовый класс для {@link Student} и {@link Teacher}
 * @author Владимир
 * @version 1.0
 */
public abstract class Person {
	/** Уникальный идентификатор для каждого человека */
	protected int id = 0;
	/** Имя */
	protected String name = "";
	/** Фамилия */
	protected String lastName = "";
	/** Возраст */
	protected int age = 0;
	
	/**
     * Функция получения значения поля {@link Person#id}
     * @return возвращает id человека
     */
	public int getId(){
		return id;
	}
	/**
     * Функция установки значения поля {@link Person#id}
     * @param id - id человека
     */
	public void setId(int id){
		this.id = id;
	}
	/**
     * Функция получения значения поля {@link Person#name}
     * @return возвращает имя человека
     */
	public String getName(){
		return name;
	}
	/**
     * Функция установки значения поля {@link Person#name}
     * @param name - имя человека
     */
	public void setName(String name){
		this.name = name;
	}
	/**
     * Функция получения значения поля {@link Person#lastName}
     * @return возвращает фамилию человека
     */
	public String getLastName(){
		return lastName;
	}
	/**
     * Функция установки значения поля {@link Person#lastName}
     * @param lastName - фамилия человека
     */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	/**
     * Функция получения значения поля {@link Person#age}
     * @return возвращает возраст человека
     */
	public int getAge(){
		return age;
	}
	/**
     * Функция установки значения поля {@link Person#age}
     * @param age - возраст человека
     */
	public void setAge(int age){
		this.age = age;
	}
}