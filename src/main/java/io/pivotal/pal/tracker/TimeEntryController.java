package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRep;
    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRep = timeEntryRepository;

    }
@PostMapping

public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry respTimeEntry = this.timeEntryRep.create(timeEntry);
        return new ResponseEntity<TimeEntry>(respTimeEntry,HttpStatus.CREATED);

    }

    @GetMapping("{timeEntryId}")

    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry respTimeEntry = this.timeEntryRep.find(timeEntryId);
        if(respTimeEntry==null){
            return new ResponseEntity<TimeEntry>(respTimeEntry,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TimeEntry>(respTimeEntry,HttpStatus.OK);
    }
    @GetMapping()

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> respTimeEntryList = this.timeEntryRep.list();
        return new ResponseEntity<List<TimeEntry>>(respTimeEntryList,HttpStatus.OK);
    }
    @PutMapping("{timeEntryId}")

    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
//        if(this.timeEntryRep.find(timeEntryId)==null){
//
//            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
//
//        }

        TimeEntry respTimeEntry = this.timeEntryRep.update(timeEntryId, timeEntryToUpdate);
    if(respTimeEntry ==null){
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

        return new ResponseEntity<TimeEntry>(respTimeEntry,HttpStatus.OK);
    }
    @DeleteMapping("{timeEntryId}")

    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {

        //if(this.timeEntryRep.find(timeEntryId)!=null){
            this.timeEntryRep.delete(timeEntryId);
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        }
//        else{
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//        }



    }
}
