package studentAttendanceProjectFinal;

import java.util.*;

public class FinalProjectJava {

	
    interface Person {
        int getId();
        String getName();
    }

    interface AttendanceHandler {
        void markAttendance(String date, boolean isPresent);
        void updateAttendance(String date, boolean isPresent);
        void deleteAttendance(String date);
        void viewAttendance();
    }

    abstract static class AbstractPerson implements Person, AttendanceHandler {
        protected int id;
        protected String name;
        protected Map<String, Boolean> attendance;

        public AbstractPerson(int id, String name) {
            this.id = id;
            this.name = name;
            this.attendance = new HashMap<>();
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void markAttendance(String date, boolean isPresent) {
            attendance.put(date, isPresent);
            System.out.println("\n\tAttendance marked successfully for: " + name + " on " + date);
        }

        @Override
        public void updateAttendance(String date, boolean isPresent) {
            if (attendance.containsKey(date)) {
                attendance.put(date, isPresent);
                System.out.println("\n\tAttendance updated successfully for: " + name + " on " + date);
            } else {
                System.out.println("\n\tNo attendance record found for this date.");
            }
        }

        @Override
        public void deleteAttendance(String date) {
            if (date != null) {
            	attendance.remove(date);
                System.out.println("\n\tAttendance deleted successfully for: " + name + " on " + date);
            } else {
                System.out.println("\n\tNo attendance record found for this date.");
            }
        }

        @Override
        public void viewAttendance() {
            System.out.println("\n\tAttendance Records for " + name + ":");
            System.out.println("\t----------------------------------------");
            if (attendance.isEmpty()) {
                System.out.println("\tNo attendance records available.");
            } else {
                attendance.forEach((date, isPresent) -> {
                    System.out.println("\tDate: " + date + "\t| Status: " + (isPresent ? "Present" : "Absent"));
                });
            }
        }
    }

    static class Student extends AbstractPerson {
        public Student(int id, String name) {
            super(id, name);	
        }
    }

    static class Staff extends AbstractPerson {
        public Staff(int id, String name) {
            super(id, name);
        }
    }

    // This class contains the all data of Student and Staff which we want to display--
    static class AttendanceManagementSystem {
        private List<Student> students;
        private List<Staff> staffMembers;
        private Scanner scanner;

        public AttendanceManagementSystem() {
            students = new ArrayList<>();
            staffMembers = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void addStudent(int id, String name) {
            students.add(new Student(id, name));
            System.out.println("\n\tStudent added successfully! [ID: " + id + ", Name: " + name + "]");
        }

        public void addStaff(int id, String name) {
            staffMembers.add(new Staff(id, name));
            System.out.println("\n\tStaff added successfully! [ID: " + id + ", Name: " + name + "]");
        }

        public Student findStudentById(int id) {
            return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
        }

        public Staff findStaffById(int id) {
            return staffMembers.stream().filter(staff -> staff.getId() == id).findFirst().orElse(null);
        }

        public void displayMenu() {
            while (true) {
                System.out.println("\n==========================================================================");
                System.out.println("\tSchool Attendance Management System");
                System.out.println("==========================================================================");
                System.out.println("\t1. Manage Students");
                System.out.println("\t2. Manage Staff");
                System.out.println("\t3. View Total Students and Staff");
                System.out.println("\t4. Exit");
                System.out.println("--------------------------------------------------------------------------");                					                                                                
                System.out.print("\tEnter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageStudentMenu();
                        break;
                    case 2:
                        manageStaffMenu();
                        break;
                    case 3:
                        displayTotalCount();
                        break;
                    case 4:
                        System.out.println("\n\tExiting the system. Goodbye!");
                        return;
                    default:
                        System.out.println("\n\tInvalid choice. Please try again.");
                }
            }
        }

        private void manageStudentMenu() {
            while (true) {
                System.out.println("\n--------------------------------------------------------------------------");
                System.out.println("\tStudent Management Menu");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("\t1. Add Student");
                System.out.println("\t2. Mark Attendance");
                System.out.println("\t3. Update Attendance");
                System.out.println("\t4. Delete Attendance");
                System.out.println("\t5. View Attendance");
                System.out.println("\t6. Back to Main Menu");
                System.out.println("--------------------------------------------------------------------------");
                System.out.print("\tEnter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("\tEnter Student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\tEnter Student Name: ");
                        String studentName = scanner.nextLine();
                        addStudent(studentId, studentName);
                        break;
                    case 2:
                        handleAttendance("student", true);
                        break;
                    case 3:
                        handleAttendance("student", false);
                        break;
                    case 4:
                        deleteAttendance("student");
                        break;
                    case 5:
                        viewAttendance("student");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("\n\tInvalid choice. Please try again.");
                }
            }
        }

        private void manageStaffMenu() {
            while (true) {
                System.out.println("\n--------------------------------------------------------------------------");
                System.out.println("\tStaff Management Menu");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("\t1. Add Staff");
                System.out.println("\t2. Mark Attendance");
                System.out.println("\t3. Update Attendance");
                System.out.println("\t4. Delete Attendance");
                System.out.println("\t5. View Attendance");
                System.out.println("\t6. Back to Main Menu");
                System.out.println("--------------------------------------------------------------------------");
                System.out.print("\tEnter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("\tEnter Staff ID: ");
                        int staffId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\tEnter Staff Name: ");
                        String staffName = scanner.nextLine();
                        addStaff(staffId, staffName);
                        break;
                    case 2:
                        handleAttendance("staff", true);
                        break;
                    case 3:
                        handleAttendance("staff", false);
                        break;
                    case 4:
                        deleteAttendance("staff");
                        break;
                    case 5:
                        viewAttendance("staff");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("\n\tInvalid choice. Please try again.");
                }
            }
        }

        private void handleAttendance(String type, boolean isNewEntry) {
            System.out.print("\tEnter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            AbstractPerson person = type.equals("student") ? findStudentById(id) : findStaffById(id);

            if (person == null) {
                System.out.println("\n\tNo record found with ID: " + id);
                return;
            }

            System.out.print("\tEnter Date (yyyy-mm-dd): ");
            String date = scanner.nextLine();
            System.out.print("\tPresent (true/false): ");
            boolean isPresent = scanner.nextBoolean();

            if (isNewEntry) {
                person.markAttendance(date, isPresent);
            } else {
                person.updateAttendance(date, isPresent);
            }
        }

        private void deleteAttendance(String type) {
            System.out.print("\tEnter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            AbstractPerson person = type.equals("student") ? findStudentById(id) : findStaffById(id);

            if (person == null) {
                System.out.println("\n\tNo record found with ID: " + id);
                return;
            }

            System.out.print("\tEnter Date (yyyy-mm-dd): ");
            String date = scanner.nextLine();
            person.deleteAttendance(date);
        }

        private void viewAttendance(String type) {
            System.out.print("\tEnter ID: ");
            int id = scanner.nextInt();
            AbstractPerson person = type.equals("student") ? findStudentById(id) : findStaffById(id);

            if (person == null) {
                System.out.println("\n\tNo record found with ID: " + id);
            } else {
                person.viewAttendance();
            }
        }

        private void displayTotalCount() {
            System.out.println("\n==============================================================================");
            System.out.println("\tTotal Records");
            System.out.println("==============================================================================");
            System.out.println("\tTotal Students: " + students.size());
            System.out.println("\tTotal Staff: " + staffMembers.size());
        }
    }

    public static void main(String[] args) {
        AttendanceManagementSystem system = new AttendanceManagementSystem();
        system.displayMenu();
    }
}
