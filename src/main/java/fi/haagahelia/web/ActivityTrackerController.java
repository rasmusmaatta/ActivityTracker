package fi.haagahelia.web;

import fi.haagahelia.domain.Activity;
import fi.haagahelia.domain.ActivityRepository;
import fi.haagahelia.domain.Category;
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
    private CategoryRepository crepository;
    
    public ActivityTrackerController(ActivityRepository repository, CategoryRepository cRepository) {
        this.repository = repository;
        this.crepository = crepository;
    }
    
    @RequestMapping(value = "activities", method=RequestMethod.GET)
    public @ResponseBody List<Activity> activitylistRest() {
        return (List<Activity>) repository.findAll();
    }
    
    @RequestMapping(value="/activities/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Activity> findActivityRest(@PathVariable("id") Long ActivityId){
        return repository.findById(ActivityId);
    }
   
     @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/activitylist")
    public String activitylist(Model model) {
        model.addAttribute("activities", repository.findAll());
        return "activitylist";
    }
}
