import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double score;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getGrade() {
        if (score >= 90) return "A+";
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }
}

public class StudentGradeTracker {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("==================================");

        int numberOfStudents = readInt("Enter number of students: ", 1, 1000);

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\\nStudent " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();

            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter student name: ");
                name = sc.nextLine().trim();
            }

            double score = readDouble("Enter score (0-100): ", 0, 100);
            students.add(new Student(name, score));
        }

        displayReport();
        sc.close();
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a score between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid score. Example: 85 or 85.5");
            }
        }
    }

    private static void displayReport() {
        double total = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        System.out.println("\\n================ STUDENT REPORT ================");
        System.out.printf("%-5s %-25s %-10s %-8s%n", "No.", "Student Name", "Score", "Grade");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%-5d %-25s %-10.2f %-8s%n",
                    i + 1, student.getName(), student.getScore(), student.getGrade());

            total += student.getScore();

            if (student.getScore() > highest.getScore()) {
                highest = student;
            }
            if (student.getScore() < lowest.getScore()) {
                lowest = student;
            }
        }

        double average = total / students.size();

        System.out.println("------------------------------------------------");
        System.out.printf("Average score : %.2f%n", average);
        System.out.printf("Highest score : %s (%.2f)%n", highest.getName(), highest.getScore());
        System.out.printf("Lowest score  : %s (%.2f)%n", lowest.getName(), lowest.getScore());
        System.out.println("================================================");
    }
}
