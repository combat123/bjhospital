package com.xnfh.bjhospital.bean;

import java.util.List;

/**
 * Created by wangxuewei on 2017/10/20.
 */
public class PinglunBean {

    /**
     * total : 2
     * rows : [{"critic":"晨曦先生","criticPic":"http://www.nngglt.com/avatars/aaa/000/00/00/56_avatar_Small.jpg","timepl":"2017/9/1 8:20:00","content":"[b]回复 [url=http://www.nngglt.com/showtopic.aspx?topicid=194&amp;postid=356#356]1楼[color=Olive]BBMHbXLZ1z[/color]的帖子[/url][/b]\r\n\r\n这讲解，没毛病，老铁:strong:"},{"critic":"什么才是你的黑","criticPic":"http://www.nngglt.com/avatars/aaa/000/00/00/53_avatar_Small.jpg","timepl":"2017/8/31 18:46:00","content":"好讲解"}]
     */

    private String total;
    /**
     * critic : 晨曦先生
     * criticPic : http://www.nngglt.com/avatars/aaa/000/00/00/56_avatar_Small.jpg
     * timepl : 2017/9/1 8:20:00
     * content : [b]回复 [url=http://www.nngglt.com/showtopic.aspx?topicid=194&amp;postid=356#356]1楼[color=Olive]BBMHbXLZ1z[/color]的帖子[/url][/b]

     这讲解，没毛病，老铁:strong:
     */

    private List<RowsBean> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        private String critic;
        private String criticPic;
        private String timepl;
        private String content;

        public String getCritic() {
            return critic;
        }

        public void setCritic(String critic) {
            this.critic = critic;
        }

        public String getCriticPic() {
            return criticPic;
        }

        public void setCriticPic(String criticPic) {
            this.criticPic = criticPic;
        }

        public String getTimepl() {
            return timepl;
        }

        public void setTimepl(String timepl) {
            this.timepl = timepl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
