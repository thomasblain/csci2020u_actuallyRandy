public class StudentRecord {
    String studentID;
    double midterm;
    double assignments;
    double finalExam;
    double finalGrade;
    char letterGrade;

    StudentRecord(String sid, double m, double a, double fe){
        this.studentID = sid;
        this.midterm = m;
        this.assignments = a;
        this.finalExam = fe;

        this.finalGrade = (m * 0.3) + (assignments * 0.2) + (finalExam * 0.5);

        if(finalGrade < 49){
            letterGrade = 'F';
        }else if(finalGrade < 59){
            letterGrade = 'D';
        }else if(finalGrade < 69){
            letterGrade = 'C';
        }else if(finalGrade < 79){
            letterGrade = 'B';
        }else{
            letterGrade = 'A';
        }

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) { this.midterm = midterm; }

    public double getAssignments() {
        return assignments;
    }

    public void setAssignments(double assignments) {
        this.assignments = assignments;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }
}
