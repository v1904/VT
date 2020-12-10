package beans;

/**
 * ������� ����� ��� {@link Student} � {@link Teacher}
 * @author ��������
 * @version 1.0
 */
public abstract class Person {
	/** ���������� ������������� ��� ������� �������� */
	protected int id = 0;
	/** ��� */
	protected String name = "";
	/** ������� */
	protected String lastName = "";
	/** ������� */
	protected int age = 0;
	
	/**
     * ������� ��������� �������� ���� {@link Person#id}
     * @return ���������� id ��������
     */
	public int getId(){
		return id;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#id}
     * @param id - id ��������
     */
	public void setId(int id){
		this.id = id;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#name}
     * @return ���������� ��� ��������
     */
	public String getName(){
		return name;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#name}
     * @param name - ��� ��������
     */
	public void setName(String name){
		this.name = name;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#lastName}
     * @return ���������� ������� ��������
     */
	public String getLastName(){
		return lastName;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#lastName}
     * @param lastName - ������� ��������
     */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#age}
     * @return ���������� ������� ��������
     */
	public int getAge(){
		return age;
	}
	/**
     * ������� ��������� �������� ���� {@link Person#age}
     * @param age - ������� ��������
     */
	public void setAge(int age){
		this.age = age;
	}
}