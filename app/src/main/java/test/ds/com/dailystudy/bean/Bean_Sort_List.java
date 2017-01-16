package test.ds.com.dailystudy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 张天成
 * on 2017/1/13 13:52.
 */
public class Bean_Sort_List implements Serializable{

    private int count;
    private int limit;
    private int curpage;
    private List<DatalistBean> datalist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean implements Serializable{
        /**
         * cid : 5742
         * course_tname : 赵晓琴
         * course_name : 晓琴微课堂——梵文计数
         * course_price : 0.00
         * course_pic : http://img.dianfu.net/img/20170106/bbbc896eb9a2260c91bffcd040f09ea3.jpg
         * course_paycount : 5
         * school_name : 优学教育
         */

        private String cid;
        private String course_tname;
        private String course_name;
        private String course_price;
        private String course_pic;
        private String course_paycount;
        private String school_name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_paycount() {
            return course_paycount;
        }

        public void setCourse_paycount(String course_paycount) {
            this.course_paycount = course_paycount;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }
}
