package learn.example.com.zeta.model;

import java.util.List;

/**
 * Created by manisha.sharan on 04/05/19.
 */
public class Pagemap {
    private List<CseThumbnail> cse_thumbnail;
    private List<CseImage> cse_image;

    public List<CseThumbnail> getCse_thumbnail() {
        return cse_thumbnail;
    }

    public List<CseImage> getCse_image() {
        return cse_image;
    }
}
