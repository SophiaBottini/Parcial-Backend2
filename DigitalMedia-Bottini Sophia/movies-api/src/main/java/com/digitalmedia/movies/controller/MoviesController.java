package com.digitalmedia.movies.controller;

import com.digitalmedia.movies.mapper.MovieMapper;
import com.digitalmedia.movies.model.Movie;
import com.digitalmedia.movies.model.dto.CreateMovieRequest;
import com.digitalmedia.movies.model.dto.MovieDto;
import com.digitalmedia.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.digitalmedia.movies.model.dto.AddCommentRequest;
import com.digitalmedia.movies.model.dto.UpdateMovieRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping
//    @PreAuthorize("hasAuthority('GROUP_client') or hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_client') or hasRole('ROLE_admin')")
    public List<MovieDto> getMovies() {
        return movieService.getMovies().stream()
                .map(movieMapper::toMovieDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{imdbId}")
//    @PreAuthorize("hasAuthority('GROUP_client') or hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_client') or hasRole('ROLE_admin')")
    public MovieDto getMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        return movieMapper.toMovieDto(movie);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
//    @PreAuthorize("hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        Movie movie = movieMapper.toMovie(createMovieRequest);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @PutMapping("/{imdbId}")
//    @PreAuthorize("hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto updateMovie(@PathVariable String imdbId, @Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieMapper.updateMovieFromDto(updateMovieRequest, movie);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @DeleteMapping("/{imdbId}")
//    @PreAuthorize("hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_admin')")
    public MovieDto deleteMovie(@PathVariable String imdbId) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieService.deleteMovie(movie);
        return movieMapper.toMovieDto(movie);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{imdbId}/comments")
//    @PreAuthorize("hasAuthority('GROUP_client') or hasAuthority('GROUP_admin')")
    @PreAuthorize("hasRole('ROLE_client') or hasRole('ROLE_admin')")
    public MovieDto addMovieComment(@PathVariable String imdbId, @Valid @RequestBody AddCommentRequest addCommentRequest, Principal principal) {
        Movie movie = movieService.validateAndGetMovie(imdbId);
        Movie.Comment comment = new Movie.Comment(principal.getName(), addCommentRequest.getText(), LocalDateTime.now());
        movie.getComments().add(0, comment);
        movie = movieService.saveMovie(movie);
        return movieMapper.toMovieDto(movie);
    }
}