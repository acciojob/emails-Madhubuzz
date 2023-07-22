package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Gmail extends Email {

    private int inboxCapacity;
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() >= inboxCapacity) {
            // Move the oldest mail in the inbox to trash.
            Mail oldestMail = inbox.get(0);
            trash.add(oldestMail);
            inbox.remove(oldestMail);
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        Iterator<Mail> iterator = inbox.iterator();
        while (iterator.hasNext()) {
            Mail mail = iterator.next();
            if (mail.getMessage().equals(message)) {
                trash.add(mail);
                iterator.remove();
                return;
            }
        }
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail latestMail = inbox.get(inbox.size() - 1);
        return latestMail.getMessage();
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        Mail oldestMail = inbox.get(0);
        return oldestMail.getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private static class Mail {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }
}
