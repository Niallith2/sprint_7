package apiTests.entities;

public class CourierResponse {
    private Integer id;
    private boolean ok;
    private String message;


    public CourierResponse(Integer id, boolean ok, String message) {
        this.id = id;
        this.ok = ok;
        this.message = message;
    }

    public CourierResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}