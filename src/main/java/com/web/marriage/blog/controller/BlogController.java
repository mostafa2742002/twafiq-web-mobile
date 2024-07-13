package com.web.marriage.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.marriage.blog.dto.BlogDTO;
import com.web.marriage.blog.entity.Blog;
import com.web.marriage.blog.entity.PageResponse;
import com.web.marriage.blog.service.BlogService;
import com.web.marriage.constants.ServerConstants;
import com.web.marriage.dto.ErrorResponseDto;
import com.web.marriage.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class BlogController {

    private final BlogService blogService;

    @Operation(summary = "Create a new blog Rest API", description = "Create a new blog Rest API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Blog created successfully"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(path = "/blog")
    public ResponseEntity<ResponseDto> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
        blogService.createBlog(blogDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ServerConstants.STATUS_201, ServerConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Blog Details REST API", description = "REST API to fetch Blog &  Blog details based on a Blog ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/blog")
    public ResponseEntity<BlogDTO> getCourse(@RequestParam @NotNull String blogId) {
        BlogDTO blogDTO = blogService.getCourse(blogId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blogDTO);
    }

    @Operation(summary = "Update Blog Details REST API", description = "REST API to update Blog &  Blog details based on a Blog ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/blog")
    public ResponseEntity<ResponseDto> updateCourseDetails(@Valid @RequestBody BlogDTO blogDTO) {
        boolean isUpdated = blogService.updateCourse(blogDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ServerConstants.STATUS_417,
                            ServerConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Blog REST API", description = "REST API to delete Blog based on a Blog ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/blog")
    public ResponseEntity<ResponseDto> deleteCourse(@RequestParam @NotNull String blogId) {
        boolean isDeleted = blogService.deleteCourse(blogId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ServerConstants.STATUS_200, ServerConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ServerConstants.STATUS_417,
                            ServerConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(summary = "Fetch all Blog REST API", description = "REST API to fetch all Blogs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/blogs")
    public ResponseEntity<PageResponse<Blog>> findAllCourses(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        PageResponse<Blog> courses = blogService.findAllCourses(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courses);
    }
}
