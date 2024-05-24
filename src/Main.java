import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<BirthDate> birthDates = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        readFromFile();

        boolean flag = true;

        while (flag) {
            System.out.println("Виберіть опцію:");
            System.out.println("1. Показати всі дні народження");
            System.out.println("2. Показати всі дні народження за ім'ям");
            System.out.println("3. Показати всі дні народження за прізвищем");
            System.out.println("4. Показати всі дні народження за місяцем");
            System.out.println("5. Ввести новий запис про день народження ");
            System.out.println("6. Видалити всі дні народження за ім'ям");
            System.out.println("7. Видалити всі дні народження за прізвищем");
            System.out.println("8. Оновити запис про дні народження за прізвищем");
            System.out.println("9. Завантажити всі дні народження з файлу");
            System.out.println("10. Зберегти всі дні народження у файл");
            System.out.println("11. Вихід");

            System.out.print("Введіть операцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    getAll();
                    break;
                case 2:
                    getByFirstName(scanner);
                    break;
                case 3:
                    getByLastName(scanner);
                    break;
                case 4:
                    getByMonth(scanner);
                    break;
                case 5:
                    Post(scanner);
                    break;
                case 6:
                    DeleteByFirstName(scanner);
                    break;
                case 7:
                    DeleteByLastName(scanner);
                    break;
                case 8:
                    PutByLastName(scanner);
                    break;
                case 9:
                    readFromFile();
                    break;
                case 10:
                    writeToFile();
                    break;
                case 11:
                    writeToFile();
                    flag = false;
                    break;

                default:
                    System.out.println("Некоректний вибір. Спробуйте знову.");
            }
        }
    }

    private static void writeToFile() {
        String filename = "BirthDates.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (BirthDate birthDate : birthDates) {
                writer.write(birthDate.toString());
                writer.newLine();
            }

            writer.close();
            System.out.println("Дані про дні народження збережені у файл " + filename);
        } catch (IOException e) {
            System.err.println("Виникла помилка при записі даних про дні народження у файл " + filename);
        }
    }

    private static void readFromFile() {
        birthDates.clear();
        String filename = "BirthDates.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String phoneNumber = parts[2];
                    String date = parts[3];

                    BirthDate birthDate = new BirthDate(firstName, lastName, phoneNumber, date);
                    birthDates.add(birthDate);
                }
            }
            System.out.println("Дані про дні народження було завантажено з файлу");
        } catch (IOException e) {
            System.err.println("Виникла помилка при зчитувані даних про дні народження з файлу " + filename);
        }
    }

    private static void getAll() {
        System.out.println("Дані про дні народження:");

        boolean found = false;

        for (BirthDate birthDate : birthDates) {
            found = true;
            System.out.println(birthDate.toString());
        }
        if (!found) {
            System.out.println("Немає записів про дні народження.");
        }
    }

    private static void getByFirstName(Scanner scanner) {
        System.out.println("Введіть ім'я:");
        String firstName = scanner.nextLine();

        boolean found = false;

        for (BirthDate birthDate : birthDates) {
            if (birthDate.getFirstName().equals(firstName)) {
                found = true;
                System.out.println(birthDate);
            }
        }

        if (!found) {
            System.out.println("Немає записів про дні народження за вказаним ім'ям.");
        }
    }

    private static void getByLastName(Scanner scanner) {
        System.out.println("Введіть прізвище:");
        String lastName = scanner.nextLine();

        boolean found = false;

        for (BirthDate birthDate : birthDates) {
            if (birthDate.getLastName().equals(lastName)) {
                found = true;
                System.out.println(birthDate);
            }
        }

        if (!found) {
            System.out.println("Немає записів про дні народження за вказаним прізвищем.");
        }
    }

    private static void getByMonth(Scanner scanner) {
        System.out.println("Введіть місяць:");
        int month = scanner.nextInt();

        boolean found = false;

        for (BirthDate birthDate : birthDates) {
            if (birthDate.getMonth() == month) {
                found = true;
                System.out.println(birthDate);
            }
        }

        if (!found) {
            System.out.println("Немає записів про дні народження у заданому місяці.");
        }
    }

    private static void Post(Scanner scanner) {
        System.out.println("Введіть ім'я:");
        String firstName = scanner.nextLine();

        System.out.println("Введіть прізвище:");
        String lastName = scanner.nextLine();

        String phoneNumber;
        String date;

        do {
            System.out.println("Введіть номер телефону");
            phoneNumber = scanner.nextLine();
        } while (!Formatter.isValidPhoneNumber(phoneNumber));

        do {
            System.out.println("Введіть дату (формат: рррр-мм-дд):");
            date = scanner.nextLine();
            if (Formatter.isValidDate(date)) {
                try{
                    DateTimeFormatter validator = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateValidation = LocalDate.parse( date , validator);
                    int diff = Period.between(dateValidation, LocalDate.now()).getYears();
                    if ( 0 < diff && diff < 100) {
                        break;
                    } else {
                        System.out.println("Дата народження мусить бути не пізніше поточного часу та не більше 100 років тому");
                    }
                }catch (DateTimeParseException ex){
                    System.err.println("Date is in a wrong format");
                }
            }
        } while (true);

        BirthDate birthDate = new BirthDate(firstName, lastName, phoneNumber, date);
        birthDates.add(birthDate);
    }

    private static void DeleteByFirstName(Scanner scanner) {
        System.out.println("Введіть ім'я, за яким потрібно видалити дні народження:");
        String firstName = scanner.nextLine();

        boolean found = false;

        for(int i = 0; i < birthDates.size();){
            if (birthDates.get(i).getFirstName().equals(firstName)) {
                birthDates.remove(birthDates.get(i));
                found = true;
            }
            else{
                i++;
            }
        }

        if (!found) {
            System.out.println("Не було знайдено днів народження з заданим ім'ям");
        }
    }

    private static void DeleteByLastName(Scanner scanner) {
        System.out.println("Введіть прізвище, за яким потрібно видалити дні народження:");
        String lastName = scanner.nextLine();

        boolean found = false;

        for(int i = 0; i < birthDates.size();){
            if (birthDates.get(i).getLastName().equals(lastName)) {
                birthDates.remove(birthDates.get(i));
                found = true;
            }
            else{
                i++;
            }
        }

        if (!found) {
            System.out.println("Не було знайдено днів народження з заданим прізвищем");
        }
    }

    private static void PutByLastName(Scanner scanner) {
        System.out.println("Введіть прізвище, за яким проводити пошук:");
        String lastNameToChange = scanner.nextLine();

        System.out.println("Введіть ім'я:");
        String firstName = scanner.nextLine();

        System.out.println("Введіть прізвище:");
        String lastName = scanner.nextLine();

        String phoneNumber;
        String date;

        do {
            System.out.println("Введіть номер телефону");
            phoneNumber = scanner.nextLine();
        } while (!Formatter.isValidPhoneNumber(phoneNumber));

        do {
            System.out.println("Введіть дату (формат: рррр-мм-дд):");
            date = scanner.nextLine();
            if (Formatter.isValidDate(date)) {
                try{
                    DateTimeFormatter validator = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateValidation = LocalDate.parse( date , validator);
                    int diff = Period.between(dateValidation, LocalDate.now()).getYears();
                    if ( 0 < diff && diff < 100) {
                        break;
                    } else {
                        System.out.println("Дата народження мусить бути не пізніше поточного часу та не більше 100 років тому");
                    }
                }catch (DateTimeParseException ex){
                    System.err.println("Date is in a wrong format");
                }
            }
        } while (true);

        BirthDate birthDateToPut = new BirthDate(firstName, lastName, phoneNumber, date);

        boolean found = false;

        for(int i = 0; i < birthDates.size(); i++){
            if (birthDates.get(i).getLastName().equals(lastNameToChange)) {
                birthDates.remove(birthDates.get(i));
                birthDates.addFirst(birthDateToPut);
                found = true;
            }
            else{
                i++;
            }
        }

        if (!found) {
            System.out.println("Не було знайдено дні народження з заданим прізвищем");
        }
    }
}