package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ÂïçèçôéêÞ ôÜîç ãéá ôéò çìåñïìçíßåò ôùí äçìïðñáóéþí êáé ôéò ðñÜîåéò ôùí çìåñïìçíéþí.
 * ¼ôáí äçìéïõñãåßôå ìðïñåßôå íá ïñßóåôå ôçí áñ÷éêÞ çìåñïìçíßá. Óôç óõíÝ÷åéá, ìå ôç ìÝèïäï
 * getDate() ìðïñåßôå íá ðÜñåôå ôç ôñÝ÷ïõóá çìåñïìçíßá, åíþ ìå ôç ìÝèïäï nextDay() ìðïñåßôå íá 
 * ðñï÷ùñÞóåôå ôï çìåñïëüãéï êáôÜ ìßá çìÝñá.
 * 
 * Ãéá íá ôç ÷ñçóéìïðïéÞóåôå óôï ðñüãñáììÜ óáò áñêåß íá äçìéïõñãÞóåôå Ýíá áíôéêåßìåíï
 * áõôÞò ôçò ôÜîçò. Ð.÷. ãéá íá äçìéïõñãÞóåôå Ýíá íÝï çìåñïëüãéï ìå çìåñïìçíßá 1 Öåâñïõáñßïõ 2012
 * áñêåß íá êÜíåôå ôï áêüëïõèï:
 * AuctionCalendar.init(1, 2, 2012);
 * 
 * Óôç óõíÝ÷åéá, ãéá íá ÷ñçóéìïðïéÞóåôå ôçí ôñÝ÷ïõóá çìåñïìçíßá áðü ôï çìåñïëüãéï:
 * Date now = AuctionCalendar.getDate();
 * 
 * Ãéá íá ðñï÷ùñÞóåôå óôçí åðüìåíç çìÝñá (2 Öåâñïõáñßïõ ôïõ 2012):
 * AuctionCalendar.nextDay();
 * 
 * Ãéá íá ðñáãìáôïðïéÞóåôå Ýëåã÷ï ãéá ôï áí ìéá çìåñïìçíßá (Ýóôù myDate) åßíáé ìåôÜ áðü ôçí ôñÝ÷ïõóá
 * çìåñïìçíßá:
 * 
 * Date myDate = someObject.getAuctionDate();
 * if( AuctionCalendar.isCurrentDateAfter( myDate ) ) {
 * 		System.out.println("Ç ôñÝ÷ïõóá çìåñïìçíßá åßíáé ìåôÜ áðü ôçí çìåñïìçíßá ðïõ ïñßóáôå");
 * }
 * 
 * Ãéá íá ðñáãìáôïðïéÞóåôå Ýëåã÷ï ãéá ôï áí ç ôñÝ÷ïõóá çìåñïìçíßá åßíáé n çìÝñåò ìåôÜ
 * áðü ôçí ìßá çìåñïìçíßá (Ýóôù myDate) :
 * 
 * Date myDate = someObject.getAuctionDate();
 * if( AuctionCalendar.isCurrentDateNDaysAfter( myDate, 10 ) ) {
 * 		System.out.println("Ç ôñÝ÷ïõóá çìåñïìçíßá åßíáé ôïõëÜ÷éóôïí 10 çìÝñåò ìåôÜ áðü ôçí çìåñïìçíßá ðïõ ïñßóáôå");
 * }
 */
public class AuctionCalendar {
	
	private static final DateFormat format = new SimpleDateFormat("MMM d, yyyy");
	
	private static Calendar currentDate;
	
	public static void init(int dayOfMonth, int month, int year) {
		currentDate = GregorianCalendar.getInstance();
		currentDate.set(year, month - 1, dayOfMonth);
	}
	
	public static void nextDay() {
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("Η νέα ημερομηνία είναι: " + format.format(currentDate.getTime()));
	}
	
	public static Date getDate() {
		return currentDate.getTime();
	}
	
	public static boolean isCurrentDateAfter(Date t) {
		return currentDate.after(t);
	}
	
	public static boolean isCurrentDateBefore(Date t) {
		return currentDate.before(t);
	}
	
	public static boolean isCurrentDateNDaysAfter(Date t, int days) {
		Calendar startDate = GregorianCalendar.getInstance();
		startDate.setTime(t);
		startDate.add(Calendar.DAY_OF_MONTH, days);
		return currentDate.after(startDate);
	}

}
