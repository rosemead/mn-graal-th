package com.example;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {this.userRepository = userRepository;}

    @Get
    public ModelAndView all(@QueryValue("page") Optional<Integer> page,
                            @QueryValue("size") Optional<Integer> size) {

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(20);

        final Page<User> userPage = userRepository.findAll(Pageable.from(currentPage - 1, pageSize).order("name"));
        final Map<String, Object> model = new HashMap<>();
        model.put("userPage", userPage);
        final int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(toList());
            model.put("pageNumbers", pageNumbers);
        }
        return new ModelAndView<>("users", model);
    }
}
