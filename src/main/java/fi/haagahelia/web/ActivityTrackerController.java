package fi.haagahelia.web;

import fi.haagahelia.domain.Activity;
import fi.haagahelia.domain.ActivityRepository;
import fi.haagahelia.domain.CategoryRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityTrackerController {

    private ActivityRepository repository;
    private CategoryRepository categoryRepository;

    public ActivityTrackerController(ActivityRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public @ResponseBody List<Activity> activitylistRest() {
        return (List<Activity>) repository.findAll();
    }

    @RequestMapping(value = "/activities/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Activity> findActivityRest(@PathVariable("id") Long ActivityId) {
        return repository.findById(ActivityId);
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/activitylist")
    public String activitylist(Model model) {
        model.addAttribute("activities", repository.findAll());
        return "ActivityList";
    }

    @GetMapping("/addactivity")
    public String addActivity(Model model) {
        model.addAttribute("activity", new Activity());
        model.addAttribute("categories", categoryRepository.findAll());
        return "AddActivity";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Activity activity) {
        repository.save(activity);
        return "redirect:/activitylist";
    }

    @RequestMapping(value = "/deleteactivity/{id}", method = RequestMethod.GET)
    public String deleteActivity(@PathVariable("id") Long activityId, Model model) {
        repository.deleteById(activityId);
        return "redirect:/activitylist";
    }

    @GetMapping("/editactivity/{id}")
    public String editActivities(@PathVariable("id") Long id, Model model) {
        model.addAttribute("activity", repository.findById(id).orElse(null));
        model.addAttribute("categories", categoryRepository.findAll());
        return "EditActivity";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "Login";
    }
}



