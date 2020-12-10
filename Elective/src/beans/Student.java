package beans;

import java.util.ArrayList;
/**
 * ����� Javabean ����������� {@link Person} 
 * @author ��������
 * @version 1.0
 */
public class Student extends Person {
	/** ����� ���������� ��������� */
	protected ArrayList<Integer> coursesId = new ArrayList<Integer>();
	/**
    * ������� ��������� �������� ���� {@link Student#coursesId}
    * @return ���������� ������ ������ ���������� ���������
    */
	public ArrayList<Integer> getCoursesId()
	{
		return coursesId;
	}
	/**
     * ������� ��������� �������� ���� {@link Student#coursesId}
     * @param courses - ������ ������ ���������� ���������
     */
	public void setCoursesId(ArrayList<Integer> coursesId)
	{
		this.coursesId = coursesId;
	}
	/**
     * ������� ��������������� �������� ������ {@link Student}
     * @param o - ������ � ������� ������������ ������� {@link Student}
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

        Student that = (Student) o;

        return this.id != that.id ? false : true;
    }
	/**
     * ������� ��������� ��� ���� ������ {@link Student}
     * @return ���������� ��� ��� �������
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * ������� �������������� ������ {@link Student} � ������ 
     * @return ���������� ��������� ������������� ��������
     */
	@Override
    public String toString() {
        return lastName + " " + name + " " + age;
    }
}