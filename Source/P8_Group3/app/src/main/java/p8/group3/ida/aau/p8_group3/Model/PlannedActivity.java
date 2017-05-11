package p8.group3.ida.aau.p8_group3.Model;


public class PlannedActivity {
    private String plannedActivityTime;
    private int plannedActivityLocationID;
    private int plannedActivityParentID;



    public PlannedActivity(String plannedActivityTime, int plannedActivityLocationID, int plannedActivityParentID) {
            this.plannedActivityTime = plannedActivityTime;
            this.plannedActivityLocationID = plannedActivityLocationID;
            this.plannedActivityParentID = plannedActivityParentID;
    }


    public String getPlannedActivityTime() {
        return plannedActivityTime;
    }

    public void setPlannedActivityTime(String plannedActivityTime) {
        this.plannedActivityTime = plannedActivityTime;
    }

    public int getPlannedActivityLocationID() {
        return plannedActivityLocationID;
    }

    public void setPlannedActivityLocationID(int plannedActivityLocationID) {
        this.plannedActivityLocationID = plannedActivityLocationID;
    }

    public int getPlannedActivityParentID() {
        return plannedActivityParentID;
    }

    public void setPlannedActivityParentID(int plannedActivityParentID) {
        this.plannedActivityParentID = plannedActivityParentID;
    }
}

