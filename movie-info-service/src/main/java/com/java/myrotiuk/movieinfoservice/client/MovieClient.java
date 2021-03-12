package com.java.myrotiuk.movieinfoservice.client;

import com.java.myrotiuk.movieinfoservice.dto.MovieSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Ivan on 11.03.2021. All rights reserved.
 */
@FeignClient(name = "movieDb", url = "https://api.themoviedb.org/3/movie")
public interface MovieClient {

    @GetMapping("/{movieId}/?api_key={apiKey}")
    public MovieSummaryDto getMovieSummary(@PathVariable("movieId") Long movieId,
                                           @PathVariable("apiKey") String apiKey);

}
