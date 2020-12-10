package beans;

/**
 * Класс хранящий время проведения занятий 
 * @author Владимир
 * @version 1.0
 */
public class Schedule
{
	public enum daysOfWeek { MONDAY, TUESDAY, WEDNESDAY, 
		THURSDAY, FRIDAY, SATURDAY, SUNDAY }
	/**
	 * Класс хранящий день и время проведения занятий 
	 * @author Владимир
	 * @version 1.0
	 */
	public class Day
	{
		private boolean checked; 
		private daysOfWeek day;
		private int hours;
		private int minutes;
		public Day(boolean checked, daysOfWeek day, int hours, int minutes)
		{
			this.checked = checked;
			this.day = day;
			this.hours = hours;
			this.minutes = minutes;
		}
		
		public daysOfWeek getDay()
		{
			return day;
		}
		public void setDay(daysOfWeek day)
		{
			this.day = day;
		}
		
		public Integer getHours()
		{
			return hours;
		}
		public void setHours(int hours)
		{
			this.hours = hours;
		}
		
		public Integer getMinutes()
		{
			return minutes;
		}
		public void setMinutes(int minutes)
		{
			this.minutes = minutes;
		}
		
		public boolean isChecked()
		{
			return checked;
		}
		public void setChecked(boolean checked)
		{
			this.checked = checked;
		}
		
		@Override
	    public String toString() {
	        return day.name() + " " + hours + " " + minutes;
	    }
	}
	/** Список дней проведения факультатива */
	private Day[] days = new Day[7];
	/**
     * Функция получения значения поля {@link Schedule#days}
     * @return возвращает списка дней проведения занятий
     */
	public Day[] getDays(){
		return days;
	}
	/**
     * Функция установки значения поля {@link Schedule#days}
     * @param days - список дней проведения занятий
     */
	public void setDays(Day[] days){
		this.days = days;
	}
	
	@Override
    public String toString() {
		String res = "";
		for(Day d : days)
			if(d.isChecked())
				res += ", " + d;
        return res.length() != 0 ? res.substring(2) : "";
    }
}