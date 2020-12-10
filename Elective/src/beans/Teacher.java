package beans;

import java.util.ArrayList;

/**
 * ����� Javabean ����������� {@link Person} 
 * @author ��������
 * @version 1.0
 */
public class Teacher extends Person {
	/** ����� ������� ����� ������������� */
	protected ArrayList<Integer> coursesId = new ArrayList<Integer>();
	
	/**
     * ������� ��������� �������� ���� {@link Teacher#coursesId}
     * @return ���������� ������ ������ ������� ����� �������������
     */
	public ArrayList<Integer> getCoursesId()
	{
		return coursesId;
	}
	/**
     * ������� ��������� �������� ���� {@link Teacher#coursesId}
     * @param coursesId - ������ ������ ������� ����� �������������
     */
	public void setCoursesId(ArrayList<Integer> coursesId)
	{
		this.coursesId = coursesId;
	}
	/**
     * ������� ��������������� �������� ������ {@link Teacher}
     * @param o - ������ � ������� ������������ ������� {@link Teacher}
     * @return ������ true ���� ��� ���� � ��� �� �������
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
     * ������� ��������� ��� ���� ������ {@link Teacher}
     * @return ���������� ��� ��� �������
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * ������� �������������� ������ {@link Teacher} � ������ 
     * @return ���������� ��������� ������������� �������
     */
	@Override
    public String toString() {
        return lastName + " " + name + " " + age;
    }
}