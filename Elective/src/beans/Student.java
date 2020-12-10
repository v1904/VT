package beans;

import java.util.ArrayList;
/**
 * Класс Javabean расширяющий {@link Person} 
 * @author Владимир
 * @version 1.0
 */
public class Student extends Person {
	/** Курсы посещаемые студентом */
	protected ArrayList<Integer> coursesId = new ArrayList<Integer>();
	/**
    * Функция получения значения поля {@link Student#coursesId}
    * @return возвращает список курсов посещаемых студентом
    */
	public ArrayList<Integer> getCoursesId()
	{
		return coursesId;
	}
	/**
     * Функция установки значения поля {@link Student#coursesId}
     * @param courses - список курсов посещаемых студентом
     */
	public void setCoursesId(ArrayList<Integer> coursesId)
	{
		this.coursesId = coursesId;
	}
	/**
     * Функция экфивалентности объектов класса {@link Student}
     * @param o - объект с которым сравнивается текущий {@link Student}
     * @return вернет true если это один и тот же студент
     */
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student that = (Student) o;

        return this.id != that.id ? false : true;
    }
	/**
     * Функция получения хэш кода класса {@link Student}
     * @return возвращает хэш код учителя
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * Функция преобразования класса {@link Student} в строку 
     * @return возвращает строковое представление студента
     */
	@Override
    public String toString() {
        return lastName + " " + name + " " + age;
    }
}