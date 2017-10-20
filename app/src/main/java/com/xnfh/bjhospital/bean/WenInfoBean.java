package com.xnfh.bjhospital.bean;

/**
 * Created by wangxuewei on 2017/10/20.
 */
public class WenInfoBean {

    /**
     * type : 1
     * wzx : 夏天，对于很多白癜风患者来说，不是一个讨人喜欢的季节。由与白斑的原因，很多病友不敢也不愿意穿上凉爽的夏装出门，怕别人取笑自己，于是想到了一个自认为很好的主意，用纹身来遮盖白斑。

     一个好看的纹身既美丽又可以遮挡白斑，不再担心别人异样的眼光，感觉出门的压力也小了。这样看起来，纹身确实是不错的选择，有十全十美的意思，可是你必须要考虑到这样会对自己的身体造成什么伤害！


     * clj :
     * url :
     */

    private int type;
    private String wzx;
    private String clj;
    private String url;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWzx() {
        return wzx;
    }

    public void setWzx(String wzx) {
        this.wzx = wzx;
    }

    public String getClj() {
        return clj;
    }

    public void setClj(String clj) {
        this.clj = clj;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
