package live_coding_session_7.assignment_problems.problem3;

public class CuttingTool extends GardenTool {

    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return useBase() + ", blade sharpened first";
    }
}