package za.co.mabatalale.models;

/**
 * Created by robson on 2017/03/04.
 */
public class TopFiveDailyProblems {
    public String rig;
    public String operator;
    public String problem;

    public String getRig() {
        return rig;
    }

    public void setRig(String rig) {
        this.rig = rig;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}