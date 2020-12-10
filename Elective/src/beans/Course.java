package beans;

import java.util.ArrayList;

/**
 * Класс Javabean отвечающий за курс 
 * @author Владимир
 * @version 1.0
 */
public class Course {
	/** Уникальный идентификатор для каждого курса */
	protected int id;
	/** Название курса */
	protected String name;
	/** Время проведения занятий */
	protected Schedule schedule;
	/** Преподаватель ведущий курс */
	protected int teacherId;
	/** Студенты посещающие занятия */
	protected ArrayList<Integer> studentsId = new ArrayList<Integer>();
	/**
     * Функция получения значения поля {@link Course#id}
     * @return возвращает id курса
     */
	public int getId(){
		return id;
	}
	/**
     * Функция установки значения поля {@link Course#id}
     * @param id - id курса
     */
	public void setId(int id){
		this.id = id;
	}
	/**
     * Функция получения значения поля {@link Course#name}
     * @return возвращает название курса
     */
	public String getName(){
		return name;
	}
	/**
     * Функция установки значения поля {@link Course#name}
     * @param name - название курса
     */
	public void setName(String name){
		this.name = name;
	}
	/**
     * Функция получения значения поля {@link Course#schedule}
     * @return возвращает время проведения занятий
     */
	public Schedule getSchedule(){
		return schedule;
	}
	/**
     * Функция установки значения поля {@link Course#schedule}
     * @param schedule - время проведения занятий
     */
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
	/**
     * Функция получения значения поля {@link Course#teacherId}
     * @return возвращает преподавателя ведущего занятия
     */
	public int getTeacherId(){
		return teacherId;
	}
	/**
     * Функция получения значения поля {@link Course#studentsId}
     * @return возвращает студенов записанных на курс
     */
	public ArrayList<Integer> getStudentsId(){
		return studentsId;
	}
	/**
     * Функция установки значения поля {@link Course#studentsId}
     * @param students - студенты записанные на курс
     */
	public void setStudentsId(ArrayList<Integer> studentsId){
		this.studentsId = studentsId;
	}
	
	/**
     * Функция установки значения поля {@link Course#teacherId}
     * @param teacher - преподаватель ведущий занятия
     */
	public void setTeacherId(int teacherId){
		this.teacherId = teacherId;
	}
	/**
     * Функция экфивалентности объектов класса {@link Course}
     * @param o - объект с которым сравнивается текущий {@link Course}
     * @return вернет true если это один и тот же курс
     */
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course that = (Course)o;

        return this.id != that.id ? false : true;
    }
	/**
     * Функция получения хэш кода класса {@link Course}
     * @return возвращает хэш код курса
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * Функция преобразования класса {@link Course} в строку 
     * @return возвращает строковое представление курса
     */
	@Override
    public String toString() {
        return id + " " + name;
    }
}