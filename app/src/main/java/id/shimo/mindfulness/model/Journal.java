package id.shimo.mindfulness.model;

public class Journal {
    private String bestThing, worstThing, rate, wellness, did, datetime;
    private Integer id;

    public Journal(Integer id, String bestThing, String worstThing, String rate, String wellness, String did, String datetime){
        this.id = id;
        this.bestThing = bestThing;
        this.worstThing = worstThing;
        this.rate = rate;
        this.wellness = wellness;
        this.did = did;
        this.datetime = datetime;
    }

    public Integer getId() { return id; }
    public String getBestThing() { return bestThing; }
    public String getWorstThing() { return worstThing; }
    public String getRate() { return rate; }
    public String getWellness() { return wellness; }
    public String getDid() { return did; }
    public String getDatetime() { return datetime; }
}
