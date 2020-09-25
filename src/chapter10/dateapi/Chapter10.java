package chapter10.dateapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Chapter10 {

	public static void main(String[] args) {
		// LocalDate -> represents a date format, without time and timezone
		// LocalDateTime -> represents a date/time format
		// LocalTime -> represents a time format
		// ZoneDateTime -> represents a date/time format with timezone
		
		LocalDate nextMonth = LocalDate.now().plusMonths(1); // plusYears, plusMonths, plusWeeks, plusDays
		System.out.println(nextMonth);
		
		LocalDate lastMonth = LocalDate.now().minusMonths(1); // minusYears, minusMonths, minusWeeks, minusDays
		System.out.println(lastMonth);
		
		// creating an specific local date/time
		LocalDateTime todayAtNoon = LocalDate.now().atTime(12, 0);
		System.out.println(todayAtNoon);
		
		LocalTime now = LocalTime.now();
		LocalDate today = LocalDate.now();
		LocalDateTime todayNow = today.atTime(now);
		System.out.println(todayNow);
		
		// defining a timezone
		ZonedDateTime dateTimeWithTimezone = todayNow.atZone(ZoneId.of("America/Sao_Paulo"));
		System.out.println(dateTimeWithTimezone);
		
		// converting formats
		LocalDateTime dateTimeWithoutTimezone = dateTimeWithTimezone.toLocalDateTime();
		System.out.println(dateTimeWithoutTimezone);
		
		LocalDate justDate = dateTimeWithoutTimezone.toLocalDate();
		System.out.println(justDate);

		LocalTime justTime = dateTimeWithoutTimezone.toLocalTime();
		System.out.println(justTime);
		
		// creating new instances
		LocalDate date = LocalDate.of(2020, 01, 01);
		LocalDateTime dateTime = LocalDateTime.of(2020, 01, 01, 10, 30);

		// setter methods
		LocalDate pastDate = LocalDate.now().withYear(1986); // withYear, withMonth, withDayOfYear, withDayOfMonth

		// getter methods
		int year = pastDate.getYear(); // getYear, getMonth, getDayOfYear, getDayOfMonth...
		
		// comparing
		LocalDate tomorrow = today.plusDays(1);
		System.out.println(today.isBefore(tomorrow)); // true
		System.out.println(today.isAfter(tomorrow)); // false
		System.out.println(today.isEqual(tomorrow)); // false
		
		// comparing with timezone
		ZonedDateTime tokyo = ZonedDateTime.of(2020, 1, 1, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2020, 1, 1, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		tokyo = tokyo.plusHours(12);
		
		System.out.println(tokyo.isEqual(saoPaulo)); // true
		
		// formatting
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		// converting from LocalDate to String
		String todayFormat = today.format(dateTimeFormatter);
		System.out.println(todayFormat);
		
		// converting from String to LocalDate
		LocalDate todayParse = LocalDate.parse(todayFormat, dateTimeFormatter);
		System.out.println(todayParse);
		
		// duration and interval
		System.out.println(ChronoUnit.DAYS.between(today, nextMonth));
		
		Period period = Period.between(today, nextMonth);
		System.out.println(period.getDays());
	}
}
