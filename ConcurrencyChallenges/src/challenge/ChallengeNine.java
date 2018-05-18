package challenge;

/*
 * Challenge 9 - Code has been changed to use notify and wait with synchronized blocks, but still has
 *               a deadlock problem. Why is it deadlocking?
 *               
 *               
 * Notes: Results are consistent, what is happening is the tutor grabs its own lock, prints its first
 *        message and grabs the student lock and waits. The wait method releases the tutor lock for the
 *        student to hand in their assignment, but the tutor thread still has the student lock. The
 *        student thread cannot progress anymore without its own lock, and the tutor thread cannot
 *        progress because its waiting on a notification from another thread.
 * 
 */

public class ChallengeNine {
    public static void main(String[] args) {
        final NewTutor tutor = new NewTutor();
        final NewStudent student = new NewStudent(tutor);
        tutor.setStudent(student);

        Thread tutorThread = new Thread(new Runnable() {
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
        studentThread.start();
    }
}

class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {

        synchronized (this) {
            System.out.println("Tutor has arrived");
            synchronized (student) {
                try { /* Here  is where the code can deadlock. The tutor thread still has the
                         student lock while waiting on a notification to continue. */
                    // wait for student to arrive
                    this.wait();
                    
                } catch (InterruptedException e) {

                }
                student.startStudy();
                System.out.println("Tutor is studying with student");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}

class NewStudent {

    private NewTutor tutor;

    NewStudent(NewTutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}