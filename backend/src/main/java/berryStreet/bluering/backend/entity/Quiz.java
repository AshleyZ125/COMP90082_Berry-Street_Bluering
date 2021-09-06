package berryStreet.bluering.backend.entity;

public class Quiz {
    private int QID;
    private String topic;
    private String overview;
    private int status;
    private int creatorID;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public int getQID() {
        return QID;
    }

    public void setQID(int QID) {
        this.QID = QID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
