package io.github.pfwikis.bots.assistant;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Task {
    private TaskType task;
    private String image;
    private String replaceWith;
}
