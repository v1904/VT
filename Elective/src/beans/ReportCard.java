package beans;

import java.util.ArrayList;

/**
 * Класс Javabean отвечающий за зачетку студента 
 * @author Владимир
 * @version 1.0
 */
public class ReportCard {
	/**
	 * Класс представляющий собой строку в зачетке 
	 * @author Владимир
	 * @version 1.0
	 */
	public class Grade
	{
		/** Идентификатор курса по которому выставлена оценка */
		private int 	courseId;
		/** Оценка по пройденому курсу */
		private byte 	grade;
		/** Отзыв преподавателя */
		private String 	comment;

		public int getCourseId(){
			return courseId;
		}
		public void setCourseId(int courseId){
			this.courseId = courseId;
		}
		public byte getGrade(){
			return grade;
		}
		public void setGrade(byte grade){
			this.grade = grade;
		}
		public String getComment(){
			return comment;
		}
		public void setComment(String comment){
			this.comment = comment;
		}
		
		/**
	     * Функция преобразования класса {@link Grade} в строку 
	     * @return возвращает строковое представление оценки
	     */
		@Override
	    public String toString() {
	        return courseId + " " + grade + " " + comment;
	    }
	}	
	
	/** Студент которому принадлежит зачетка */
	protected int studentId;
	/** Оценки в зачетке */
	protected ArrayList<Grade> grades = new ArrayList<Grade>();
	
	/**
     * Функция получения значения поля {@link ReportCard#studentId}
     * @return возвращает студента которому принадлежит зачетка
     */
	public Integer getStudentId(){
		return studentId;
	}
	/**
     * Функция установки значения поля {@link ReportCard#studentId}
     * @param student - студент которому принадлежит зачетка
     */
	public void setStudent(int studentId){
		this.studentId = studentId;
	}
	/**
     * Функция получения значения поля {@link ReportCard#grades}
     * @return возвращает оценки из зачетки студента
     */
	public ArrayList<Grade> getGrades(){
		return grades;
	}
	/**
     * Функция установки значения поля {@link ReportCard#grades}
     * @param grades - оценки из зачетки студента
     */
	public void setGrades(ArrayList<Grade> grades){
		this.grades = grades;
	}
	
	/**
     * Функция экфивалентности объектов класса {@link ReportCard}
     * @param o - объект с которым сравнивается текущий {@link ReportCard}
     * @return вернет true если это одна и та же зачетка
     */
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReportCard that = (ReportCard)o;

        return this.studentId != that.studentId ? false : true;
    }
	/**
     * Функция получения хэш кода класса {@link ReportCard}
     * @return возвращает хэш код зачетки
     */
	@Override
	public int hashCode() {
		return studentId;
	}
	/**
     * Функция преобразования класса {@link ReportCard} в строку 
     * @return возвращает строковое представление зачетки
     */
	@Override
    public String toString() {
        return studentId + " - зачетка";
    }
}