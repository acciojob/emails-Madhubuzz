package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Workspace extends Gmail {

    private static final int MAX_INBOX_CAPACITY = Integer.MAX_VALUE;
    private ArrayList<Meeting> calendar;

    public Workspace(String emailId) {
        super(emailId, MAX_INBOX_CAPACITY);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        // Sort meetings based on end time.
        calendar.sort(Comparator.comparing(Meeting::getEndTime));

        int maxMeetings = 0;
        LocalTime previousEndTime = LocalTime.MIN;

        for (Meeting meeting : calendar) {
            if (meeting.getStartTime().isAfter(previousEndTime)) {
                maxMeetings++;
                previousEndTime = meeting.getEndTime();
            }
        }
        return maxMeetings;
    }
}
