package todoexpert.lesiecki.com.todoexpert;

import java.io.Serializable;

/**
 * Created by meep_lesp on 17.01.2017.
 */

public class Todo implements Serializable{
    private String content;
    private boolean done;

    public Todo(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "content='" + content + '\'' +
                ", done=" + done +
                '}';
    }


}
