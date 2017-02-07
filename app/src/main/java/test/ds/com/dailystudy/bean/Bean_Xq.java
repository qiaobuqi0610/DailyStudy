package test.ds.com.dailystudy.bean;

/**
 * Created by 张天成
 * on 2017/2/6 15:48.
 */
public class Bean_Xq {

    /**
     * code : 200
     * data : {"des":"&lt;p style=&quot;text-indent:2em;&quot;&gt;\n\t&lt;span style=&quot;font-family:'Microsoft YaHei';font-size:16px;&quot;&gt;&lt;strong&gt;【课程亮点】&lt;/strong&gt;&lt;/span&gt; \n&lt;/p&gt;\n&lt;p style=&quot;text-indent:2em;&quot;&gt;\n\t&lt;span style=&quot;font-family:'Microsoft YaHei';font-size:14px;&quot;&gt;轻松学会编发，手把手教您装扮美而真的你。&lt;/span&gt; \n&lt;/p&gt;\n&lt;p style=&quot;text-indent:2em;&quot;&gt;\n\t&lt;span style=&quot;font-family:'Microsoft YaHei';font-size:16px;&quot;&gt;&lt;strong&gt;【课程简介】&lt;/strong&gt;&lt;/span&gt; \n&lt;/p&gt;\n&lt;p style=&quot;text-indent:2em;&quot;&gt;\n\t&lt;span style=&quot;font-family:'Microsoft YaHei';font-size:14px;&quot;&gt;本套实用编发教程手把手教学，简单易学，让你365天发型天天不重样，是一套手残妞们都能学会编发呦。&lt;/span&gt; \n&lt;/p&gt;","course_tname":"刘景超","school_name":"优学教育"}
     * msg :
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * des : &lt;p style=&quot;text-indent:2em;&quot;&gt;
         &lt;span style=&quot;font-family:'Microsoft YaHei';font-size:16px;&quot;&gt;&lt;strong&gt;【课程亮点】&lt;/strong&gt;&lt;/span&gt;
         &lt;/p&gt;
         &lt;p style=&quot;text-indent:2em;&quot;&gt;
         &lt;span style=&quot;font-family:'Microsoft YaHei';font-size:14px;&quot;&gt;轻松学会编发，手把手教您装扮美而真的你。&lt;/span&gt;
         &lt;/p&gt;
         &lt;p style=&quot;text-indent:2em;&quot;&gt;
         &lt;span style=&quot;font-family:'Microsoft YaHei';font-size:16px;&quot;&gt;&lt;strong&gt;【课程简介】&lt;/strong&gt;&lt;/span&gt;
         &lt;/p&gt;
         &lt;p style=&quot;text-indent:2em;&quot;&gt;
         &lt;span style=&quot;font-family:'Microsoft YaHei';font-size:14px;&quot;&gt;本套实用编发教程手把手教学，简单易学，让你365天发型天天不重样，是一套手残妞们都能学会编发呦。&lt;/span&gt;
         &lt;/p&gt;
         * course_tname : 刘景超
         * school_name : 优学教育
         */

        private String des;
        private String course_tname;
        private String school_name;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }
}
