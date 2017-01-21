package broken.db;

/**
 *
 * @author ahm3dhany
 */
public class Quote {
    
    private Integer id;
    private String content;
    
     public Quote() {
    }

    public Quote(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
