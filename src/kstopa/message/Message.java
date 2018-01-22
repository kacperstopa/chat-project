package kstopa.message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Message implements Serializable {
    private String author;
    private String content;
    private LocalDateTime time;

    public Message(String author, String content) {
        this.time = LocalDateTime.now();
        this.author = author;
        this.content = content;
    }

    public String getTimeString() {
        return time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
    }

    public String getStringWithTime() {
        return getTimeString() + " " + author + ": " + content + "\n";
    }

    public String getString() {
        return author + ": " + content + "\n";
    }
}
