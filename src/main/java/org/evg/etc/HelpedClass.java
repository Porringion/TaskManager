package org.evg.etc;

import java.util.HashMap;

public class HelpedClass {

    public static HashMap<Integer, String> taskStage;

    public static void initTaskStage(){
        taskStage = new HashMap<>();

        taskStage.put(1, "TO DO");
        taskStage.put(2, "In process");
        taskStage.put(3, "Finished");
    }

}
