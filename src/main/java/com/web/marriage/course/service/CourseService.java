// package com.web.marriage.course.service;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.stereotype.Service;

// import com.web.marriage.course.dto.CourseDTO;
// import com.web.marriage.course.dto.CourseMapper;
// import com.web.marriage.course.entity.Course;
// import com.web.marriage.course.entity.PageResponse;
// import com.web.marriage.course.repo.CourseRepository;
// import com.web.marriage.exception.ResourceNotFoundException;
// import com.web.marriage.lesson.entity.Question;
// import com.web.marriage.lesson.repo.QuestionRepository;

// import java.util.*;

// import jakarta.validation.Valid;
// import jakarta.validation.constraints.NotNull;
// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class CourseService {

//     private CourseRepository courseRepository;
//     private QuestionRepository questionRepository;

//     public void addCourse(CourseDTO courseDTO) {
//         courseDTO.setId(null);
//         Course course = CourseMapper.toCourse(courseDTO);

//         Optional<Course> courseOptional = courseRepository.findByName(course.getName());
//         if (courseOptional.isPresent()) {
//             throw new IllegalStateException("Course already exists");
//         }

//         courseRepository.save(course);
//     }

//     public CourseDTO getCourse(@NotNull String courseId) {
//         Course course = courseRepository.findById(courseId).orElseThrow(
//                 () -> new ResourceNotFoundException("Course", "Course Id", courseId));
//         CourseDTO courseDTO = CourseMapper.toCourseDto(course);
//         ArrayList<Question> questions = getAllQuestions(courseId);
//         courseDTO.setFinalQuiz(questions);
//         return courseDTO;
//     }

//     public boolean updateCourse(@Valid CourseDTO courseDTO) {
//         boolean isUpdated = false;
//         Course course = CourseMapper.toCourse(courseDTO);
//         Optional<Course> courseOptional = courseRepository.findById(course.getId());
//         if (!courseOptional.isPresent())
//             throw new ResourceNotFoundException("Course", "Course Id", courseDTO.getId());

//         courseRepository.save(course);
//         isUpdated = true;

//         return isUpdated;
//     }

//     public boolean deleteCourse(@NotNull String courseId) {
//         boolean isDeleted = false;
//         Course course = courseRepository.findById(courseId).orElseThrow(
//                 () -> new ResourceNotFoundException("Course", "Course Id", courseId));

//         courseRepository.delete(course);
//         isDeleted = true;

//         return isDeleted;
//     }

//     public PageResponse<Course> findAllCourses(int page, int size) {
//         Page<Course> coursePage = courseRepository.findAll(PageRequest.of(page, size));

//         PageResponse<Course> response = new PageResponse<>();
//         response.setContent(coursePage.getContent());
//         response.setNumber(coursePage.getNumber());
//         response.setSize(coursePage.getSize());
//         response.setTotalElements(coursePage.getTotalElements());
//         response.setTotalPages(coursePage.getTotalPages());
//         response.setFirst(coursePage.isFirst());
//         response.setLast(coursePage.isLast());

//         return response;
//     }

//     public void addQuestion(@NotNull Question question, @NotNull String courseId) {
//         Course course = courseRepository.findById(courseId).orElseThrow(
//                 () -> new ResourceNotFoundException("Course", "Course Id", courseId));

//         if (question.getId() != null)
//             question.setId(null);
//         if (question.getCourseId() == null)
//             throw new ResourceNotFoundException("Course Id", "Course Id", courseId);

//         Question savedQuestion = questionRepository.save(question);

//         course.getFinalQuizIds().add(savedQuestion.getId());
//         courseRepository.save(course);
//     }

//     public boolean deleteQuestion(@NotNull String questionId) {
//         boolean isDeleted = false;
//         Question question = questionRepository.findById(questionId).orElseThrow(
//                 () -> new ResourceNotFoundException("Question", "Question Id", questionId));

//         Course course = courseRepository.findById(question.getCourseId()).orElseThrow(
//                 () -> new ResourceNotFoundException("Course", "Course Id", question.getCourseId()));

//         course.getFinalQuizIds().remove(questionId);
//         courseRepository.save(course);

//         questionRepository.delete(question);

//         isDeleted = true;

//         return isDeleted;
//     }

//     public boolean updateQuestion(@NotNull Question question) {
//         boolean isUpdated = false;
//         if (question.getId() == null)
//             throw new ResourceNotFoundException("Question Id", "Question Id", null);
//         if (question.getCourseId() == null)
//             throw new ResourceNotFoundException("Course Id", "Course Id", null);
//         if (questionRepository.findById(question.getId()).isEmpty())
//             throw new ResourceNotFoundException("Question", "Question Id", question.getId());

//         questionRepository.save(question);

//         isUpdated = true;

//         return isUpdated;
//     }

//     public ArrayList<Question> getAllQuestions(@NotNull String courseId) {
//         Course course = courseRepository.findById(courseId).orElseThrow(
//                 () -> new ResourceNotFoundException("Course", "Course Id", courseId));

//         ArrayList<Question> questions = new ArrayList<>();
//         for (String questionId : course.getFinalQuizIds()) {
//             Question question = questionRepository.findById(questionId).orElseThrow(
//                     () -> new ResourceNotFoundException("Question", "Question Id", questionId));
//             questions.add(question);
//         }

//         return questions;
//     }
// }
