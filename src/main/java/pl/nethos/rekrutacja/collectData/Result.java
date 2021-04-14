package pl.nethos.rekrutacja.collectData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("accountAssigned")
    @Expose
    private String accountAssigned;

    @SerializedName("requestDateTime")
    @Expose
    private String requestDateTime;

    @SerializedName("requestId")
    @Expose
    private String requestId;

    public String getAccountAssigned() {
        return accountAssigned;
    }

    public void setAccountAssigned(String accountAssigned) {
        this.accountAssigned = accountAssigned;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
