package beans;

import java.util.ArrayList;

/**
 * ����� Javabean ���������� �� ������� �������� 
 * @author ��������
 * @version 1.0
 */
public class ReportCard {
	/**
	 * ����� �������������� ����� ������ � ������� 
	 * @author ��������
	 * @version 1.0
	 */
	public class Grade
	{
		/** ������������� ����� �� �������� ���������� ������ */
		private int 	courseId;
		/** ������ �� ���������� ����� */
		private byte 	grade;
		/** ����� ������������� */
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
	     * ������� �������������� ������ {@link Grade} � ������ 
	     * @return ���������� ��������� ������������� ������
	     */
		@Override
	    public String toString() {
	        return courseId + " " + grade + " " + comment;
	    }
	}	
	
	/** ������� �������� ����������� ������� */
	protected int studentId;
	/** ������ � ������� */
	protected ArrayList<Grade> grades = new ArrayList<Grade>();
	
	/**
     * ������� ��������� �������� ���� {@link ReportCard#studentId}
     * @return ���������� �������� �������� ����������� �������
     */
	public Integer getStudentId(){
		return studentId;
	}
	/**
     * ������� ��������� �������� ���� {@link ReportCard#studentId}
     * @param student - ������� �������� ����������� �������
     */
	public void setStudent(int studentId){
		this.studentId = studentId;
	}
	/**
     * ������� ��������� �������� ���� {@link ReportCard#grades}
     * @return ���������� ������ �� ������� ��������
     */
	public ArrayList<Grade> getGrades(){
		return grades;
	}
	/**
     * ������� ��������� �������� ���� {@link ReportCard#grades}
     * @param grades - ������ �� ������� ��������
     */
	public void setGrades(ArrayList<Grade> grades){
		this.grades = grades;
	}
	
	/**
     * ������� ��������������� �������� ������ {@link ReportCard}
     * @param o - ������ � ������� ������������ ������� {@link ReportCard}
     * @return ������ true ���� ��� ���� � �� �� �������
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
     * ������� ��������� ��� ���� ������ {@link ReportCard}
     * @return ���������� ��� ��� �������
     */
	@Override
	public int hashCode() {
		return studentId;
	}
	/**
     * ������� �������������� ������ {@link ReportCard} � ������ 
     * @return ���������� ��������� ������������� �������
     */
	@Override
    public String toString() {
        return studentId + " - �������";
    }
}