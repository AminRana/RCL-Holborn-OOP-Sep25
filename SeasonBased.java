package myPackage;

public class SeasonBased {
    public static void main(String[] args) {

        String month = "March"; 
        String season;

        switch (month) {
            case "December":
            case "January":
            case "February":
                season = "Winter";
                break;

            case "March":
            case "April":
            case "May":
                season = "Spring";
                break;

            case "June":
            case "July":
            case "August":
                season = "Summer";
                break;

            case "September":
            case "October":
            case "November":
                season = "Autumn";
                break;

            default:
                season = "Invalid month! Please enter a number between 1 and 12.";
        }

        System.out.println("Month: " + month);
        System.out.println("Season: " + season);
    }
}
