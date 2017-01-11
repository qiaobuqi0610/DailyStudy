package test.ds.com.dailystudy.bean;

/**
 * Created by 吕卓钊
 * on 2017/1/11 14:13.
 */

public class MineBean {
    private int imageid;
    private String name;

    public MineBean(int imageid, String name) {
        this.imageid = imageid;
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
