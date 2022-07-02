package md.dunai;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("Open") OPEN,
    @SerializedName("In Progress") IN_PROGRESS,
    @SerializedName("Closed") CLOSED
}
