public class SeasonFinder {
    public static void main(String[] args) {
        int month = 8; 
        String season;

        switch (month) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "Autumn";
                break;
            default:
                season = "Invalid month. Please enter a value between 1 and 12.";
        }

        System.out.println("Month: " + month);
        System.out.println("Season: " + season);
    }
}