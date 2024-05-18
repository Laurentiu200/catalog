package com.example.catalog.serviceImpl;

import com.example.catalog.exception.StudentAlreadyExistsException;
import com.example.catalog.exception.StudentNotFoundException;
import com.example.catalog.models.Student;
import com.example.catalog.repository.UserRepository;
import com.example.catalog.service.StudentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentServiceInterface {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<Student> getStudents() {
         List<Student> students = userRepository.findAll();
         return students;
    }
    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
            throw  new StudentAlreadyExistsException(student.getEmail()+ " already exists!");
        }
        return userRepository.save(student);
    }


    @Override
    public Student updateStudent(Student student, Integer id) {
        return userRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            return userRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Student getStudentById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with the Id :" +id));
    }

    @Override
    public void deleteStudent(Integer id) {
        if (!userRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, student not found");
        }
        userRepository.deleteById(id);
    }
    private boolean studentAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
