// package com.web.marriage.course.controller;

// import org.springframework.web.bind.annotation.RestController;

// import com.web.marriage.constants.ServerConstants;
// import com.web.marriage.course.dto.CourseDTO;
// import com.web.marriage.course.entity.Course;
// import com.web.marriage.course.entity.PageResponse;
// import com.web.marriage.course.service.CourseService;
// import com.web.marriage.dto.ErrorResponseDto;
// import com.web.marriage.dto.ResponseDto;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import jakarta.validation.Valid;
// import jakarta.validation.constraints.NotNull;
// import lombok.AllArgsConstructor;
// import java.util.*;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;

// @RestController
// @AllArgsConstructor
// @Validated
// @RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
// public class CourseController {

//         private final CourseService courseService;

//         @Operation(summary = "Create Course REST API", description = "REST API to create new Course")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @PostMapping("/course")
//         public ResponseEntity<ResponseDto> AddCourse(@RequestBody CourseDTO courseDTO) {
//                 courseService.addCourse(courseDTO);
//                 return ResponseEntity
//                                 .status(HttpStatus.CREATED)
//                                 .body(new ResponseDto(ServerConstants.STATUS_201, ServerConstants.MESSAGE_201));
//         }

//         @Operation(summary = "Fetch Course Details REST API", description = "REST API to fetch Course &  Course details based on a Course ID")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @GetMapping("/course")
//         public ResponseEntity<CourseDTO> getCourse(@RequestParam @NotNull String courseId) {
//                 CourseDTO courseDTO = courseService.getCourse(courseId);
//                 return ResponseEntity
//                                 .status(HttpStatus.OK)
//                                 .body(courseDTO);
//         }

//         @Operation(summary = "Update Course Details REST API", description = "REST API to update Course &  Course details based on a Course ID")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "417", description = "Expectation Failed"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @PutMapping("/course")
//         public ResponseEntity<ResponseDto> updateCourseDetails(@Valid @RequestBody CourseDTO courseDTO) {
//                 boolean isUpdated = courseService.updateCourse(courseDTO);
//                 if (isUpdated) {
//                         return ResponseEntity
//                                         .status(HttpStatus.OK)
//                                         .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
//                 } else {
//                         return ResponseEntity
//                                         .status(HttpStatus.EXPECTATION_FAILED)
//                                         .body(new ResponseDto(ServerConstants.STATUS_417,
//                                                         ServerConstants.MESSAGE_417_UPDATE));
//                 }
//         }

//         @Operation(summary = "Delete Course REST API", description = "REST API to delete Course based on a Course ID")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "417", description = "Expectation Failed"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @DeleteMapping("/course")
//         public ResponseEntity<ResponseDto> deleteCourse(@RequestParam @NotNull String courseId) {
//                 boolean isDeleted = courseService.deleteCourse(courseId);
//                 if (isDeleted) {
//                         return ResponseEntity
//                                         .status(HttpStatus.OK)
//                                         .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
//                 } else {
//                         return ResponseEntity
//                                         .status(HttpStatus.EXPECTATION_FAILED)
//                                         .body(new ResponseDto(ServerConstants.STATUS_417,
//                                                         ServerConstants.MESSAGE_417_DELETE));
//                 }
//         }

//         @Operation(summary = "Fetch all Courses REST API", description = "REST API to fetch all Courses")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @GetMapping("/courses")
//         public ResponseEntity<PageResponse<Course>> findAllCourses(
//             @RequestParam(name = "page", defaultValue = "0", required = false) int page,
//             @RequestParam(name = "size", defaultValue = "10", required = false) int size
//         ){
//                 PageResponse<Course> courses = courseService.findAllCourses(page, size);
//                 return ResponseEntity
//                                 .status(HttpStatus.OK)
//                                 .body(courses);
//         }

//         @Operation(summary = "Add A New Question REST API", description = "REST API to Add a new Question to a Course")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @PostMapping("/course/question")
//         public ResponseEntity<ResponseDto> addQuestion(@RequestBody @NotNull Question question,
//                         @RequestParam @NotNull String courseId) {
//                 courseService.addQuestion(question, courseId);
//                 return ResponseEntity
//                                 .status(HttpStatus.CREATED)
//                                 .body(new ResponseDto(ServerConstants.STATUS_201, ServerConstants.MESSAGE_201));
//         }

//         @Operation(summary = "Fetch all Questions REST API", description = "REST API to fetch all Questions of a Course")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @GetMapping("/course/questions")
//         public ResponseEntity<List<Question>> getAllQuestions(@RequestParam @NotNull String courseId) {
//                 List<Question> questions = courseService.getAllQuestions(courseId);
//                 return ResponseEntity
//                                 .status(HttpStatus.OK)
//                                 .body(questions);
//         }

//         @Operation(summary = "Delete Question REST API", description = "REST API to delete a Question from a Course")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "417", description = "Expectation Failed"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @DeleteMapping("/course/question")
//         public ResponseEntity<ResponseDto> deleteQuestion(@RequestParam @NotNull String questionId) {
//                 boolean isDeleted = courseService.deleteQuestion(questionId);
//                 if (isDeleted) {
//                         return ResponseEntity
//                                         .status(HttpStatus.OK)
//                                         .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
//                 } else {
//                         return ResponseEntity
//                                         .status(HttpStatus.EXPECTATION_FAILED)
//                                         .body(new ResponseDto(ServerConstants.STATUS_417,
//                                                         ServerConstants.MESSAGE_417_DELETE));
//                 }
//         }

//         @Operation(summary = "Update Question REST API", description = "REST API to update a Question from a Course")
//         @ApiResponses({
//                         @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//                         @ApiResponse(responseCode = "417", description = "Expectation Failed"),
//                         @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
//         })
//         @PutMapping("/course/question")
//         public ResponseEntity<ResponseDto> updateQuestion(@RequestBody @NotNull Question question) {
//                 boolean isUpdated = courseService.updateQuestion(question);
//                 if (isUpdated) {
//                         return ResponseEntity
//                                         .status(HttpStatus.OK)
//                                         .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
//                 } else {
//                         return ResponseEntity
//                                         .status(HttpStatus.EXPECTATION_FAILED)
//                                         .body(new ResponseDto(ServerConstants.STATUS_417,
//                                                         ServerConstants.MESSAGE_417_UPDATE));
//                 }
//         }

// }
