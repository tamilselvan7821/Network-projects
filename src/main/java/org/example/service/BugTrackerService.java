package org.example.service;

import org.example.model.Bug;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BugTrackerService {
    private static Map<Integer, Bug> bugs = new HashMap<>();
    private static int bugId = 1;

    public Bug addBug(String title, String priority) {
        Bug bug = new Bug(bugId, title, priority, "open");
        bugs.put(bugId, bug);
        bugId++;
        return bug;
    }

    public List<Bug> getAllBugs() {
        return bugs.values().stream().collect(Collectors.toList());
    }

    public Bug updateStatus(int id, String status) {
        if (bugs.containsKey(id)) {
            Bug bug = bugs.get(id);
            bug.setStatus(status);
            return bug;
        }
        return null;
    }

    public Bug getBug(int id) {
        return bugs.get(id);
    }
}
