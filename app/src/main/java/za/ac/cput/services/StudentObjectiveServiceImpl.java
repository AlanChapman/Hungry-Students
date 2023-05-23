package za.ac.cput.services;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.repository.impl.ObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;

public class StudentObjectiveServiceImpl implements IStudentObjectiveService{

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private StudentRepositoryImpl studentRepository;
    private ObjectiveRepositoryImpl objectiveRepository;

    public StudentObjectiveServiceImpl(Context context) {
        this.studentObjectiveRepository = new StudentObjectiveRepositoryImpl(context);
        this.studentRepository = new StudentRepositoryImpl(context);
        this.objectiveRepository = new ObjectiveRepositoryImpl(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean createStudentObjective(int studentId, int objectiveId) {

        Student student = studentRepository.getStudent(studentId);
        Objective objective = objectiveRepository.getObjective(objectiveId);

        if(student != null && objective != null) {
            System.out.println("OBJECTIVE: " + objective);
            System.out.println("STUDENT: " + student);

            StudentObjective theStudentObjective = studentObjectiveRepository.create(new StudentObjective.Builder()
                    .setStudentId(studentId)
                    .setObjectiveId(objectiveId)
                    .build());

            return theStudentObjective != null;

        }

        return false;
    }
}
