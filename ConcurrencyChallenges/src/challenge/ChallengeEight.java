package challenge;


/*
 * Challenge 8 - Given this code, what is the issue and how do we fix it?
 * 
 * Notes: The results are different almost every time, in one case it completes without errors,
 *        but the events are out of order. Another case has the tutor and student deadlocked
 *        when the tutor thread goes first. The tutor is trying to call startStudy when the 
 *        student is trying to call getProgressReport, but both are in synchronized methods,
 *        blocking each other.
 *        
 *        In this case, the code should probably be rewritten to break the circular calls and
 *        use a singular thread, as the events in question in the code would have happened in
 *        a linear fashion like so:
 *        
 *        1. Tutor arrives
 *        2. Student hands in assignment
 *        3. Tutor gives progress report
 *        4. Both begin study
 *        
 *        Once this is done we can actually remove the synchronizations from the methods as well.
 */

public class ChallengeEight {
 
    public static void main(String[] args) {
        Tutor tutor = new Tutor();
        Student student = new Student(tutor);
        tutor.setStudent(student);
 
        /*Thread tutorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tutor.studyTime();
            }
        });
 
        Thread studentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                student.handInAssignment();
            }
        });
 
        tutorThread.start();
        studentThread.start();*/ // Old code
        
        tutor.studyTime();
    }
}
 
class Tutor {
    private Student student;
 
    public synchronized void setStudent(Student student) {
        this.student = student;
    }
 
/*    public synchronized void studyTime() {
        System.out.println("Tutor has arrived");
        try {
            // wait for student to arrive and hand in assignment
            Thread.sleep(300);
        }
        catch (InterruptedException e) {
 
        }
        student.startStudy();
        System.out.println("Tutor is studying with student");
    }
 
    public synchronized void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }*/ // Old code
    
    public void studyTime() {
        System.out.println("Tutor has arrived");
        try {
            // wait for student to arrive and hand in assignment
            Thread.sleep(300);
        }
        catch (InterruptedException e) {
 
        }
        student.handInAssignment(); // Student hands in assignment.
        student.startStudy();
        System.out.println("Tutor is studying with student");
    }
 
    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}
 
class Student {
 
    private Tutor tutor;
 
    Student(Tutor tutor) {
        this.tutor = tutor;
    }
 
/*    public synchronized void startStudy() {
        // study
        System.out.println("Student is studying");
    }
 
    public synchronized void handInAssignment() {
        tutor.getProgressReport();
        System.out.println("Student handed in assignment");
    }*/ // Old code
    
    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }
 
    public void handInAssignment() {
        System.out.println("Student handed in assignment");
        tutor.getProgressReport();
    }
}