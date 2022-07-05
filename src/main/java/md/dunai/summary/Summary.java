package md.dunai.summary;

public class Summary implements SummaryResult{

    private String name;
    private int total;
    private int closed;
    private int open;
    private int inProgress;

    public Summary() {}

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%d,%d", this.name,this.total, this.open, this.inProgress,this.closed);
    }

    @Override
    public String header() {
        return null;
    }

    public String toJson() {
        return "";
    }


}
