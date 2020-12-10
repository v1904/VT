package beans;

import java.util.ArrayList;

/**
 * ����� Javabean ���������� �� ���� 
 * @author ��������
 * @version 1.0
 */
public class Course {
	/** ���������� ������������� ��� ������� ����� */
	protected int id;
	/** �������� ����� */
	protected String name;
	/** ����� ���������� ������� */
	protected Schedule schedule;
	/** ������������� ������� ���� */
	protected int teacherId;
	/** �������� ���������� ������� */
	protected ArrayList<Integer> studentsId = new ArrayList<Integer>();
	/**
     * ������� ��������� �������� ���� {@link Course#id}
     * @return ���������� id �����
     */
	public int getId(){
		return id;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#id}
     * @param id - id �����
     */
	public void setId(int id){
		this.id = id;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#name}
     * @return ���������� �������� �����
     */
	public String getName(){
		return name;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#name}
     * @param name - �������� �����
     */
	public void setName(String name){
		this.name = name;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#schedule}
     * @return ���������� ����� ���������� �������
     */
	public Schedule getSchedule(){
		return schedule;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#schedule}
     * @param schedule - ����� ���������� �������
     */
	public void setSchedule(Schedule schedule){
		this.schedule = schedule;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#teacherId}
     * @return ���������� ������������� �������� �������
     */
	public int getTeacherId(){
		return teacherId;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#studentsId}
     * @return ���������� �������� ���������� �� ����
     */
	public ArrayList<Integer> getStudentsId(){
		return studentsId;
	}
	/**
     * ������� ��������� �������� ���� {@link Course#studentsId}
     * @param students - �������� ���������� �� ����
     */
	public void setStudentsId(ArrayList<Integer> studentsId){
		this.studentsId = studentsId;
	}
	
	/**
     * ������� ��������� �������� ���� {@link Course#teacherId}
     * @param teacher - ������������� ������� �������
     */
	public void setTeacherId(int teacherId){
		this.teacherId = teacherId;
	}
	/**
     * ������� ��������������� �������� ������ {@link Course}
     * @param o - ������ � ������� ������������ ������� {@link Course}
     * @return ������ true ���� ��� ���� � ��� �� ����
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
     * ������� ��������� ��� ���� ������ {@link Course}
     * @return ���������� ��� ��� �����
     */
	@Override
	public int hashCode() {
		return id;
	}
	/**
     * ������� �������������� ������ {@link Course} � ������ 
     * @return ���������� ��������� ������������� �����
     */
	@Override
    public String toString() {
        return id + " " + name;
    }
}