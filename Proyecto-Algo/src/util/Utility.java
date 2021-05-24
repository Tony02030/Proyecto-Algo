/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

//import domain.Course;
//import domain.Student;
import domain.Career;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import domain.SingleLinkedList;
import domain.CircularLinkedList;
import domain.DoublyLinkedList;
import domain.CircularDoublyLinkedList;
import domain.ListException;
import domain.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Utility {

    private static SingleLinkedList students = new SingleLinkedList();
    private static CircularLinkedList security = new CircularLinkedList();
    private static DoublyLinkedList careers = new DoublyLinkedList();
    private static CircularDoublyLinkedList courses = new CircularDoublyLinkedList();
    private static SingleLinkedList schedules = new SingleLinkedList();
    private static CircularDoublyLinkedList enrollment = new CircularDoublyLinkedList();

    public static SingleLinkedList getStudents() {
        return students;
    }

    public static CircularLinkedList getSecurity()  {
        try {
            if (!security.contains1("admin", "1234")) {
                security.add(new Security("admin", "1234"));
            }
        } catch (ListException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return security;
    }

    public static DoublyLinkedList getCareers() {
        return careers;
    }

    public static CircularDoublyLinkedList getCourses() {
        return courses;
    }

    public static SingleLinkedList getSchedules() {
        return schedules;
    }

    public static CircularDoublyLinkedList getEnrollment() {
        return enrollment;
    }

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return (int) Math.floor(Math.random() * bound);
    }

    public static int random1() {
        //return 1+random.nextInt(bound);
        return (int) Math.floor(Math.random() * (50 - 40 + 1) + 40);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("###,###.##")
                .format(value);
    }

    public static String hhmmss(long start, long end) {
        long milisegundos = end - start;
        long hora = milisegundos / 3600000;
        long restohora = milisegundos % 3600000;
        long minuto = restohora / 60000;
        long restominuto = restohora % 60000;
        long segundo = restominuto / 1000;
        long restosegundo = restominuto % 1000;

        return hora + ":" + minuto + ":" + segundo + "." + restosegundo;
    }

    public static boolean equals(Object data, Object element) {
        switch (instanceOf(data, element)) {
            case "integer":
                Integer x = (Integer) data;
                Integer y = (Integer) element;
                return x.equals(y);
            case "string":
                String s1 = (String) data;
                String s2 = (String) element;
                return s1.compareTo(s2) == 0;
            case "security":
                Security se = (Security) data;
                String str = (String) element;
                return se.getUser().equals(str) && se.getPassword().equals(str);
            case "career":
                Career cr1 = (Career) data;
                Integer cr2 = (Integer) element;
                return cr1.getId()==cr2;
        }
        return false;
    }

    public static boolean equals1(Object data, Object element, Object element2) {
        switch (instanceOf1(data, element, element2)) {
            case "integer":
                Integer x = (Integer) data;
                Integer y = (Integer) element;
                return x.equals(y);
            case "string":
                String s1 = (String) data;
                String s2 = (String) element;
                return s1.compareTo(s2) == 0;
//            case "student1":
//                Student st1 = (Student) data;
//                String st2 = (String) element;
//                return st1.getId().equals(st2) || st1.getName().equals(st2);
//            case "course1":
//                Course c1 = (Course) data;
//                String c2 = (String) element;
//                return c1.getId().equals(c2) || c1.getName().equals(c2);
//                case "employee":
//                Employee e1 = (Employee) data;
//                String e2 = (String) element;
//                return e1.getTitle().equals(e2);
//                case "job":
//                JobPosition j1 = (JobPosition) data;
//                String j2 = (String) element;
//                return j1.getDescription().equals(j2);
            case "security":
                Security se = (Security) data;
                String str = (String) element;
                String str2 = (String) element2;
                return se.getUser().equals(str) && se.getPassword().equals(str2);
        }
        return false;
    }

    private static String instanceOf(Object data, Object element) {
        if (data instanceof Integer && element instanceof Integer) {
            return "integer";
        }
        if (data instanceof String && element instanceof String) {
            return "string";
        }
//        if (data instanceof Student && element instanceof String) {
//            return "student1";
//        }
//         if (data instanceof Student && element instanceof Student) {
//            return "student";
//        }
//         if (data instanceof Course && element instanceof String) {
//            return "course1";
//        }
//         if (data instanceof Course && element instanceof Course) {
//            return "course";
//        }
//        if (data instanceof Employee && element instanceof String) {
//            return "employee";
//        }
        if (data instanceof Security && element instanceof String) {
            return "security";
        }
         if (data instanceof Career && element instanceof Integer) {
            return "career";
        }

        return "unknown";
    }

    public static String instanceOf1(Object data, Object element, Object element2) {
//        if (data instanceof Employee) {
//            return true;
//        }

//        if (data instanceof Student && element instanceof String) {
//            return "student1";
//        }
//         if (data instanceof Student && element instanceof Student) {
//            return "student";
//        }
//         if (data instanceof Course && element instanceof String) {
//            return "course1";
//        }
//         if (data instanceof Course && element instanceof Course) {
//            return "course";
//        }
        if (data instanceof Security && element instanceof String && element2 instanceof String) {
            return "security";
        }
        return "unknown";
    }

    public static boolean greaterT(Object data, Object element) {
        switch (instanceOf(data, element)) {
            case "integer":
                Integer x = (Integer) data;
                Integer y = (Integer) element;
                return x > y;
            case "string":
                String s1 = (String) data;
                String s2 = (String) element;
                return s1.compareTo(s2) > 0;
//            case "student":
//                Student st1 = (Student) data;
//                Student st2 = (Student) element;
//                return st1.getName().compareTo(st2.getName())>0;

        }
        return false;
    }

    public static boolean lessT(Object data, Object element) {
        switch (instanceOf(data, element)) {
            case "integer":
                Integer x = (Integer) data;
                Integer y = (Integer) element;
                return x < y;
            case "string":
                String s1 = (String) data;
                String s2 = (String) element;
                return s1.compareTo(s2) < 0;
//            case "student":
//                Student st1 = (Student) data;
//                Student st2 = (Student) element;
//                return st1.getName().compareTo(st2.getName())<0;
//             case "course":
//                Course c1 = (Course) data;
//                Course c2 = (Course) element;
//                return c1.getName().compareTo(c2.getName())<0;
        }
        return false;
    }

    public static String dateFormat(Date birthday) {
        return new SimpleDateFormat("dd/MM/yyyy").format(birthday);
    }
}
