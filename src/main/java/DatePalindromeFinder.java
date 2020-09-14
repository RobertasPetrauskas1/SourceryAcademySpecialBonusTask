/**
 * Class that finds all date palindromes between two input years.
 *
 * This project was made as a special task to get bonus points for the Sorcery Academy for Developers entry exam.
 * @Author: Robertas Petrauskas
 * @Date: 2020-09-14
 */

import Exceptions.DateLessThanZeroException;
import Exceptions.ToDateLessThanFromDateException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatePalindromeFinder {
    public static void main(String[] args){
        printBonusDatesBetween(1999, 2020);
        printBonusDatesBetween(2001, 2001);
        printBonusDatesBetween(2003, 2003);
        try{
            printBonusDatesBetween(-1999, -2020);
        }
        catch (DateLessThanZeroException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }

        try{
            printBonusDatesBetween(2020, 1999);
        }
        catch (ToDateLessThanFromDateException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }
    }
    static void printBonusDatesBetween(int fromYear, int toYear){
        System.out.println(String.format("Bonus dates between %d and %d", fromYear, toYear));
        validateDates(fromYear, toYear);
        List<String> dates = getAllDatesBetween(fromYear, toYear);
        List<String> palindromes = getPalindromes(dates);

        if(palindromes.isEmpty()){
            System.out.println("No bonus dates found");
        } else{
            palindromes.forEach(System.out::println);
        }
        System.out.println();
    }
    // Method for all date validations
    static void validateDates(int fromYear, int toYear){
        if(fromYear < 0 || toYear < 0) {
            throw new DateLessThanZeroException("Dates can't be less than 0");
        } else if(fromYear > toYear){
            throw new ToDateLessThanFromDateException("toDate can't be less than fromDate");
        }
    }
    // Returns a List of type String that contains all dates between fromYear and toYear in the format of yyyy-mm-dd
    static List<String> getAllDatesBetween(int fromYear, int toYear){
        LocalDate startDate = LocalDate.of(fromYear, 1, 1);
        LocalDate endDate;
        // Check if fromYear is equal to toYear to determine end date
        if(fromYear == toYear){
            endDate = LocalDate.of(toYear, 12, 31);
        } else {
            endDate = LocalDate.of(toYear, 1, 1);
        }
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        List<String> dates = Stream.iterate(startDate, date -> date.plusDays(1))
                .map(LocalDate::toString)
                .limit(numOfDays)
                .collect(Collectors.toList());

        return dates;
    }
    // Filters the list of dates to only contain palindromes
    static List<String> getPalindromes(List<String> dates){
        List<String> palindromes = dates.stream()
                .filter(date -> {
                    date = date.replace("-", "");
                    return date.equals(new StringBuilder(date).reverse().toString());
                })
                .collect(Collectors.toList());

        return palindromes;
    }
}
