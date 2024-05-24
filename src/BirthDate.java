public class BirthDate {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final int day;
    private final int month;
    private final int year;

    public BirthDate(String firstName, String lastName, String phoneNumber, String date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.year = Integer.parseInt(date.split("-")[0]);
        this.month = Integer.parseInt(date.split("-")[1]);
        this.day = Integer.parseInt(date.split("-")[2]);
    }

    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getPhoneNumber(){ return phoneNumber; }
    public int getDay(){ return day; }
    public int getMonth(){ return month; }
    public int getYear(){ return year; }
    public String getBirthDate(){
        return Formatter.FormatDate(year, month, day);
    }

    @Override
    public String toString() {
        return getFirstName() + ", " + getLastName() + ", " + getPhoneNumber() + ", " + getBirthDate();
    }
}
