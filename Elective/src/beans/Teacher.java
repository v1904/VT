package beans;

import java.util.ArrayList;

/**
 * Класс Javabean расширяющий {@link Person} 
 * @author Владимир
 * @version 1.0
 */
public class Teacher extends Person {
	/** Курсы которые ведет преподаватель */
	protected ArrayList<Integer> coursesId = new ArrayList<Integer>();
	
	/**
     * Функция получения значения поля {@link Teacher#coursesId}
     * @return возвращает список курсов которые ведет преподаватель
     */
	public ArrayList<Integer> getCoursesId()
	{
		return coursesId;
	}
	/**
     * Функция установки значения поля {@link Teacher#coursesId}
     * @param coursesId - список курсов которые ведет преподаватель
     */
	public void setCoursesId(ArrayList<Integer> coursesId)
	{
		this.coursesId = coursesId;
	}
	/**
     * Функция экфивалентности объектов класса {@link Teacher}
     * @param o - объект с которым сравнивается текущий {@link Teacher}
     * @return вернет true если это один и тот же учитель
     */
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Teacher that = (Teacher) o;

        return this.id != that.id ? false : true;
    }
	/**
     * Функция получения хэш кода класса {@link Teacher}
     * @return возвращает хэш код учителя
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * Функция преобразования класса {@link Teacher} в строку 
     * @return возвращает строковое представление учителя
     */
	@Override
    public String toString() {
        return lastName + " " + name + " " + age;
    }
}