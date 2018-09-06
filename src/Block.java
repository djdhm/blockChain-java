import java.util.Date;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;

    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
