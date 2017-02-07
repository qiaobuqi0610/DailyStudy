package test.ds.com.dailystudy.bean;

import java.util.List;

/**
 * Created by 张天成
 * on 2017/2/6 16:51.
 */
public class Bean_MuLu {

    /**
     * code : 200
     * data : [{"id":"5435","step_name":"实用编发教程（28）","step_course_id":"5662","step_order":"1","nodes":[{"seid":"29057","sections_name":"编发401蝴蝶结盘发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"1","vtime":467560},{"seid":"29058","sections_name":"编发402立体爱心编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"2","vtime":285360},{"seid":"29059","sections_name":"编发403马尾辫","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"3","vtime":281840},{"seid":"29060","sections_name":"编发404随意半坡","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"4","vtime":290952},{"seid":"29061","sections_name":"编发405随意半坡编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"5","vtime":603160},{"seid":"29062","sections_name":"编发406随意三股辫","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"6","vtime":291520},{"seid":"29063","sections_name":"编发407唯美编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"7","vtime":313960},{"seid":"29064","sections_name":"编发408鱼骨辫甩花","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"8","vtime":284720},{"seid":"29065","sections_name":"编发409","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"9","vtime":438840}]}]
     * msg :
     * course_name : 实用编发教程（28）
     */

    private int code;
    private String msg;
    private String course_name;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5435
         * step_name : 实用编发教程（28）
         * step_course_id : 5662
         * step_order : 1
         * nodes : [{"seid":"29057","sections_name":"编发401蝴蝶结盘发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"1","vtime":467560},{"seid":"29058","sections_name":"编发402立体爱心编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"2","vtime":285360},{"seid":"29059","sections_name":"编发403马尾辫","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"3","vtime":281840},{"seid":"29060","sections_name":"编发404随意半坡","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"4","vtime":290952},{"seid":"29061","sections_name":"编发405随意半坡编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"5","vtime":603160},{"seid":"29062","sections_name":"编发406随意三股辫","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"6","vtime":291520},{"seid":"29063","sections_name":"编发407唯美编发","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"7","vtime":313960},{"seid":"29064","sections_name":"编发408鱼骨辫甩花","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"8","vtime":284720},{"seid":"29065","sections_name":"编发409","sections_chid":"5435","sections_des":"","sections_isfree":"1","sections_sort":"9","vtime":438840}]
         */

        private String id;
        private String step_name;
        private String step_course_id;
        private String step_order;
        private List<NodesBean> nodes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getStep_course_id() {
            return step_course_id;
        }

        public void setStep_course_id(String step_course_id) {
            this.step_course_id = step_course_id;
        }

        public String getStep_order() {
            return step_order;
        }

        public void setStep_order(String step_order) {
            this.step_order = step_order;
        }

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public static class NodesBean {
            /**
             * seid : 29057
             * sections_name : 编发401蝴蝶结盘发
             * sections_chid : 5435
             * sections_des :
             * sections_isfree : 1
             * sections_sort : 1
             * vtime : 467560
             */

            private String seid;
            private String sections_name;
            private String sections_chid;
            private String sections_des;
            private String sections_isfree;
            private String sections_sort;
            private int vtime;

            public String getSeid() {
                return seid;
            }

            public void setSeid(String seid) {
                this.seid = seid;
            }

            public String getSections_name() {
                return sections_name;
            }

            public void setSections_name(String sections_name) {
                this.sections_name = sections_name;
            }

            public String getSections_chid() {
                return sections_chid;
            }

            public void setSections_chid(String sections_chid) {
                this.sections_chid = sections_chid;
            }

            public String getSections_des() {
                return sections_des;
            }

            public void setSections_des(String sections_des) {
                this.sections_des = sections_des;
            }

            public String getSections_isfree() {
                return sections_isfree;
            }

            public void setSections_isfree(String sections_isfree) {
                this.sections_isfree = sections_isfree;
            }

            public String getSections_sort() {
                return sections_sort;
            }

            public void setSections_sort(String sections_sort) {
                this.sections_sort = sections_sort;
            }

            public int getVtime() {
                return vtime;
            }

            public void setVtime(int vtime) {
                this.vtime = vtime;
            }
        }
    }
}
